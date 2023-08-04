package istad.co.readingbookwev5api.shared.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ValidatedError {

    private String field;
    private String message;


}
