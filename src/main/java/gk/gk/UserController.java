package gk.gk;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public String Getre(){
        return "Get Request Checked.";
    }

    @PostMapping("users")
    public UserDetail Postre(@RequestBody User userIn){
        UserDetail userDetail = new UserDetail();
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userIn, userDto);

        UserDto saved = userService.save(userDto);

        BeanUtils.copyProperties(saved, userDetail);

        return userDetail;
    }


}
