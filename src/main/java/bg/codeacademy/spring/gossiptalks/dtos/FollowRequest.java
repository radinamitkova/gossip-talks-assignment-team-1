package bg.codeacademy.spring.gossiptalks.dtos;

public class FollowRequest
{
  private Boolean follow;

  public Boolean isFollow()
  {
    return follow;
  }

  public FollowRequest setFollow(Boolean follow)
  {
    this.follow = follow;
    return this;
  }
}
