package br.com.orderservice.exception.feign;

import static org.springframework.http.HttpStatus.valueOf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.orderservice.common.model.ErrorResponse;
import br.com.orderservice.exception.FeignClientException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper jsonMapper;
    private final ErrorDecoder errorDecoder = new Default();

    public FeignErrorDecoder(final ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(response.body().asInputStream()))) {
            final var errorMessage = br.lines().collect(Collectors.joining(System.lineSeparator()));
            if (response.status() == 400) {
                final List<ErrorResponse> errorList = jsonMapper.readValue(errorMessage, new TypeReference<List<ErrorResponse>>() {
                });
                return new FeignClientException(valueOf(response.status()), errorList);
            }

            return new FeignClientException(valueOf(response.status()), jsonMapper.readValue(errorMessage, ErrorResponse.class));
        } catch (IOException e) {
            return errorDecoder.decode(methodKey, response);
        }
    }
}
