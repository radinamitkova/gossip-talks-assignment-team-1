package bg.codeacademy.spring.gossiptalks.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

@Entity
@Table(name = "users")
public class User
{
  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  @NotNull
  @Pattern(regexp = "^[a-z0-8\\.\\-]+$")
  private String username;

  @Column(unique = true)
  @NotNull
  @Email
  private String email;

  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<User> friends;


  @OneToMany(fetch = FetchType.EAGER)
  private List<Gossip> gossips;

  @NotNull
  private String password;

  private Integer gossipCount;

  public User()
  {
  }

  public User(Long id, String username, String email, String name, Set<User> friends, List<Gossip> gossips, String password)
  {
    this.id = id;
    this.username = username;
    this.email = email;
    this.name = name;
    this.friends = friends;
    this.gossips = gossips;
    this.password = password;
  }

  public User(String username, String email, String name, Set<User> friends, List<Gossip> gossips,
              String password)
  {
    this.username = username;
    this.email = email;
    this.name = name;
    this.friends = friends;
    this.gossips = gossips;
    this.password = password;
  }

  public Set<User> getFriends()
  {
    return friends;
  }

  public User setFriends(Set<User> friends)
  {
    this.friends = friends;
    return this;
  }

  public Long getId()
  {
    return id;
  }

  public User setId(Long id)
  {
    this.id = id;
    return this;
  }

  public String getUsername()
  {
    return username;
  }

  public User setUsername(String username)
  {
    this.username = username;
    return this;
  }

  public String getEmail()
  {
    return email;
  }

  public User setEmail(String email)
  {
    this.email = email;
    return this;
  }

  public String getName()
  {
    if (name == null) {
      return "";
    }
    return name;
  }

  public User setName(String name)
  {
    this.name = name;
    return this;
  }


  public String getPassword()
  {
    return password;
  }

  public User setPassword(String password)
  {
    this.password = password;
    return this;
  }

  public List<Gossip> getGossips()
  {
    return gossips;
  }

  public User setGossips(List<Gossip> gossips)
  {
    this.gossips = gossips;
    return this;
  }


  public Integer getGossipCount()
  {
    if(gossips==null) return 0;
    return gossips.size();
  }

  public void addFriend(User friend)
  {
    List<String> usernames = new ArrayList<>();
    for (User aFriend : this.friends) {
      usernames.add(aFriend.getUsername());
    }
    if (!usernames.contains(friend.getUsername())) {
      this.friends.add(friend);
    }
    else {
      System.out.println("Already following!");
    }
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getName(), user.getName()) && Objects.equals(getPassword(), user.getPassword());
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(getId(), getUsername(), getEmail(), getName(), getPassword());
  }
}
