package com.gft.projeto.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import com.gft.projeto.enums.RoleName;

@SuppressWarnings("serial")
@Entity
public class Role implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private RoleName roleName;

	
	@Override
	public String getAuthority() {
		return this.roleName.toString();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public RoleName getRoleName() {
		return roleName;
	}


	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}
	
	
	
}
