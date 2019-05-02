package com.yk.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yk.post.entity.Posts;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long>{
	public Posts findById(long post_id);
	
	@Modifying
	@Query("UPDATE Posts SET p_counts = p_counts + 1 WHERE post_id = ?1")
	void updatePostCounts(long post_id);
	
	@Modifying
	@Query("UPDATE Posts SET p_blinded = 1 WHERE post_id = ?1")
	void updatePostBlinded(long post_id);
	
	@Modifying
	@Query("UPDATE Posts t SET t.p_title= :p_title, t.p_content= :p_content WHERE post_id = :post_id")
	public void updatePostTitleAndContent(@Param("post_id") long post_id, @Param("p_title") String p_title, @Param("p_content") String p_content);
	
}
