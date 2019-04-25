package com.bosssoft.bigdata.common.minio.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.bosssoft.bigdata.common.minio.service.MinioTemplate;
import com.bosssoft.bigdata.common.minio.vo.MinioItem;
import com.bosssoft.bigdata.common.minio.vo.MinioObject;
import io.minio.messages.Bucket;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * minio 对外提供服务端点<br/>
 * Spring boot中有个注解@ConditionalOnProperty，这个注解能够控制某个configuration是否生效。<br/>
 * 具体操作是通过其两个属性name以及havingValue来实现的，其中name用来从application.properties中读取某个属性值，如果该值为空，则返回false;<br/>
 * 如果值不为空，则将该值与havingValue指定的值进行比较，如果一样则返回true;否则返回false。<br/>
 * 如果返回值为false，则该configuration不生效；为true则生效。<br/>
 * 如果minio.endpoint.enable在配置文件中并且值为true
 * @author lucky
 * <p>
 */
@ConditionalOnProperty(name = "minio.endpoint.enable", havingValue = "true")
@RestController
@AllArgsConstructor
@RequestMapping("${minio.endpoint.name:/minio}")
public class MinioEndpoint {
	private final MinioTemplate template;

	/**
	 * Bucket Endpoints
	 */
	@SneakyThrows
	@PostMapping("/bucket/{bucketName}")
	public Bucket createBucker(@PathVariable String bucketName) {

		template.createBucket(bucketName);
		return template.getBucket(bucketName).get();

	}

	@SneakyThrows
	@GetMapping("/bucket")
	public List<Bucket> getBuckets() {
		return template.getAllBuckets();
	}

	@SneakyThrows
	@GetMapping("/bucket/{bucketName}")
	public Bucket getBucket(@PathVariable String bucketName) {
		return template.getBucket(bucketName).orElseThrow(() -> new IllegalArgumentException("Bucket Name not found!"));
	}

	@SneakyThrows
	@DeleteMapping("/bucket/{bucketName}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteBucket(@PathVariable String bucketName) {
		template.removeBucket(bucketName);
	}

	/**
	 * Object Endpoints
	 */
	@SneakyThrows
	@PostMapping("/object/{bucketName}")
	public MinioObject createObject(@RequestBody MultipartFile object, @PathVariable String bucketName) {
		String name = object.getOriginalFilename();
		template.putObject(bucketName, name, object.getInputStream(), object.getSize(), object.getContentType());
		return new MinioObject(template.getObjectInfo(bucketName, name));

	}

	@SneakyThrows
	@PostMapping("/object/{bucketName}/{objectName}")
	public MinioObject createObject(@RequestBody MultipartFile object, @PathVariable String bucketName, @PathVariable String objectName) {
		template.putObject(bucketName, objectName, object.getInputStream(), object.getSize(), object.getContentType());
		return new MinioObject(template.getObjectInfo(bucketName, objectName));

	}

	@SneakyThrows
	@GetMapping("/object/{bucketName}/{objectName}")
	public List<MinioItem> filterObject(@PathVariable String bucketName, @PathVariable String objectName) {

		return template.getAllObjectsByPrefix(bucketName, objectName, true);

	}

	@SneakyThrows
	@GetMapping("/object/{bucketName}/{objectName}/{expires}")
	public Map<String, Object> getObject(@PathVariable String bucketName, @PathVariable String objectName, @PathVariable Integer expires) {
		Map<String, Object> responseBody = new HashMap<>(8);
		// Put Object info
		responseBody.put("bucket", bucketName);
		responseBody.put("object", objectName);
		responseBody.put("url", template.getObjectURL(bucketName, objectName, expires));
		responseBody.put("expires", expires);
		return responseBody;
	}

	@SneakyThrows
	@ResponseStatus(HttpStatus.ACCEPTED)
	@DeleteMapping("/object/{bucketName}/{objectName}/")
	public void deleteObject(@PathVariable String bucketName, @PathVariable String objectName) {

		template.removeObject(bucketName, objectName);
	}


}
