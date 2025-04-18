package kr.fado.library_exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BasicCustomException extends RuntimeException {

  private final HttpStatus status;
  private final String code;
  private final String message;

  // TODO: 파라미터로 넘겨주는 message 사용
  public BasicCustomException(BasicExceptionEnum basicExceptionEnum, String message) {
    super(message);
    this.status = basicExceptionEnum.getStatus();
    this.code = basicExceptionEnum.getCode();
    this.message = message;
  }

  // TODO: 별도로 message 파라미터를 넘겨주지 않을 때 Enum 기본 message 사용
  public BasicCustomException(BasicExceptionEnum basicExceptionEnum) {
    super(basicExceptionEnum.getMessage());
    this.status = basicExceptionEnum.getStatus();
    this.code = basicExceptionEnum.getCode();
    this.message = basicExceptionEnum.getMessage();
  }
}
