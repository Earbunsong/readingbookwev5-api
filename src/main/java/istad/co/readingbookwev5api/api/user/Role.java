package istad.co.readingbookwev5api.api.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Role implements GrantedAuthority {

    private Integer id;
    private String name;

    @Override
    public String getAuthority() {
        //All Roles must prefix with 'ROLE_'
        //Example -> Admin -> 'ROLE_ADMIN'
        return "ROLE_" + name;
    }
}
