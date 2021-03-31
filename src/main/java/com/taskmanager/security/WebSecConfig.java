package com.taskmanager.security;

import com.taskmanager.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   @Autowired
   private UserDetailServiceImpl userDetailService;

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailService)
            .passwordEncoder(bCryptPasswordEncoder);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.
            authorizeRequests()
            .antMatchers("/", "/login", "/register").permitAll()
            .antMatchers("/tasks/**").hasAuthority("USER")
            .and().csrf().disable()
            .formLogin()
               .loginPage("/")
               .loginProcessingUrl("/login")
               .defaultSuccessUrl("/tasks/view_tasks")
               .failureUrl("/login")
               .usernameParameter("username")
               .passwordParameter("password")
            .and().logout()
               .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
               .logoutSuccessUrl("/").and().exceptionHandling();
   }

//   @Override
//   protected void registerAuthentication(AuthenticationManagerBuilder authManagerBuilder) {
//      authManagerBuilder
//            .inMemoryAuthentication()
//            .withUser("user")
//            .password("password")
//            .roles("USER");
//   }

   @Override
   public void configure(WebSecurity web) throws Exception{
      web
            .ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
   }
}
