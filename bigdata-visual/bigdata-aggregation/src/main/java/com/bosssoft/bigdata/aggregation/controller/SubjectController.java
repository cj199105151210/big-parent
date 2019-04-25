package com.bosssoft.bigdata.aggregation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.aggregation.constant.MinioBucketEnum;
import com.bosssoft.bigdata.aggregation.entity.*;
import com.bosssoft.bigdata.aggregation.service.*;
import com.bosssoft.bigdata.aggregation.vo.SubjectRelVO;
import com.bosssoft.bigdata.aggregation.vo.SubjectVO;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.minio.service.MinioTemplate;
import com.bosssoft.bigdata.usercenter.api.feign.RemoteUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author lifei
 * @date 2019-03-11 17:38:46
 */
@RestController
@AllArgsConstructor
@RequestMapping("/subject")
@Api(value = "subject", tags = "栏目管理")
public class SubjectController {

    private final AggrLightappService aggrLightappService;
    private final SubjectService subjectService;
    private final RemoteUserService remoteUserService;
    private final MinioTemplate template;

    /**
     * 查询树结构方法
     *
     * @return 每个门户所包含栏目的树形结构
     */
    @GetMapping("/getSubjectTree")
    public R getSubjectTree() {
        return new R<>(subjectService.getSubjectTree());
    }

    /**
     * 查询控件详细信息方法
     *
     * @param subjectId 《栏目表》主键
     * @return 查询对应《控件信息表》和《栏目表》取得控件详细信息
     */
    @GetMapping("/subject")
    public R getSubjectById(@RequestParam Integer subjectId, @RequestParam Integer gatewayId) {
        List<Integer> subjectIdList = new ArrayList<>();
        if (!subjectId.equals(-1)) {
            subjectIdList.add(subjectId);
        }
        List<Integer> gatewayIdList = new ArrayList<>();
        if (!gatewayId.equals(-1)) {
            gatewayIdList.add(gatewayId);
        }
        return new R<>(subjectService.getSubjectById(subjectIdList, gatewayIdList).get(0));
    }

    /**
     * 新增、修改下级
     *
     * @param subjectVO 页面数据
     * @return 操作结果
     */
    @PostMapping("/addOrUpdateTreeNode")
    public R addOrUpdateTreeNode(@Valid @RequestBody SubjectVO subjectVO) {
        return subjectService.addOrUpdateTreeNode(subjectVO);
    }

    /**
     * 删除下级
     *
     * @param subjectId 栏目表主键
     * @return 删除结果
     */
    @GetMapping("/deleteTreeNode")
    public R deleteTreeNode(@RequestParam Integer subjectId) {
        return subjectService.deleteTreeNode(subjectId);
    }

    /**
     * 查询用户组方法
     *
     * @return 用户中心中所包含的所有的用户组ID及名称
     */
    @GetMapping("/getUserGroupMsg")
    public R getUserGroupMsg() {
        return remoteUserService.getAllUserGroup();
    }

    /**
     * 保存用户自定义栏目信息
     *
     * @param subjectRelVO 自定义栏目信息
     * @return 处理结果
     */
    @PostMapping("/subjectRel")
    @ApiOperation("保存用户自定义门户页面内容")
    public R subjectRel(@RequestBody SubjectRelVO subjectRelVO) {
        return subjectService.subjectRel(subjectRelVO);
    }

    /**
     * 根据登录用户获取页面内容
     *
     * @param code 门户code
     * @return 页面内容
     */
    @GetMapping("/getGatewayPage")
    @ApiOperation("根据登录用户获取指定门户页面内容")
    @ApiImplicitParam(name = "code", value = "门户编码", required = true)
    public R getGatewayPage(@RequestParam String code) {
        return new R<>(subjectService.getGatewayPage(code));
    }

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R getAggrLightappPage(Page page, String name) {
        QueryWrapper<AggrLightapp> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        IPage<AggrLightapp> searchResult = aggrLightappService.page(page, queryWrapper);
        List<AggrLightapp> resultList = searchResult.getRecords();
        for (AggrLightapp lightapp : resultList) {
            lightapp.setPhotoUrl(template.getObjectURL(MinioBucketEnum.BUCKETNAME_LIGHTAPP.getName(), lightapp.getPhotoUrl(), 604800));
        }
        return new R<>(searchResult);
    }

    /**
     * 图片上传
     *
     * @param file 需要上传的文件
     * @return
     */
    @PostMapping("/upload")
    public Map<String, String> createObject(MultipartFile file) throws Exception {
        String objectName = "";
        String fileName[] = file.getOriginalFilename().split("\\.");
        if (fileName.length == 2) {
            objectName = String.valueOf(System.currentTimeMillis()) + "." + fileName[1];
        } else {
            throw new Exception("请勿在文件名中包含“.”！");
        }
        if (file.getSize() / 1048576 > 5) {
            throw new Exception("上传图片大小不能超过 5MB!！");
        }

        template.putObject(MinioBucketEnum.BUCKETNAME_SLIDESHOW.getName(), objectName, file.getInputStream(), file.getSize(), file.getContentType());
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("url", template.getObjectURL(MinioBucketEnum.BUCKETNAME_SLIDESHOW.getName(), objectName, 604800));
        resultMap.put("fileName", objectName);
        return resultMap;
    }

    /**
     * 根据轻应用ID获取轻应用信息
     *
     * @param id 轻应用ID
     * @return
     */
    @GetMapping("/getAggrLightappMsg")
    @ApiOperation("根据轻应用ID获取轻应用信息")
    @ApiImplicitParam(name = "id", value = "轻应用ID", required = true)
    public R getAggrLightappMsg(Integer id) {
        AggrLightapp aggrLightapp = aggrLightappService.getById(id);
        aggrLightapp.setPhotoUrl(template.getObjectURL(MinioBucketEnum.BUCKETNAME_LIGHTAPP.getName(), aggrLightapp.getPhotoUrl(), 604800));
        return new R<>(aggrLightapp);
    }
}
