package bg.codeacademy.spring.gossiptalks.services;

import bg.codeacademy.spring.gossiptalks.model.User;

import java.util.List;

public interface UserService {

  List<User> getAll();

  User getByUsername(String username);

  User save(User user);

  void updateFriendList(User user);

  User create(String email, String username, String name, String password)
      throws Exception;

}
