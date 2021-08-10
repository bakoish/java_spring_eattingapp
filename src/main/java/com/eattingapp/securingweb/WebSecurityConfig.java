package com.eattingapp.securingweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/page","/contact","/css/**","/img/**","/js/**","/navbar/**").permitAll()
                .antMatchers("/error404/**","/error/**").permitAll()
                // DOREJESTRACJI
                .antMatchers( "/registration").permitAll()
                // *************
                .antMatchers("/user/**").hasAnyAuthority("USER")
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/dishes/**").hasAnyAuthority("ADMIN")
                .antMatchers("/dish/**").hasAnyAuthority("ADMIN")
                .antMatchers("/update/**").hasAnyAuthority("ADMIN")
                .antMatchers("/updatepersonaladress/**").hasAnyAuthority("USER")
                .antMatchers("/personaladress/**").hasAnyAuthority("USER")
                .antMatchers("/order/**").hasAnyAuthority("USER")


                .and()

                .formLogin()
                .defaultSuccessUrl("/default", true)
                .loginPage("/login")
                .permitAll()
                .and()


                .logout()
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");

        http.csrf().disable(); //zeby dostac sie do /console - usunac na koniec
        http.headers().frameOptions().disable(); //zeby dostac sie do /console - usunac na koniec
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("ADMIN")
                        .authorities("ADMIN")
                        .build();

        UserDetails user2 =
                User.withDefaultPasswordEncoder()
                        .username("user2")
                        .password("password2")
                        .roles("USER")
                        .authorities("USER")
                        .build();

        return new InMemoryUserDetailsManager(user,user2);
    }
}



