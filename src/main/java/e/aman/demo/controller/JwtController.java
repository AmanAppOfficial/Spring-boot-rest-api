package e.aman.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import e.aman.demo.helper.JwtUtil;
import e.aman.demo.model.JWT;
import e.aman.demo.services.CustomUserDetailService;

@RestController
public class JwtController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailService customUserDetailsService; 
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value="/login" , method= RequestMethod.POST )
	public ResponseEntity<?> generateToken(@RequestBody JWT jwt){
		try {
		  this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwt.getUsername() , jwt.getPassword()));	
		}
		catch(BadRequest e) {
			System.out.print("Bad request");
		}
		
		
		UserDetails details = this.customUserDetailsService.loadUserByUsername(jwt.getUsername());
		
		String token = this.jwtUtil.generateToken(details);
		
		
		return ResponseEntity.ok(token);
		
	}
	
	
	
}
