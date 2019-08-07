package gk.gk;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UserIdGen {
    public  String sample="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public  String userIdGen(int o){
        StringBuilder stringBuilder = new StringBuilder(o);
        Random random = new Random();
        for (int i= 0; i<o; i++){
            stringBuilder.append(sample.charAt(random.nextInt(sample.length())));
        }

        return stringBuilder.toString();
    }
}
