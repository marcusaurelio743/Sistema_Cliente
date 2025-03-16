package Vendas.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import Vendas.rest.exception.ApiErrors;

@RestControllerAdvice
public class ApplicationControlerAdvice {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationError(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<String> messages = bindingResult.getAllErrors()
			.stream()
				.map(objecterror-> objecterror.getDefaultMessage())
				.collect(Collectors.toList());
		
		return new ApiErrors(messages);
		
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<?> responseStatus(ResponseStatusException ex) {
		String message = ex.getMessage();
		HttpStatus codigo = ex.getStatus();
		ApiErrors apiErrors = new ApiErrors(message);
		
		return  ResponseEntity.status(codigo).body(apiErrors);
	}

}
