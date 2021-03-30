package ai.plats.domain.account.service;


import ai.plats.domain.user.repository.UserRepository;
import ai.plats.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String clientEmail) throws UsernameNotFoundException {
        System.out.println("login try..");
        System.out.println("============>"+clientEmail.toString());

        List<GrantedAuthority> authorities=new ArrayList<>();

//        Optional<UserTable> voName = clientRepository.findByUsername(username);
            Optional<User> vo = userRepository.findByClientEmail(clientEmail);

        if(vo!=null){

            //이름으로 유저 가져오긴했는데 비밀번호는 어떻게 가져다가 비교할지 고민
            if (clientEmail.equals("admin")) {
                System.out.println("admin chk");
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }else {
                System.out.println("user chk");
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }

            System.out.println(">>"+vo);
            System.out.println("check value ->"+vo.get().getClientEmail() );
            return new org.springframework.security.core.userdetails.User(vo.get().getClientEmail(),vo.get().getClientPwd(),authorities);

        }else{
            System.out.println("유저없음");
            throw new UsernameNotFoundException("User not authorized.");
        }
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); //앞에 ROLE_을 붙여야 home.html에서 정상적으로 hasRole()구문을 인식




    }

}
