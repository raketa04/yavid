package by.yavid.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such type production")
public class ThereIsNoSuchGroupMCException extends RuntimeException {
}
