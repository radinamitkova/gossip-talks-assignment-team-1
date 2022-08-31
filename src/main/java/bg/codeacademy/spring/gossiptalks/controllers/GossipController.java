package bg.codeacademy.spring.gossiptalks.controllers;

import bg.codeacademy.spring.gossiptalks.dtos.CreateGossipRequest;
import bg.codeacademy.spring.gossiptalks.dtos.GossipList;
import bg.codeacademy.spring.gossiptalks.dtos.GossipResponse;
import bg.codeacademy.spring.gossiptalks.model.Gossip;
import bg.codeacademy.spring.gossiptalks.security.AppUser;
import bg.codeacademy.spring.gossiptalks.security.LoggedInUser;
import bg.codeacademy.spring.gossiptalks.services.GossipService;
import bg.codeacademy.spring.gossiptalks.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Calendar;

@RestController
@RequestMapping("/api/v1/gossips")
public class GossipController
{

  private final GossipService gossipService;
  private final UserService   userService;

  public GossipController(GossipService gossipService, UserService userService)
  {
    this.gossipService = gossipService;
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<GossipList> getCurrentUserFeed(@RequestParam(defaultValue = "0") Integer pageNo,
                                                       @RequestParam(defaultValue = "20") Integer pageSize,
                                                       @Valid @NotNull @LoggedInUser AppUser loggedUser)
  {
    Page<Gossip> feed = gossipService.getCurrentUserFeed(loggedUser,pageNo,pageSize);

    return new ResponseEntity<>(GossipList.createGossipListFromPage(feed), HttpStatus.OK);
  }

  @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<GossipResponse> createGossip(@Valid CreateGossipRequest request, Principal principal)
  {
    String username = principal.getName();
    Gossip gossip = new Gossip()
        .setText(request.getText())
        .setUser(userService.getByUsername(username))
        .setDate(Calendar.getInstance().getTime());

    gossipService.insert(gossip);
    return ResponseEntity.ok(GossipResponse.mapEntityToDto(gossip));
  }

}