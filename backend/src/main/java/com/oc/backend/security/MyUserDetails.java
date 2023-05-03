package com.oc.backend.security;
import com.oc.backend.model.User;
import com.oc.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class MyUserDetails implements UserDetailsService {

  private final UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final User User = userRepository.findByEmail(email);

    if (User == null) {
      throw new UsernameNotFoundException("User '" + email + "' not found");
    }

    return org.springframework.security.core.userdetails.User//
      .withUsername(email)//
      .password(User.getPassword())//
      .accountExpired(false)//
      .accountLocked(false)//
      .credentialsExpired(false)//
      .disabled(false)//
      .build();
  }
}
