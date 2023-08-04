package istad.co.readingbookwev5api.shared.rest;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class RestError<T> {

    private Boolean status;
    private Integer code;
    private String message;
    private Timestamp timestamp;
    private T error;
}
