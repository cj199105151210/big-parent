package com.bosssoft.bigdata.common.security.component;

import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.common.security.exception.BosxAuth2Exception;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;

/**
 * @author lucky
 * @date 2019/2/23
 * <p>
 * OAuth2 异常格式化
 */
public class BosxAuth2ExceptionSerializer extends StdSerializer<BosxAuth2Exception> {
	public BosxAuth2ExceptionSerializer() {
		super(BosxAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(BosxAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}
}
