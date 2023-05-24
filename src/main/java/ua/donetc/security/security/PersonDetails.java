package ua.donetc.security.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ua.donetc.security.models.Peronsq;

import java.util.Collection;
import java.util.Collections;

public class PersonDetails implements UserDetails {
    private final Peronsq peronsq;

    public PersonDetails(Peronsq peronsq) {
        this.peronsq = peronsq;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //SHOW_ACCOUNT, WITHDREW_MONEY if will be authorities
        return Collections.singletonList(new SimpleGrantedAuthority(peronsq.getRole()));
    }

    @Override
    public String getPassword() {
        return this.peronsq.getPassword();
    }

    @Override
    public String getUsername() {
        return this.peronsq.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Peronsq getPerson() {
        return this.peronsq;
    }
}