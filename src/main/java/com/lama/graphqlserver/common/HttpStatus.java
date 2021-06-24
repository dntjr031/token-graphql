package com.lama.graphqlserver.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum HttpStatus {
  /** 요청에 대한 성공 **/
  SUCCESS(200, "요청이 정상적으로 처리되었습니다"),

  /** 요청에 대한 성공 및 결과 없음 **/
  NO_DATA(204, "검색된 데이터가 없습니다"),

  /** 요청에 대한 접근 거부 **/
  ACCESS_DENIED(401, "액세스가 거부되었습니다"),

  /** 요청에 대한 옳지 않은 파라미터 **/
  INVALID_PARAMETER(400, "올바르지 않는 파라미터입니다"),

  /** 존재하지 않는 테이블 **/
  NO_TABLE(405, "존재하지 않는 테이블입니다"),

  /** 요청 처리 중 발생한 서버 에러 **/
  INTERNAL_SERVER_ERROR(500, "데이터 처리 중 에러가 발생했습니다");

  private int status;

  private String message;
}
