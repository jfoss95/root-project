package com.eureka.data.vo;


import java.util.ArrayList;
import java.util.List;

public class ErrorsVO {
     private final String code;
     private final String message;
     private final List<ErrorVO> errors;

     public ErrorsVO(final String code, final String message,
                       final String reason, final String errorMessage) {
         this.code = code;
         this.message = message;
         List<ErrorVO> errorsList = new ArrayList<>();
         errorsList.add((new ErrorVO(reason, errorMessage)));
         this.errors = errorsList;
     }

     private ErrorsVO(final String code, final String message, final List<ErrorVO> errors) {
         this.code = code;
         this.message = message;
         this.errors = errors;
     }

     public static ErrorsVO copyWithMessage(final ErrorsVO errorsVO, final String message) {
         return new ErrorsVO(errorsVO.code, message, errorsVO.errors);
     }

     public String getCode() {
         return code;
     }

     public String getMessage() {
         return message;
     }

     public List<ErrorVO> getErrors() {
         return errors;
     }
}
