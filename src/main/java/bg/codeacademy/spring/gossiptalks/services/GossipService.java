package bg.codeacademy.spring.gossiptalks.services;

import bg.codeacademy.spring.gossiptalks.model.Gossip;
import bg.codeacademy.spring.gossiptalks.model.User;
import bg.codeacademy.spring.gossiptalks.security.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GossipService
{
  Page<Gossip> getCurrentUserFeed(AppUser currentUser, Integer pageNo, Integer pageSize);

  Page<Gossip> getByUser(Pageable pageable, User user);

  Gossip insert(Gossip gossip);

  Long getCountByUser(User user);
}
