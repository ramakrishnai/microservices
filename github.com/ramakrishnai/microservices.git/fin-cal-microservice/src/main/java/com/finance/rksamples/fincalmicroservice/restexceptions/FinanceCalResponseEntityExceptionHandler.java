package com.finance.rksamples.fincalmicroservice.restexceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.finance.rksamples.fincalmicroservice.user.UserNotFoundException;


/*
 * ResponseEntityException -> go through this class for more details.
 * This Controller will be used within several other Controllers / Resources
 * 
 * @ControllerAdvice:
 * 		- Declared a Class -> to share (mainly Exceptions) across multiple Controller classes 
 * 		- InitBinder ->  Handle Dates using this annotation 
 * 		- ModelAttribute -> Common Model attribute across all controllers
 */


@ControllerAdvice
@RestController
public class FinanceCalResponseEntityExceptionHandler 
				extends ResponseEntityExceptionHandler{

	
	//Just for Internal Server Error By any Rest Controller/Resource. 
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions
					(Exception ex, WebRequest request) {
		
		ExceptionMessageResponse exceptionMessageResponse = 
				new ExceptionMessageResponse(new Date(), ex.getMessage(), request.getDescription(false));
	
		return new ResponseEntity<Object>(exceptionMessageResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	//Specific Exception: User Not Found Exception
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions
					(Exception ex, 
					 WebRequest request) {
		
		ExceptionMessageResponse exceptionMessageResponse = 
				new ExceptionMessageResponse(new Date(), ex.getMessage(), request.getDescription(false));
	
		return new ResponseEntity<Object>(exceptionMessageResponse, HttpStatus.NOT_FOUND);
	}
	
	
	
	/*@Valid - validation happens if any invalid input in Http Request,
	 * The validation error as 'Bad Request' will be returned by 
	 * ResponseEntityExceptionHander.handleMethodArgumentNotValid() 
	 * So just override this method to customise with your own-exception in FinCalxxxExceptionHandler.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, 
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
		
		ExceptionMessageResponse exceptionMessageResponse = 
				new ExceptionMessageResponse(
						new Date(), 
						"Invalid Data Found in the API Request", //see messages - BindingResults, also validation-api-2.x.x.Final.jar inside maven
						ex.getBindingResult().toString()); 
		return new ResponseEntity<Object>(exceptionMessageResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
