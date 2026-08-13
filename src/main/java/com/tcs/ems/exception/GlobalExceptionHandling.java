package com.tcs.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFound(UserNotFoundException userNotFoundException ){
		return new ResponseEntity<String>(userNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(UserAlreadyExistsException.class)
		public ResponseEntity<String> userALreadyExists(UserAlreadyExistsException alreadyExistsException){
			return new ResponseEntity<String>(alreadyExistsException.getMessage(),HttpStatus.CONFLICT);
	}
	
	
	@ExceptionHandler(UserIsVerifiedException.class)
	public ResponseEntity<String> userIsVerified(UserIsVerifiedException  isVerifiedException){
		return new ResponseEntity<String>(isVerifiedException.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(InvalidOtpException.class)
	public ResponseEntity<String> invalidOtp(InvalidOtpException  invalidOtpException){
		return new ResponseEntity<String>(invalidOtpException.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(OtpExpiredException.class)
	public ResponseEntity<String> otpExpired(OtpExpiredException otpExpiredException){
		return new ResponseEntity<String>(otpExpiredException.getMessage(),HttpStatus.REQUEST_TIMEOUT);
	}
	
	
	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<String> InvalidEmail(InvalidEmailException emailException){
		return new ResponseEntity<String>(emailException.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MissingFieldException.class)
	public ResponseEntity<String> MissingField(MissingFieldException missingFieldException){
		return new ResponseEntity<String>(missingFieldException.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
