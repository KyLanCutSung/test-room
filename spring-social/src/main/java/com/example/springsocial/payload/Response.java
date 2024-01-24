package com.example.springsocial.payload;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class Response<T> {
    @NotNull
    private boolean success;
    @NotNull
    private int statusCode;
    private String message;
    private String messageCode;
    private String debugMessage;
    @NotNull
    private T data;
    @NotNull
    private int total;

    public Response<T> setMessage(String messageCode, String message) {
        this.message = message;
        this.messageCode = messageCode;
        return this;
    }

    public Response<T> setMessage(String messageCode) {
        this.message = "";
        this.messageCode = messageCode;
        return this;
    }

    public static <T> Response<T> ok(T data) {
        return Response.<T>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .data(data)
                .message("OK")
                .messageCode("ok")
                .total(1)
                .debugMessage("")
                .build();
    }

    public static <T> Response<List<T>> ok(Page<T> data) {
        return Response.<List<T>>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .data(data.getContent())
                .message("OK")
                .messageCode("ok")
                .total((int) data.getTotalElements())
                .debugMessage("")
                .build();
    }

    public static <T> Response<T> created(T data) {
        return Response.<T>builder()
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .data(data)
                .message("CREATED")
                .messageCode("created")
                .total(1)
                .debugMessage("")
                .build();
    }

    public static <T> Response<T> deleted() {
        return Response.<T>builder()
                .success(true)
                .statusCode(HttpStatus.NO_CONTENT.value())
                .message("DELETED")
                .messageCode("deleted")
                .total(1)
                .debugMessage("")
                .build();
    }

    public static <T> Response<T> notfound() {
        return Response.<T>builder()
                .success(false)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message("Dữ liệu không tồn tại")
                .messageCode("not-found")
                .total(1)
                .debugMessage("")
                .build();
    }

    public static <T> Response<T> bad() {
        return Response.<T>builder()
                .success(false)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("")
                .messageCode("")
                .total(1)
                .debugMessage("")
                .build();
    }
}
