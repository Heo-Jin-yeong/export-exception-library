package kr.fado.library_exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@AllArgsConstructor     // 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성
@Builder
public class BasicExceptionResponse {

    private String errorCode;
    private String errorMessage;
}
