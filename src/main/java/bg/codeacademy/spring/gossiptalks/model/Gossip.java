package bg.codeacademy.spring.gossiptalks.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Gossip
{
  @Id
  @GeneratedValue
  private Long id;
  @Size(max = 255)
  private String text;

  @ManyToOne
  private User user;
  @DateTimeFormat
  private Date date;


  public Gossip()
  {
  }

  public Gossip(Long id, String text, User user, Date date)
  {
    this.id = id;
    this.text = text;
    this.user = user;
    this.date = date;
  }

  public Long getId()
  {
    return id;
  }

  public Gossip setId(Long id)
  {
    this.id = id;
    return this;
  }

  public String getText()
  {
    return text;
  }

  public Gossip setText(String text)
  {
    this.text = text;
    return this;
  }

  public User getUser()
  {
    return user;
  }

  public Gossip setUser(User user)
  {
    this.user = user;
    return this;
  }

  public Date getDate()
  {
    return date;
  }

  public Gossip setDate(Date date)
  {
    this.date = date;
    return this;
  }
}
