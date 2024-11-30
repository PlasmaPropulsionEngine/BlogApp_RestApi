package com.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog_app.models.Comments;

public interface CommentsRepository extends JpaRepository<Comments,Integer>{

}
