package istad.co.readingbookwev5api.api.user.web;

import istad.co.readingbookwev5api.api.book.web.SaveBookDto;
import istad.co.readingbookwev5api.api.user.User;
import istad.co.readingbookwev5api.api.user.UserMapper;
import istad.co.readingbookwev5api.api.user.UserServerImpl;
import istad.co.readingbookwev5api.config.security.CustomUserSecurity;
import istad.co.readingbookwev5api.shared.rest.Rest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserServerImpl userServiceImpl;
    private final UserMapper userMapper;


    @Value("${file.uri}")
    private String fileBaseUri;

    @GetMapping("/me")
    ResponseEntity<?> getCurrentUser(Authentication authentication){
        CustomUserSecurity userSecurity = (CustomUserSecurity) authentication.getPrincipal();

        User user = userSecurity.getUser();
        UserDto userDto = userMapper.toUserDto(user);
        userDto.getProfile().buildNameAndUri(fileBaseUri);

        Rest<Object> rest = Rest.ok().setData(userDto).setMessage("Retrieving profile information successfully!");
        return ResponseEntity.ok(rest);
    }

    @GetMapping
    ResponseEntity<?> getAllUsers(){

        List<UserDto> userDtoList = userServiceImpl.getAllUsers();

        Rest<List<UserDto>> rest = new Rest<>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("User have been fetched");
        rest.setData(userDtoList);

        return ResponseEntity.ok(rest);
    }
    @GetMapping("/{id}")
    ResponseEntity<?> getUserById(@PathVariable("id")Long id) {

        UserDto userDto = userServiceImpl.getUserById(id);
        Rest<Object> rest = Rest.ok().setData(userDto).setMessage("User has been fetched successfully.");

        return ResponseEntity.ok(rest);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> doUpdateUserById(@PathVariable("id")Long id, @RequestBody SaveBookDto saveBookDto){
        return ResponseEntity.ok(saveBookDto);
    }

    @PutMapping("/{id}/enable-user-status")
    ResponseEntity<?> enableOrDisableUserById(@PathVariable("id")Long id,@RequestBody isEnabledDto isEnabledDto){

        UserDto userDto = userServiceImpl.enableAndDisableUser(id,isEnabledDto.getStatus());

        Rest<Object> rest =  Rest.ok()
                .setData(userDto)
                .setMessage(isEnabledDto.getStatus()? String.format("User Id = %d has been enabled.", id) : String.format("user Id = %d has been disabled.",id));
        return ResponseEntity.ok(rest);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUserById(@PathVariable("id")Long id){
        UserDto userDto = userServiceImpl.deletedUserById(id);

        Rest<Object> rest = Rest.ok()
                .setData(userDto)
                .setMessage("User has been deleted permanently.");

        return ResponseEntity.ok(rest);
    }

}
