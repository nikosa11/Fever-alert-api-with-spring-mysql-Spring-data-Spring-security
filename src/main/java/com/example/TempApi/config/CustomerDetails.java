package com.example.TempApi.config;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.TempApi.Model.User;

public class CustomerDetails implements UserDetails {
	private String login;
	private String password;

	public static CustomerDetails fromUserEntityToCustomerDetails(User user) {
		CustomerDetails c = new CustomerDetails();
		c.login = user.getUserName();
		c.password = user.getPassword();
		return c;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
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

}
