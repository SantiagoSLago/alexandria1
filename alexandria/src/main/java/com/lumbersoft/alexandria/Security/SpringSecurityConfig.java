package com.lumbersoft.alexandria.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity()
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {




    /* //Save Secure
      @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .httpBasic()
                .and().authorizeHttpRequests()
//                .requestMatchers("/menu/ver","/pedidos/altaPedido","/pedidos/pedidoExitoso/{id}",
//                        "/css/*","/js/*","/img/*").permitAll()
                .requestMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll().
                 and().csrf().disable()
                .build();
    }

     */

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .httpBasic()
                .and().authorizeHttpRequests()
//                .requestMatchers("/menu/ver","/pedidos/altaPedido","/pedidos/pedidoExitoso/{id}",
//                        "/css/*","/js/*","/img/*").permitAll()
                .requestMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin(form -> form.loginPage("/log").permitAll()
                        .loginProcessingUrl("/logProcess")
                        .defaultSuccessUrl("/admin/home")).

                 csrf().disable()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();//encoder no apto para produccion
    }
}
