package br.com.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.orderservice.exception.feign.FeignErrorDecoder;

@Component
public class FeignConfig {

    @Bean
    public FeignErrorDecoder feignErrorDecoder(final ObjectMapper jsonMapper) {
        return new FeignErrorDecoder(jsonMapper);
    }

}
