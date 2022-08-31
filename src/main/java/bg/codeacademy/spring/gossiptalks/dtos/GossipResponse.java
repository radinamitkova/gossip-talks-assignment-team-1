package bg.codeacademy.spring.gossiptalks.dtos;

import bg.codeacademy.spring.gossiptalks.model.Gossip;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class GossipResponse
{
  @Size(max = 255)
  private String         text;
  @Pattern(regexp = "[A-Z0-9]+")
  private String         id;
  @Pattern(regexp = "^[a-z0-8\\.\\-]+$")
  private String         username;
  @DateTimeFormat
  private OffsetDateTime datetime;

  public String getText()
  {
    return text;
  }

  public GossipResponse setText(String text)
  {
    this.text = text;
    return this;
  }

  public String getId()
  {
    return id;
  }

  public GossipResponse setId(String id)
  {
    this.id = id;
    return this;
  }

  public String getUsername()
  {
    return username;
  }

  public GossipResponse setUsername(String username)
  {
    this.username = username;
    return this;
  }

  public OffsetDateTime getDatetime()
  {
    return datetime;
  }

  public GossipResponse setDatetime(OffsetDateTime datetime)
  {
    this.datetime = datetime;
    return this;
  }

  public static GossipResponse mapEntityToDto(Gossip gossip)
  {
    return new GossipResponse()
        .setText(gossip.getText())
        .setId(Long.toString(gossip.getId(), 36).toUpperCase())
        .setUsername(gossip.getUser().getUsername())
        .setDatetime(gossip.getDate().toInstant().atOffset(ZoneOffset.ofHours(3)));
  }
}
