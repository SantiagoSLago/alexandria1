package com.lumbersoft.alexandria.servicios;

import com.lumbersoft.alexandria.Security.SecurityUser;
import com.lumbersoft.alexandria.repositorios.RepositorioUser;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final RepositorioUser repositorioUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var optUser = this.repositorioUser.findByUsername(username);

        if(optUser.isPresent()){

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attributes.getRequest().getSession(true);

            session.setAttribute("userSession", optUser.get());

            return new SecurityUser(optUser.get());

        }

        throw new UsernameNotFoundException("User not found: " + username);


    }
}
