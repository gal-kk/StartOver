package gk.gk;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    public UserDto save(UserDto userDto);
    public UserDto findUserByEmail(String email);
}
