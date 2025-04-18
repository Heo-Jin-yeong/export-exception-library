package kr.fado.library_exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor     // 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성
public enum BasicExceptionEnum {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401", "인증이 필요한 요청입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "403", "접근 권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "404", "요청한 데이터를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "405", "허용되지 않은 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "서비스가 원활하지 않습니다. \n 문제가 지속되는 경우 관리자에게 문의해주세요."),
    ;

    private final HttpStatus status;
    private final String code;
    private String message;

    public void changeMessage(String message) {
        this.message = message;
    }
}
