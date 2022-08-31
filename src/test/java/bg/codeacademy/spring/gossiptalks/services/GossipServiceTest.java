package bg.codeacademy.spring.gossiptalks.services;

import bg.codeacademy.spring.gossiptalks.model.Gossip;
import bg.codeacademy.spring.gossiptalks.model.User;
import bg.codeacademy.spring.gossiptalks.repositories.GossipRepository;
import bg.codeacademy.spring.gossiptalks.security.AppUser;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;

import static org.testng.Assert.*;

@SpringBootTest
public class GossipServiceTest extends AbstractTransactionalTestNGSpringContextTests
{
  @Mock
  private       GossipRepository  gossipRepository;
  private       GossipServiceImpl gossipService;
  private final PageRequest       pageRequest = PageRequest.of(0, 3);
  private       User              user1;
  private       User              user2;
  private       Page<Gossip>      gossips;


  @BeforeClass
  public void beforeClass()
  {
    MockitoAnnotations.openMocks(this);
    user1 = new User(1L, "username", "email@gmail.com", "name", null, null, "password");
    user2 = new User(2L, "userTwo", "second@gmail.com", "second name", null, null, "password2");
    user1.setFriends(new HashSet<>(Arrays.asList(user2)));
    user1.setFriends(new HashSet<>(Arrays.asList(user1)));
    gossips = new PageImpl<>(Arrays.asList(
        new Gossip(1L, "some gossip", user1, Calendar.getInstance().getTime()),
        new Gossip(1L, "another gossip", user1, Calendar.getInstance().getTime()),
        new Gossip(1L, "yet another gossip", user1, Calendar.getInstance().getTime())
    ));
    gossipService = new GossipServiceImpl(gossipRepository);
  }

  @BeforeTest
  public void setUp()
  {
  }

  @AfterMethod
  public void tearDown()
  {
    Mockito.clearInvocations(gossipRepository);
  }

  @Test
  public void getCurrentUserFeed_WithValidParameters_ShouldReturnGossipFeed()
  {
    Mockito.when(gossipRepository.getByUser(Mockito.any(Pageable.class), Mockito.any(User.class)))
        .thenReturn(gossips);
    Page<Gossip> page = gossipService.getCurrentUserFeed(new AppUser(user1),pageRequest.getPageNumber(), pageRequest.getPageSize());
    assertEquals(page.getNumber(), pageRequest.getPageNumber());
    assertEquals(page.getSize(), pageRequest.getPageSize());
    assertEquals(page.getSort(), pageRequest.getSort());
    assertTrue(page.stream().allMatch(g->user1.getFriends().contains(g.getUser())));

  }

  @Test
  public void getByUser_IfUserExists_ShouldReturnUserPage()
  {
    Mockito.when(gossipRepository.getByUser(Mockito.any(Pageable.class), Mockito.any(User.class)))
        .thenReturn(gossips);
    Page<Gossip> page = gossipService.getByUser(pageRequest, user1);

    Mockito.verify(gossipRepository, Mockito.times(1)).getByUser(pageRequest, user1);

    assertEquals(page.getNumber(), pageRequest.getPageNumber());
    assertEquals(page.getSize(), pageRequest.getPageSize());
    assertEquals(page.getSort(), pageRequest.getSort());
    assertEquals(page.stream().filter(gossip -> gossip.getUser().getUsername().equals(user1.getUsername())).count(), 3);
    assertTrue(gossips.toList().containsAll(page.toList()));
  }

  @Test
  void insertGossip_WithValidParameter_ShouldReturnGossip()
  {
    Gossip gossip = new Gossip(null, "gossiping some gossips", user1, Calendar.getInstance().getTime());
    assertNull(gossip.getId());
    Mockito.when(gossipRepository.save(Mockito.any(Gossip.class)))
        .thenReturn(gossip.setId(5L));
    gossip = gossipService.insert(gossip);
    assertNotNull(gossip.getId());
    Mockito.verify(gossipRepository, Mockito.times(1)).save(Mockito.any(Gossip.class));
  }

  @Test
  void getCountByUser_IfUserGossipsExist_ShouldReturnCount()
  {
    Mockito.when(gossipRepository.countByUser(Mockito.any(User.class)))
        .thenReturn(gossips.stream().filter(g -> g.getUser().getId().equals(user1.getId())).count());

    Long count = gossipService.getCountByUser(user1);

    Mockito.verify(gossipRepository, Mockito.times(1)).countByUser(user1);
    assertEquals(count, Long.valueOf(3));
  }

  @Test
  void getCountByUser_IfUserGossipsDoNotExist_ShouldReturnZero()
  {
    Mockito.when(gossipRepository.countByUser(Mockito.any(User.class)))
        .thenReturn(gossips.stream().filter(g -> g.getUser().getId().equals(user2.getId())).count());

    Long count = gossipService.getCountByUser(user1);

    Mockito.verify(gossipRepository, Mockito.times(1)).countByUser(user1);
    assertEquals(count, Long.valueOf(0));
  }
}