
package bg.codeacademy.spring.gossiptalks.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http
        .csrf().disable()
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authorizeRequests()
            .mvcMatchers(HttpMethod.GET, "/api/v1/users").authenticated()
            .mvcMatchers(HttpMethod.POST, "/api/v1/users/").permitAll()
            .mvcMatchers("/api/v1/users/me").authenticated()
            .antMatchers("api/v1/users/{username}/follow").authenticated()
            .mvcMatchers("api/v1/users/{username}/gossips").authenticated()
            .mvcMatchers(("api/v1/gossips")).authenticated()
            .anyRequest().authenticated()
        .and().httpBasic();
  }

  @Override
  public void configure(WebSecurity web) throws Exception
  {
    web
        .ignoring()
        .antMatchers("/h2/**");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  PasswordEncoder passwordEncoder()
  {
    return new BCryptPasswordEncoder();
  }

}


