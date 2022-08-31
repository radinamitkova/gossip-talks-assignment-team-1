package bg.codeacademy.spring.gossiptalks.repositories;

import bg.codeacademy.spring.gossiptalks.model.Gossip;
import bg.codeacademy.spring.gossiptalks.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GossipRepository extends PagingAndSortingRepository<Gossip, Long>
{
  Page<Gossip> getByUser(Pageable pageable, User user);

  long countByUser(User user);
}
