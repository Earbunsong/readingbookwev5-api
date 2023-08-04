package istad.co.readingbookwev5api.config.security;

import istad.co.readingbookwev5api.api.user.User;
import istad.co.readingbookwev5api.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServerImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.selectWhereUsernameOrEmail(usernameOrEmail,true)
                .orElseThrow(()-> new UsernameNotFoundException("User Not found!!!"));
        log.info("User = {}",user);

        CustomUserSecurity userSecurity = new CustomUserSecurity();
        userSecurity.setUser(user);

        return userSecurity;
    }
}
