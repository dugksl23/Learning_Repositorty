package ai.plats.domain.user.service;

import ai.plats.domain.user.repository.UserRepository;
import ai.plats.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientUpdateService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public Optional<User> findClientByEmail(String email){
        return userRepository.findByClientEmail(email);
    }

    public Optional<User> findClientByNick(String nick){

        return userRepository.findByNick(nick);
    }

    public User updateMyInfo(User user){
        return userRepository.save(user);
    }

    public String  changePwdLogic(String name, String ori_pwd, String new_pwd) {
        Optional<User> vo= userRepository.findByClientEmail(name);

        if(passwordEncoder.matches(ori_pwd,vo.get().getClientPwd())){
            vo.get().setClientPwd(passwordEncoder.encode(new_pwd));

            System.out.println("비밀번호 변경 성공");
            userRepository.save(vo.get());
            return "변경 성공";

        }
        else{
            System.out.println("비밀번호 변경 실패  ");
            return "변경 실패";
        }

    }
}
