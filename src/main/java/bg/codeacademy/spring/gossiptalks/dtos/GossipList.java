package bg.codeacademy.spring.gossiptalks.dtos;

import bg.codeacademy.spring.gossiptalks.model.Gossip;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class GossipList
{
  private Integer              pageNumber;
  private Integer              pageSize;
  private Integer              count;
  private Long              total;
  private List<GossipResponse> content;

  public Integer getPageNumber()
  {
    return pageNumber;
  }

  public GossipList setPageNumber(Integer pageNumber)
  {
    this.pageNumber = pageNumber;
    return this;
  }

  public Integer getPageSize()
  {
    return pageSize;
  }

  public GossipList setPageSize(Integer pageSize)
  {
    this.pageSize = pageSize;
    return this;
  }

  public Integer getCount()
  {
    return count;
  }

  public GossipList setCount(Integer count)
  {
    this.count = count;
    return this;
  }

  public Long getTotal()
  {
    return total;
  }

  public GossipList setTotal(Long total)
  {
    this.total = total;
    return this;
  }

  public List<GossipResponse> getContent()
  {
    return content;
  }

  public GossipList setContent(List<GossipResponse> content)
  {
    this.content = content;
    return this;
  }

  public static GossipList createGossipListFromPage(Page<Gossip> gossipPage)
  {
    return new GossipList()
        .setPageNumber(gossipPage.getNumber())
        .setPageSize(gossipPage.getSize())
        .setCount(gossipPage.getNumberOfElements())
        .setTotal(gossipPage.getTotalElements())
        .setContent(gossipPage.toList().stream().map(GossipResponse::mapEntityToDto).collect(Collectors.toList()));
  }
}