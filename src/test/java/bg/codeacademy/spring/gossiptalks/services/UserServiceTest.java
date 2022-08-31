package bg.codeacademy.spring.gossiptalks.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import bg.codeacademy.spring.gossiptalks.model.Gossip;
import bg.codeacademy.spring.gossiptalks.model.User;
import bg.codeacademy.spring.gossiptalks.repositories.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserServiceTest extends AbstractTestNGSpringContextTests
{

  @Mock
  private UserRepository  userRepository;
  @Mock
  private PasswordEncoder passwordEncoder;

  private UserServiceImpl userService;

  @Mock
  private User user1, user2, user3;


  @BeforeClass
  public void BeforeClass()
  {

    MockitoAnnotations.openMocks(this);
    Set<User> friends = new HashSet<>();
    List<Gossip> gossips = new ArrayList<>();
    user1 = new User("user10", "user10.gmail.com", "User Ten", friends, gossips, "pass10");
    user2 = new User("user20", "user20.gmail.com", "User Twenty", friends, gossips, "pass20");
    user3 = new User("user30", "user30.gmail.com", "User Thirty", friends, gossips, "pass30");

    userService = new UserServiceImpl(userRepository, passwordEncoder);

  }


  @BeforeMethod
  public void setUp()
  {
  }

  @AfterMethod
  public void tearDown()
  {
  }


  @Test
  public void testSave()
  {
    String passBefore = user1.getPassword();
    Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
    User savedUser = userService.save(user1);

    assertNotEquals(savedUser.getPassword(),passBefore);
    assertNotNull(savedUser.getUsername());
  }

  @Test
  public void testGetByUsername()
  {
    String username = user2.getUsername();
    userRepository.save(user2);
    Mockito.when(userRepository.getByUsername(username)).thenReturn(user2);
    User fetchedUser = userService.getByUsername(username);

    assertEquals(user2, fetchedUser);
  }

  @Test
  public void testUpdateFriendList()
  {
    User loggedUser = user1;

    assertEquals(user1.getFriends().size(), 0);

    User friend = user3;
    loggedUser.addFriend(friend);

    Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
    userService.updateFriendList(loggedUser);

    assertTrue(loggedUser.getFriends().contains(friend));
    assertEquals(loggedUser.getFriends().size(), 1);
  }


  @Test
  public void getAll_ReturnsListOfUsers_IfOrderedByGossipCount()
  {
    List<User> users = new ArrayList<>();
    users.add(user1);
    users.add(user2);
    Mockito.when(userRepository.findAll()).thenReturn(users);
    List<User> expectedUsers = userService.getAll();

    assertEquals(users, expectedUsers);
  }

  @Test
  public void createUser_WithValidParameters() throws Exception {
    String email = user1.getEmail();
    String username = user1.getUsername();
    String name = user1.getName();
    String password = user1.getPassword();
    userRepository.save(user1);
    Mockito.when(userRepository.save(user1)).thenReturn(user1);
    User expectedUser = userService.create(user2.getEmail(),
        user2.getUsername(),user2.getName(),
        user2.getPassword());

    assertNotEquals(user1, expectedUser);
  }

}