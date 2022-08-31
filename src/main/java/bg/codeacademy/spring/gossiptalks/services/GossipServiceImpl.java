package bg.codeacademy.spring.gossiptalks.services;

import bg.codeacademy.spring.gossiptalks.model.Gossip;
import bg.codeacademy.spring.gossiptalks.model.User;
import bg.codeacademy.spring.gossiptalks.repositories.GossipRepository;
import bg.codeacademy.spring.gossiptalks.security.AppUser;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class GossipServiceImpl implements GossipService
{

  private final GossipRepository gossipRepository;

  public GossipServiceImpl(GossipRepository gossipRepository)
  {
    this.gossipRepository = gossipRepository;

  }

  @Override
  public Page<Gossip> getCurrentUserFeed(AppUser currentUser, Integer pageNo, Integer pageSize)
  {
    ArrayList<Gossip> feed = new ArrayList<>();
    Pageable cumulativeRequest = PageRequest.of(0, (pageNo + 1) * pageSize, Sort.by("date").descending());
    long feedCount = 0;

    for (User u : currentUser.getUser().getFriends()) {
      feedCount += getCountByUser(u);
      feed.addAll(gossipRepository.getByUser(cumulativeRequest, u).toList());
    }

    feed.sort(Comparator.comparing(Gossip::getDate).reversed());

    /* return empty array if page range too high*/
    return new PageImpl<>(
        pageNo * pageSize > feed.size() ? new ArrayList<>() :
            feed.subList(pageNo * pageSize, Math.min(feed.size(), (pageNo + 1) * pageSize)),
        PageRequest.of(pageNo, pageSize), feedCount);
  }

  @Override
  public Page<Gossip> getByUser(Pageable pageable, User user)
  {
    return gossipRepository.getByUser(pageable, user);
  }

  @Override
  public Gossip insert(Gossip gossip)
  {
    return gossipRepository.save(gossip);
  }

  @Override
  public Long getCountByUser(User user)
  {
    return gossipRepository.countByUser(user);
  }

}
