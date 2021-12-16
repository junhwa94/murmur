package org.jjh.murmur.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;

	 private String username;
	 private String password;
	 private Boolean enabled;

	 @JsonIgnore
	 @ManyToMany
	 @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
	 	
	 private List<Role> roles = new ArrayList<>();

}
