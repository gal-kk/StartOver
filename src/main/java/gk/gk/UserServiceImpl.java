package gk.gk;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserIdGen userIdGen;

    @Override
    public UserDto save(UserDto userDto) {

        UserEntity check = userRepository.findByEmail(userDto.getEmail());
        if(check!=null) throw new RuntimeException("Duplicated Email Account.");

        UserEntity userEntity = new UserEntity();
        userDto.setEmailVerificationStatus(true);

        userDto.setUserId(userIdGen.userIdGen(20));

        userDto.setEmailVerificationToken("123132");

        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        BeanUtils.copyProperties(userDto, userEntity);

        UserEntity userRe = userRepository.save(userEntity);


        return userDto;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(s);
        if (userEntity == null) throw new UsernameNotFoundException(s);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
