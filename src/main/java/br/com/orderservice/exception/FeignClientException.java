package br.com.orderservice.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.orderservice.common.model.ErrorResponse;
import lombok.Getter;

@Getter
public class FeignClientException extends RuntimeException {

	private static final long serialVersionUID = -8435092347787844622L;
	
	private final ErrorResponse error;
    private final List<ErrorResponse> errors = new ArrayList<>();
    private final HttpStatus httpStatus;

    public FeignClientException(final HttpStatus httpStatus, final ErrorResponse error) {
        this.error = error;
        this.httpStatus = httpStatus;

    }

    public FeignClientException(final HttpStatus httpStatus, final List<ErrorResponse> errors) {
        this.error = null;
        this.errors.addAll(errors);
        this.httpStatus = httpStatus;
    }

}
