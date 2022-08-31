package bg.codeacademy.spring.gossiptalks.services;

import bg.codeacademy.spring.gossiptalks.model.User;
import bg.codeacademy.spring.gossiptalks.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService
{
  UserRepository  userRepository;
  PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder)
  {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public List<User> getAll()
  {
    return userRepository.findAll();
  }

  @Override
  public User save(User user)
  {
    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User getByUsername(String username)
  {
    return userRepository.getByUsername(username);
  }

  @Override
  public void updateFriendList(User user)
  {
    userRepository.save(user);
  }

  @Override
  public User create(String email, String username, String name, String password) throws Exception
  {
    User dbUser = this.getByUsername(username);
    User user = new User();

    if (dbUser == null) {
      user.setEmail(email);
      user.setUsername(username);
      user.setName(name);
      user.setPassword(this.passwordEncoder.encode(password));

      return userRepository.save(user);
    }

    throw new Exception("Failed - the user already exists.");
  }
}
