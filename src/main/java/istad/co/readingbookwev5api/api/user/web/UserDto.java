package istad.co.readingbookwev5api.api.user.web;

import istad.co.readingbookwev5api.api.file.web.FileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String username;
    private String email;
    private String familyName;
    private String givenName;
    private String phoneNumber;
    private String biography;
    private FileDto profile;

}
