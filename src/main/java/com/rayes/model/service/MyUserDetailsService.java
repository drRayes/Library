package com.rayes.model.service;

import com.rayes.model.entity.Person;
import com.rayes.model.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom user details service.
 *
 * @author rayes
 */

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Person person = null;
		List<Role> roles = null;
		try(Session session = sessionFactory.openSession();) {

			person = (Person) session.createQuery(
					"FROM Person WHERE login = '" + username + "'").uniqueResult();
			roles =  session.createQuery(
					"FROM Role WHERE login ='" + username + "'").list();

		}

		List<GrantedAuthority> authority = buildUserAuthority(roles);

		return buildUserForAuthentication(person, authority);
	}

	private User buildUserForAuthentication(Person person, List<GrantedAuthority> authorities) {
		return new User(person.getLogin(), person.getPassword(), person.getStatus(), true,
				true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<Role> userRoles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
		for(Role role : userRoles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return authorities;
	}

}