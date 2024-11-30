package com.blog_app.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User implements UserDetails
{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int  user_id;


@Email(message = "invalid email")
@NotBlank(message = "filed can not be empty !!")
@Size(min=5,message = "email size min. 5")
@Column(name = "Email", nullable = false,unique =true)
private String email;

//min=3,max = 12
@NotBlank(message = "filed can not be empty !!")
@Size( message = "password must be between 3 to 12 charaters !!")
@Column(name = "Password")
private String password;

@NotBlank(message = "filed can not be empty !!")
@Size(min=3,max = 8,message = "name should i between 3 and 8")
@Column(name = "name")
private String name;
	
@OneToMany(mappedBy ="user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)	
@JsonIgnore
private List<Post>post=new ArrayList<>();	
	
@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
@JoinTable(name = "user_role"
,joinColumns=@JoinColumn(name = "user",referencedColumnName ="user_id")
,inverseJoinColumns =@JoinColumn(name="role",referencedColumnName = "roleId") )
private Set<Roles>roles=new HashSet<>();


@Override
public Collection<? extends GrantedAuthority> getAuthorities() {

	List<SimpleGrantedAuthority> authorities = this.roles.stream().map(role->new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());
	
	return authorities;
}

@Override
public String getUsername() {
	
	return this.getEmail();
}

@Override
public boolean isAccountNonExpired() {
	
	return true;
}

@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
}




}
