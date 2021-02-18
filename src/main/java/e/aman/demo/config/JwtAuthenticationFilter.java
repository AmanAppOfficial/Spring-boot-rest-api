package e.aman.demo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import e.aman.demo.helper.JwtUtil;
import e.aman.demo.services.CustomUserDetailService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	
	
	@Autowired
	private CustomUserDetailService customUserDetailsService; 
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String auth = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		if(auth!=null && auth.startsWith("Bearer ")) {
			jwtToken = auth.substring(7);
			try {
				username = this.jwtutil.getUsernameFromToken(jwtToken);
			}
			catch(Exception e) {
				System.out.print("Null user");
			}
			
			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
			
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
				
				System.out.println("yes");
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
				
			}
			else {
				System.out.println("no");
			}
			
			
			
			
			
		}
		else {
			System.out.println("auth null");
		}
		
		
		filterChain.doFilter(request, response);
		
		
		
	}

}
