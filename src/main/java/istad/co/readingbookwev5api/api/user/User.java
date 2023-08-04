package istad.co.readingbookwev5api.api.user;

import istad.co.readingbookwev5api.api.file.File;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String familyName;
    private String givenName;
    private String phoneNumber;
    private File profile;
    private String biography;
    private Boolean isEnabled;
    private String verificationCode;

    private List<Role> roles;
}
