package com.yk.web.post.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yk.web.post.entity.PostLikes;

public interface PostLikeRepository extends JpaRepository<PostLikes, Long>{
/*	@Query("INSERT INTO Posts ( ")
	public void saveLikes();*/
	
	
	@Query("SELECT p FROM PostLikes p WHERE post_id = :post_id AND nickname= :nickname")
	Optional<PostLikes> isLikedCheck(@Param("post_id") long post_id, @Param("nickname") String nickname);
	
	@Modifying
	@Query("UPDATE PostLikes SET likes = likes + 1 WHERE post_id = :post_id AND kinds = 'post'")
	public void likeUp(@Param("post_id") long post_id);
	
	@Modifying
	@Query("UPDATE PostLikes SET likes = likes - 1 WHERE post_id = :post_id AND kinds = 'post'")
	public void likeDown(@Param("post_id") long post_id);
	
	@Modifying
	@Query("UPDATE PostLikes SET likes = likes +1 WHERE com_id = :com_id AND kinds = 'comment'")
	public void likeUpComment(@Param("com_id") long com_id);
	
	@Modifying
	@Query("UPDATE PostLikes SET likes = likes -1 WHERE com_id = :com_id AND kinds = 'comment'")
	public void likeDownComment(@Param("com_id") long com_id);
}
