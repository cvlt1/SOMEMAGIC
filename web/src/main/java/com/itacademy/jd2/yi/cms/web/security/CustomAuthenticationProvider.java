package com.itacademy.jd2.yi.cms.web.security;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		
		final String username = authentication.getPrincipal() + "";
		final String password = authentication.getCredentials() + "";
		
		IUserAccount userAccount = userAccountService.findNickname(username);

	
		
		

		if (!userAccount.getName().equals(username)) {
			throw new BadCredentialsException("1000");
		}
		
		if (!BCrypt.checkpw(password, userAccount.getPassword())) {
			throw new BadCredentialsException("1000");
		}
		// TODO verify password (DB contains hash - not a plain password)
//		if (!hashedPassword.equals(password)) {
//			throw new BadCredentialsException("1000");
//		}
		final int userId = userAccount.getId();
		 // FIXME: it should be the real user id from DB

		UserRole role = userAccount.getRole();

		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

		ExtendedToken token = new ExtendedToken(username, password, authorities);
		token.setId(userId);
		return token;

	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
