
package com.lumbersoft.alexandria;

import com.lumbersoft.alexandria.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb extends WebSecurityConfigurerAdapter{
    
    
    @Autowired
    public UsuarioService us;
    
    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(us)
        .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    
    
    
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/admin/*").hasRole("ADMIN")//Aca se setea la autorizacion del rol admin para poder acceder a todos los metodos del controlador de administrador( /admin/*)
                    .antMatchers("/css/*","/js/*","/img/*","/")//Fijarse de agregar el doble **
                    .permitAll()
                .and().formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/logincheck")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/inicio")
                    .permitAll()
                .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll()
        //Esto es algo que se vera mas adelante ( segun minuto 1:20 v6
        .and().csrf()
        .disable();
        
        
        
        
    }
    
    
}
