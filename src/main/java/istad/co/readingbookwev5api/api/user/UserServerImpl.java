package istad.co.readingbookwev5api.api.user;

import istad.co.readingbookwev5api.api.user.web.UserDto;
import istad.co.readingbookwev5api.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServerImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Value("${file.uri}")
    private String fileBaseUri;
    @Override
    public UserDto getUserByUsernameOrEmail(String usernameOrEmail) {

        User user = userRepository.selectWhereUsernameOrEmail(usernameOrEmail,true).orElseThrow(()->new UsernameNotFoundException("User is not found!"));

        UserDto userDto = userMapper.toUserDto(user);
        userDto.getProfile().buildNameAndUri(fileBaseUri);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.select();

        List<UserDto> userDtoList = userMapper.toListDto(users);
        userDtoList.forEach(userDto -> userDto.getProfile().buildNameAndUri(fileBaseUri));
        return userDtoList;
    }

    @Override
    public UserDto getUserById(Long id) {

        User user = userRepository.selectWhereId(id).orElseThrow(()->new UsernameNotFoundException("User is not found!"));
        UserDto userDto = userMapper.toUserDto(user);
        userDto.getProfile().buildNameAndUri(fileBaseUri);
        return userDto;
    }

    @Override
    public boolean checkUserEmail(String email) {
        return userRepository.existsWhereEmail(email);
    }

    @Override
    public boolean checkUsername(String username) {
        return userRepository.existsWhereUsername(username);
    }

    @Override
    public UserDto enableAndDisableUser(Long id, Boolean isEnabled) {

        User user = userRepository.selectWhereId(id).orElseThrow(()->new UsernameNotFoundException("User is not found!!"));
        userRepository.updateIsEnabledWhereId(id,isEnabled);

        UserDto userDto = userMapper.toUserDto(user);
        userDto.getProfile().buildNameAndUri(fileBaseUri);
        return userDto;
    }

    @Override
    public UserDto deletedUserById(Long id) {

        User user = userRepository.selectWhereId(id).orElseThrow(()->new UsernameNotFoundException("User is not found!"));

        userRepository.deleteWhereId(id);

        UserDto userDto = userMapper.toUserDto(user);
        userDto.getProfile().buildNameAndUri(fileBaseUri);

        return userDto;
    }
}
