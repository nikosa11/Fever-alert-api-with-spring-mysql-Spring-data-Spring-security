package com.example.TempApi.config.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import com.example.TempApi.config.CustomerDetails;
import com.example.TempApi.config.CustomerDetailsService;

@Component
public class JwtFilter extends GenericFilterBean {

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private CustomerDetailsService customerDetailsService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("do filter");
		String token = getTokenFromRequest((HttpServletRequest) request);
		if (token != null && jwtProvider.validateToken(token)) {
			String userLogin = jwtProvider.getLoginFromToken(token);
			CustomerDetails customerDetails = customerDetailsService.loadUserByUsername(userLogin);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customerDetails, null);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		chain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearer = request.getHeader("Authorization");
		if (!bearer.isEmpty() && bearer.startsWith("Beare ")) {
			return bearer.substring(7);

		}
		return null;
	}

}
