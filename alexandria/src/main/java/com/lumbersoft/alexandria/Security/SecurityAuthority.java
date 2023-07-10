package com.lumbersoft.alexandria.Security;

import com.lumbersoft.alexandria.entidades.SecurityEntities.Authorities;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    private final Authorities authority;

    @Override
    public String getAuthority() {
        return authority.getName().toString();
    }
}
