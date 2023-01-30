package com.ty.store.storespring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.store.storespring.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> NoSuchIdFoundExceptionHandler(
			NoSuchIdFoundException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("ID Not FOund");
		responseStructure.setData(exception.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(
				responseStructure, HttpStatus.NOT_FOUND);

		return responseEntity;

	}
}
