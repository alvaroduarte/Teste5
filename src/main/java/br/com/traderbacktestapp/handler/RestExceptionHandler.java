package br.com.traderbacktestapp.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.traderbacktestapp.error.ErroDetails;
import br.com.traderbacktestapp.error.ResourceNotFoundDetails;
import br.com.traderbacktestapp.error.ResourceNotFoundException;
import br.com.traderbacktestapp.error.ValidationErrosDetails;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException) {

		
		ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder.newBuilder()
									  .timestamp(new Date().getTime())
									  .status(HttpStatus.NOT_FOUND.value())
									  .title("Resource Not Found")
									  .detail(rnfException.getMessage())
									  .developerMessage(rnfException.getClass().getName())
									  .build();
		
		return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException manvException, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<FieldError> fieldErros = manvException.getBindingResult().getFieldErrors();
		
		String fields = fieldErros.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String fieldMessager = fieldErros.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
		
		ValidationErrosDetails rnfDetails = ValidationErrosDetails.Builder.newBuilder()
									  .timestamp(new Date().getTime())
									  .status(HttpStatus.BAD_REQUEST.value())
									  .title("Field Validation Error")
									  .detail("Field Validation Error")
									  .developerMessage(manvException.getClass().getName())
									  .field(fields)
									  .fieldMessage(fieldMessager)
									  .build();
		
		return new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	/*@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


		ErroDetails erroDetails = ErroDetails.Builder.newBuilder()
									  .timestamp(new Date().getTime())
									  .status(HttpStatus.BAD_REQUEST.value())
									  .title("Resource Not Found")
									  .detail(ex.getMessage())
									  .developerMessage(ex.getClass().getName())
									  .build();
		
		return new ResponseEntity<>(erroDetails, HttpStatus.BAD_REQUEST);
	}*/
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErroDetails erroDetails = ErroDetails.Builder.newBuilder()
				  .timestamp(new Date().getTime())
				  .status(status.value())
				  .title("Internal Exception")
				  .detail(ex.getMessage())
				  .developerMessage(ex.getClass().getName())
				  .build();;
		
		return new ResponseEntity<>(erroDetails, headers, status);
	}

}