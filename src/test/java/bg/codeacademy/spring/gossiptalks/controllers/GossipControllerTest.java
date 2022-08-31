package bg.codeacademy.spring.gossiptalks.controllers;

import bg.codeacademy.spring.gossiptalks.repositories.GossipRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({WithSecurityContextTestExecutionListener.class})
class GossipControllerTest extends AbstractTransactionalTestNGSpringContextTests
{
  @Autowired
  protected     MockMvc mockMvc;
  private final String  username = "USER";
  @Autowired
  private GossipRepository gossipRepository;

  @BeforeEach
  void setUp()
  {
  }

  @AfterEach
  void tearDown()
  {
  }

  @Test
  @WithMockUser(roles = "USER", username = username)
  void getCurrentUserFeed() throws Exception
  {
    gossipRepository.deleteAll();

    mockMvc.perform(get("/api/v1/gossips"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.pageNumber").value(0))
        .andExpect(jsonPath("$.pageSize").value(50))
        .andExpect(jsonPath("$.count").value(0))
        .andExpect(jsonPath("$.total").value(0))
        .andExpect(jsonPath("$.content").isArray())
        .andExpect(jsonPath("$.content",hasSize(0)));
  }

  @Test
  @WithMockUser(roles = "USER", username = username)
  void createGossip_ReturnsOk_IfUserAuthenticated() throws Exception
  {
    String gossipText = "some gossip";

    mockMvc.perform(multipart("/api/v1/gossips")
        .param("text", gossipText))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.text").value(gossipText))
        .andExpect(jsonPath("$.username").value(username))
        .andExpect(jsonPath("$.id").value("e"));
  }
}