package com.org.media.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {
    @Bean
    public UserDetailsService userDetailsServic(PasswordEncoder encoder){
//        UserDetails admin = User.withUsername("Ankit")
//                .password(encoder.encode("pwd1"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("Vibhu")
//                .password(encoder.encode("pwd2"))
//                .roles("ADMIN")
//                .build();
//        return  new InMemoryUserDetailsManager(admin,user);

        return new UserInfoUserDetailsService();
   }

   @Bean
   public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
   }

   @Bean
   public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return   http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeRequests()
//                .requestMatchers("/api/welcome").permitAll()
//                .and()
//                .authorizeRequests().requestMatchers("/api/**")
//                .authenticated()
//                .and().formLogin(AbstractAuthenticationFilterConfigurer::permitAll).build();

       return http.authorizeHttpRequests(request -> request
                       .requestMatchers("/api/welcome").permitAll()
                       .requestMatchers("/api/**", "/user/**").authenticated()
                       .requestMatchers("/admin/**").hasRole("ADMIN")
                       .anyRequest().authenticated())
               .httpBasic(Customizer.withDefaults())
               .csrf(AbstractHttpConfigurer::disable)
               .build();

   }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(new UserInfoUserDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;

    }


}
