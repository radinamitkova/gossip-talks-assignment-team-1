package bg.codeacademy.spring.gossiptalks.dtos;

import org.springframework.web.util.HtmlUtils;

import javax.validation.constraints.Size;

public class CreateGossipRequest
{
  @Size(max = 255)
  private String text;

  public String getText()
  {
    return text;
  }

  public CreateGossipRequest setText(String text)
  {
    text = text.replaceAll(" > "," &gt; ");
    text = text.replaceAll(" < "," &lt; ");
    if (HtmlUtils.htmlEscape(HtmlUtils.htmlUnescape(text)).equals(text)) {
      text = text.replaceAll(" &gt; "," > ");
      text = text.replaceAll(" &lt; "," < ");
      this.text = text;
      return this;
    }
    else {
      throw new IllegalArgumentException("Html entities not allowed!");
    }
  }
}
