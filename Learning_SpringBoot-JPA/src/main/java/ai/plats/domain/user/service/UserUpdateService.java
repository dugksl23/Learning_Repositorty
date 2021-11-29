package ai.plats.domain.user.service;

import ai.plats.domain.user.repository.UserRepository;
import ai.plats.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUpdateService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findUserByEmail(String email){
        return userRepository.findByUserEmail(email);
    }

    public Optional<User> findUserByIdx(String username) {
        return userRepository.findByIdxUser(Integer.parseInt(username));
    }

    public Optional<User> findUserByNick(String nick){
        return userRepository.findByUserNick(nick);
    }

    public User updateMyInfo(User user){
        return userRepository.save(user);
    }

    public String  changePwdLogic(String name, String ori_pwd, String new_pwd) {
        Optional<User> vo= userRepository.findByIdxUser(Integer.parseInt(name));
        if(passwordEncoder.matches(ori_pwd,vo.get().getUserPwd())){
            vo.get().setUserPwd(passwordEncoder.encode(new_pwd));

            System.out.println("비밀번호 변경 성공");
            userRepository.save(vo.get());
            return "변경 성공";

        }
        else{
            System.out.println("비밀번호 변경 실패  ");
            return "변경 실패";
        }

    }


    public void withDraw(User user){
        user.setWithDraw("Y");
        User vo = userRepository.save(user);

        if(vo!=null){
            System.out.println("회원탈퇴 처리 되었습니다.");
        }
        else{
            System.out.println("회원탈퇴 처리 실패 service");

        }
    }


}
