package com.blank.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * A system user.
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = 2002390446280945447L;

	@Column(unique = true)
	private String username;

	@Column
	private String password;

	@Transient
	private String uiPassword;

	@Transient
	private String verifyPassword;

	@Column(unique = true)
	private String email;

	@Column
	private String name;

	@Column(name = "account_expired")
	private boolean accountExpired = false;

	@Column(name = "account_locked")
	private boolean accountLocked = false;

	@Column(name = "credentials_expired")
	private boolean credentialsExpired = false;

	@Column
	private Boolean enabled = true;

	@Transient
	private Boolean passwordEncrypted = true;

	@Transient
	private Boolean verifyPasswordEncrypted = true;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Collection<Authority> authorities;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private UserProfile userProfile = new UserProfile();

	private byte[] avator;

	public User() {
		passwordEncrypted = false;
		verifyPasswordEncrypted = false;
	}

	public User(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUiPassword() {
		return uiPassword;
	}

	public void setUiPassword(String uiPassword) {
		this.uiPassword = uiPassword;
		setPassword(new BCryptPasswordEncoder().encode(uiPassword));
	}

	public String getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserProfile getUserProfile() {
		return userProfile == null ? new UserProfile() : userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public boolean hasAuthority(String targetAuthority) {
		if (targetAuthority == null) {
			return false;
		}
		if (authorities == null) {
		}

		for (Authority authority : authorities) {
			if (targetAuthority.equals(authority.getAuthority())) {
				return true;
			}
		}

		return false;
	}

	public void addAuthority(Authority authority) {
		if (authority == null) {
			return;
		}
		if (authorities == null) {
			authorities = new ArrayList<Authority>();
		}

		authorities.add(authority);
	}

	@Override
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public byte[] getAvator() {
		return avator;
	}

	public void setAvator(byte[] avator) {
		this.avator = avator;
	}

	public void validateCreateUser(ValidationContext context) {
		MessageContext messages = context.getMessageContext();
		if (!StringUtils.equals(uiPassword, verifyPassword)) {
			messages.addMessage(new MessageBuilder().error().source("password").source("verifyPassword")
					.defaultText("Passwords must be the same.").build());
		}
	}

	@Override
	public String toString() {
		return "User(" + username + ", " + email + ")";
	}
}
