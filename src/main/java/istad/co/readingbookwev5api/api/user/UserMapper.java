package istad.co.readingbookwev5api.api.user;

import istad.co.readingbookwev5api.api.user.web.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserDto> toListDto(List<User> users);

    UserDto toUserDto(User user);

    User toUserModel(UserDto userDto);
}
