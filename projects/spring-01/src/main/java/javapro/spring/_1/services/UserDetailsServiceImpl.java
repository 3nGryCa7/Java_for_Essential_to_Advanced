package javapro.spring._1.services;

import javapro.spring._1.models.AppUser;
import javapro.spring._1.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = appUserRepository.findByUsername(username);
        return optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username));
    }
}