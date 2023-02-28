package com.hanssem.remodeling.common.common.advice;

import com.hanssem.remodeling.common.common.response.Response;
import com.hanssem.remodeling.common.common.util.Utility;
import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.constant.ResponseCode;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintDeclarationException;
import java.security.InvalidParameterException;
import java.util.*;

@Slf4j
@RestControllerAdvice
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> customException(CustomException e) {
        log.error("CustomException -> {}", e.getMessage());
        if (Objects.isNull(e.getData())) {
            return response(e.getCode(), e.getMessage());
        }
        return response(e.getCode(), e.getMessage(), e.getData());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> BadRequestException(Exception e) {
        log.error("BadRequestException -> {}", e.getMessage());
        return response(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<Response> FileUploadException(FileUploadException e) {
        log.error("FileUploadException -> {}", e.getMessage());
        return response(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Response> invalidParams(InvalidParameterException e) {
        log.error("InvalidParameterException -> {}", e.getMessage());
        return response(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, JsonParseException.class, JsonMappingException.class})
    public ResponseEntity<Response> httpMessageNotReadable(HttpServletRequest req, HttpMessageNotReadableException e) {

        String msg = "";
        Throwable throwable = e.getMostSpecificCause();

        if (throwable instanceof InvalidFormatException ife) {
            if (ife.getTargetType().isEnum()) {
                msg = ife.getPath().get(0).getFieldName() + " 값을 확인해주세요. " + Arrays.toString(ife.getTargetType().getEnumConstants());
            } else {
                msg = "Please check the error type of reverse serialization.";
            }
        } else {
            msg = "Please check your json grammar.";
        }
        log.error("exception -> {}", msg);
        return response(HttpStatus.BAD_REQUEST, msg);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> SqlException(Exception e) {
        log.error("exception -> {}", e.getMessage());
        e.printStackTrace();
        return response(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<Response> RequestNotValidHandler(Exception e) {

        List<FieldError> errors = new ArrayList<>();

        if (e instanceof MethodArgumentNotValidException) {
            final MethodArgumentNotValidException mane = (MethodArgumentNotValidException) e;
            final BindingResult bindingResult = mane.getBindingResult();
            errors = bindingResult.getFieldErrors();
        } else if (e instanceof BindException) {
            final BindException be = (BindException) e;
            errors = be.getFieldErrors();
        }

        final StringJoiner sj = Utility.stringWithBlank();
        errors.forEach(error -> sj.add(replaceVariable(error.getDefaultMessage(), error.getField(), error.getRejectedValue())));

        log.debug("RequestNotValidException -> " + e.getMessage());
        return response(HttpStatus.BAD_REQUEST, sj.toString());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String msg = "";
        if (e.getRequiredType().getEnumConstants() != null) {
            msg = Arrays.toString(e.getRequiredType().getEnumConstants());
            final StringJoiner sj = Utility.stringWithBlank();
//            sj.add(ErrorCode.DEFINED_VALUE.getMessage()).add(msg);
            msg = sj.toString();
        } else {
//            msg = ErrorCode.INVALID_PARAMETER.getMessage();
        }
        log.debug("MethodArgumentTypeMismatchException -> " + msg);
        return response(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Response> HttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.debug("HttpMediaTypeNotSupportedException -> " + e.getMessage());
        return response(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Response> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.debug("HttpRequestMethodNotSupportedException -> {}", e.getMessage());
        return response(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ConstraintDeclarationException.class)
    public ResponseEntity<Response> ConstraintDeclarationException(ConstraintDeclarationException cde) {
        log.debug("ConstraintDeclarationException");
        return response(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Response> EmptyResultDataAccessException() {
        log.debug("EmptyResultDataAccessException");
        return response(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidPropertyException.class)
    public ResponseEntity<Response> InvalidPropertyException(InvalidPropertyException ipe) {
        log.debug("InvalidPropertyException -> " + ipe.getMessage());
        return response(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Response> NoHandlerFoundException() {
        log.debug("NoHandlerFoundException");
        return response(HttpStatus.NOT_FOUND, ResponseCode.NOT_FOUND.getMessage());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Response> MissingServletRequestPartException(MissingServletRequestPartException exception) {
        log.debug("MissingServletRequestPartException -> " + exception.getMessage());
        return response(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Response> MissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        log.debug("MissingServletRequestParameterException -> " + exception.getMessage());
        return response(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Response> response(final int status, final String message) {
        return ResponseEntity.ok(new Response<ResponseCode>(status, message, null));
    }

    private ResponseEntity<Response> response(final int status, final String message, final Map<String, String> data) {
        return ResponseEntity.ok(new Response<>(status, message, data));
    }

    private ResponseEntity<Response> response(final HttpStatus status) {
        return response(status.value(), status.getReasonPhrase());
    }

    private ResponseEntity<Response> response(final HttpStatus status, String message) {
        return ResponseEntity.ok(new Response<ResponseCode>(status.value(), message, null));
    }

    private String replaceVariable(final String message, final String field, final Object rejectValue) {
        return Objects.requireNonNull(message)
            .replace("{field}", field)
            .replace("{rejectValue}", Objects.isNull(rejectValue) ? "null" : rejectValue.toString());
    }
}
