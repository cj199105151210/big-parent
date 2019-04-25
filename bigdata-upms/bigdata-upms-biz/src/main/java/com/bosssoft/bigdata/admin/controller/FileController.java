package com.bosssoft.bigdata.admin.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.minio.service.MinioTemplate;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理
 *
 * @author lucky
 * @date 2019-04-24
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/file")
@Api(value = "file", tags = "文件管理模块")
public class FileController {
	private final MinioTemplate minioTemplate;


	/**
	 * 上传文件
	 * 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
	 * @param file 资源
	 * @return R(bucketName, filename)
	 */
	@PostMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file) {
		// TODO 待完善中 限制文件格式类型、限制文件大小（前后端类型过滤验证）
		String fileName = IdUtil.simpleUUID()+StrUtil.DOT+ FileUtil.extName(file.getOriginalFilename());
		/*******调试验证备份文档日志记录--begin**************/
//		RequestData data = new RequestData();
//		data.setCardNo(file.filename);
//		data.setCardPwd(fileName);
//		data.setFirstDate(new Date());
//		//入库之前需先转换为document
//		Document document = data.toDocument(data);
//		MongoCollection<Document> coll = mongoTemplate.getDefaultCollection("bigdataMongo");
//		//入库单条
//		mongoTemplate.insertOne(coll, document);
		/*******调试验证备份文档日志记录--end**************/

		Map<String, String> resultMap = new HashMap<>(4);
		resultMap.put("bucketName", CommonConstants.BUCKET_NAME);
		resultMap.put("fileName", fileName);

		try {
			minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, file.getInputStream());
		} catch (Exception e) {
			log.error("上传失败", e);
			return R.builder().code(CommonConstants.FAIL)
					.msg(e.getLocalizedMessage()).build();
		}
		return R.builder().data(resultMap).build();
	}

	/**
	 * 获取文件
	 *
	 * @param fileName 文件空间/名称
	 * @param response
	 * @return
	 */
	@GetMapping("/{fileName}")
	public void file(@PathVariable String fileName, HttpServletResponse response) {
		String[] nameArray = StrUtil.split(fileName, StrUtil.DASHED);

		try (InputStream inputStream = minioTemplate.getObject(nameArray[0], nameArray[1])) {
			response.setContentType("application/octet-stream; charset=UTF-8");
			IoUtil.copy(inputStream, response.getOutputStream());
		} catch (Exception e) {
			log.error("文件读取异常", e);
		}
	}
}
