package com.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog_app.models.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer>{

}
