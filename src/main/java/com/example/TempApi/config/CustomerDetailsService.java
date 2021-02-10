package com.example.TempApi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.example.TempApi.Model.User;
import com.example.TempApi.Services.UserServices;

@Component
public class CustomerDetailsService implements UserDetailsService {

	@Autowired
	private UserServices userServices;

	@Override
	public CustomerDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userServices.findUserByUserName(username);

		return CustomerDetails.fromUserEntityToCustomerDetails(user);
	}

}
