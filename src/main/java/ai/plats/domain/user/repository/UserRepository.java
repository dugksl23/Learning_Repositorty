package ai.plats.domain.user.repository;


import ai.plats.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    //id 는 pk의 형식

    Optional<User> findByUserEmail(String email);

    Optional<User> findByUserNick(String nick);

    Optional<User> findByUserEmailAndWithDraw(String email, String withDraw);

}
