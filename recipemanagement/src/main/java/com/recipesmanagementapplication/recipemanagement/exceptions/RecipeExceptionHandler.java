package com.recipesmanagementapplication.recipemanagement.exceptions;

import com.recipesmanagementapplication.recipemanagement.dto.ResponseDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RecipeExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errorMessage = errorList.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO("Exception Handling while REST API call", errorMessage);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        String errorMessage = exception.getMessage();
        ResponseDTO responseDTO = new ResponseDTO("Invalid Input From User", errorMessage);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecipeIDNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleRecipeIDNotFoundException(RecipeIDNotFoundException exception){
        ResponseDTO responseDTO = new ResponseDTO("Exception Handling while REST API call", exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
