package ai.plats.domain.user.service;

import ai.plats.domain.user.repository.UserRepository;
import ai.plats.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientJoinService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User joinClient(User userVO) {

        userVO.setClientPwd(passwordEncoder.encode(userVO.getClientPwd()));  //평문 암호화

       return userRepository.save(userVO);
    }


    public Optional<User> findClientByEmail(String email){

        return userRepository.findByClientEmail(email);
    }
}
