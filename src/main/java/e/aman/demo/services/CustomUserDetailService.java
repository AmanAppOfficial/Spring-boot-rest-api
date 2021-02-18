package e.aman.demo.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if(username.equals("aman")) {
			return new User("aman" , "$a2ok" , new ArrayList<>());
		}
		else {
			System.out.print("not found username");
			throw new  UsernameNotFoundException("User not found");
		}
	}

}
