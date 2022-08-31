package bg.codeacademy.spring.gossiptalks.repositories;

import bg.codeacademy.spring.gossiptalks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
  User getByUsername(String username);

  List<User> findAll();
}
