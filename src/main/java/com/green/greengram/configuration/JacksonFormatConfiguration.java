package com.green.greengram.configuration;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.databind.ser.std.ToStringSerializer;

@Configuration
public class JacksonFormatConfiguration {
    @Bean
    public JsonMapperBuilderCustomizer jsonMapperBuilderCustomizer() {
        return builder -> {
            // 1. Long 타입을 String으로 변환할 모듈 생성
            SimpleModule longToStringModule = new SimpleModule("LongToStringModule");

            // 2. Serializer 등록 (Wrapper와 Primitive 모두 대응)
            longToStringModule.addSerializer(Long.class, ToStringSerializer.instance);
            longToStringModule.addSerializer(long.class, ToStringSerializer.instance);
            builder.addModule(longToStringModule);
        };
    }
}
