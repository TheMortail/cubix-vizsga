package hu.cubix.spring.gyuri.logistics.security;

import hu.cubix.spring.gyuri.logistics.model.TPUser;
import hu.cubix.spring.gyuri.logistics.repository.TPUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TPUserDetailService implements UserDetailsService {

    @Autowired
    private TPUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TPUser user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(username, user.getPassword(), user.getRoles().stream().map(SimpleGrantedAuthority::new).toList());
    }
}
