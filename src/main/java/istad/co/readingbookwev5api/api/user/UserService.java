package istad.co.readingbookwev5api.api.user;

import istad.co.readingbookwev5api.api.user.web.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserByUsernameOrEmail(String username);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    boolean checkUserEmail(String email);

    boolean checkUsername(String username);

    UserDto enableAndDisableUser(Long id,Boolean isEnabled);

    UserDto deletedUserById(Long id);
}
