package com.lama.graphqlserver.util;

import com.lama.graphqlserver.common.CommonResponse;
import com.lama.graphqlserver.common.HttpStatus;

public class ResponseUtil {

  public static <T> CommonResponse<T> success(T data) {
    HttpStatus status = HttpStatus.SUCCESS;
    return new CommonResponse<T>(status.getStatus(), status.getMessage(), data);
  }

  public static <T> CommonResponse<T> fail(T data) {
    HttpStatus status = HttpStatus.ACCESS_DENIED;
    return new CommonResponse<T>(status.getStatus(), status.getMessage(), data);
  }

  public static <T> CommonResponse<T> fail(T data, String message) {
    HttpStatus status = HttpStatus.ACCESS_DENIED;
    return new CommonResponse<T>(status.getStatus(), message, data);
  }

}
