package com.yk.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yk.web.entity.Posts;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long>{

}
