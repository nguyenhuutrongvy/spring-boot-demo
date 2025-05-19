package com.vynht.springboot.demosecurity.security;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

  /*@Bean
  public InMemoryUserDetailsManager userDetailsManager() {
    UserDetails john = User.builder()
        .username("john")
        .password("{noop}test123")
        .roles("EMPLOYEE")
        .build();

    UserDetails mary = User.builder()
        .username("mary")
        .password("{noop}test123")
        .roles("EMPLOYEE", "MANAGER")
        .build();

    UserDetails susan = User.builder()
        .username("susan")
        .password("{noop}test123")
        .roles("EMPLOYEE", "MANAGER", "ADMIN")
        .build();

    return new InMemoryUserDetailsManager(john, mary, susan);
  }*/

  // Add support for JDBC
  @Bean
  public UserDetailsManager userDetailsManager(DataSource dataSource) {
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

    // Define query to retrieve a user by username
    jdbcUserDetailsManager.setUsersByUsernameQuery(
        "select user_id, pw, active from members where user_id=?");

    // Define query to retrieve the authorities/roles by username
    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
        "select user_id, role from roles where user_id=?");

    return jdbcUserDetailsManager;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            configure -> configure
                .requestMatchers("/").hasRole("EMPLOYEE")
                .requestMatchers("/leaders/**").hasRole("MANAGER")
                .requestMatchers("/systems/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated())
        .formLogin(
            form -> form
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll())
        .logout(logout -> logout.permitAll())
        .exceptionHandling(configure -> configure.accessDeniedPage("/access-denied"));

    return http.build();
  }
}
