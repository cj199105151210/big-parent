package org.springframework.cloud.openfeign;

import cn.hutool.core.util.StrUtil;
import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.common.core.util.R;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author bigdata
 * <p>
 * fallback 代理处理
 */
@Slf4j
@AllArgsConstructor
public class BosxFeignFallback<T> implements MethodInterceptor {
	private final Class<T> targetType;
	private final String targetName;
	private final Throwable cause;

	@Nullable
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) {
		Class<?> returnType = method.getReturnType();
		if (R.class != returnType) {
			return null;
		}
		FeignException exception = (FeignException) cause;

		byte[] content = exception.content();

		String str = StrUtil.str(content, StandardCharsets.UTF_8);

		log.error("BosxFeignFallback:[{}.{}] serviceId:[{}] message:[{}]", targetType.getName(), method.getName(), targetName, str);
		return R.builder().code(CommonConstants.FAIL)
				.msg(str).build();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BosxFeignFallback<?> that = (BosxFeignFallback<?>) o;
		return targetType.equals(that.targetType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(targetType);
	}
}
