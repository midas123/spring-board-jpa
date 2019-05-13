package com.yk.web.post.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yk.web.post.entity.Posts;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long>{
	@Modifying
	@Query("UPDATE Posts SET p_counts = p_counts + 1 WHERE post_id = ?1")
	void updatePostCounts(long post_id);
	
/*	@Modifying
	@Query("UPDATE Posts SET p_blinded = 1 WHERE post_id = ?1")
	void updatePostBlinded(long post_id);*/
	
	@Modifying
	@Query("UPDATE Posts t SET t.p_title= :p_title, t.p_content= :p_content WHERE post_id = :post_id")
	public void updatePostTitleAndContent(@Param("post_id") long post_id, @Param("p_title") String p_title, @Param("p_content") String p_content);
	
	//@Query("SELECT p FROM Posts p LEFT JOIN p.postLikes b ON b.post.post_id = p.post_id")
	@Query(value="SELECT * FROM Posts p LEFT JOIN (SELECT post_id, SUM(likes) AS likes FROM PostLikes GROUP BY post_id) b ON b.post_id = p.post_id ORDER BY p.post_id DESC", nativeQuery=true)
	public List<Posts> getAllPost();
	
	//@Query("SELECT p, SUM(b.likes) FROM Posts p JOIN p.postLikes b WHERE b.post.post_id = :post_id")
/*	@Query(value="SELECT * FROM Posts p LEFT JOIN (SELECT post_id, SUM(likes) AS likes FROM PostLikes "
			+ "GROUP BY post_id) l ON p.post_id = l.post_id "
			+ "WHERE p.post_id = :post_id", nativeQuery=true)*/
	@Query(value="SELECT * FROM Posts p LEFT JOIN (SELECT post_id, SUM(likes) AS likes FROM PostLikes GROUP BY post_id) l ON p.post_id = l.post_id LEFT JOIN PostComments c ON c.post_id = p.post_id WHERE p.post_id = :post_id", nativeQuery=true)
	public Posts getOnePost(@Param("post_id") long post_id);
	
}
