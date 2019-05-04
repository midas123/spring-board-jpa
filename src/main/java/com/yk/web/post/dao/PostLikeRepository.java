package com.yk.web.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yk.web.post.entity.PostLikes;

public interface PostLikeRepository extends JpaRepository<PostLikes, Long>{
	//public PostLikes existsByPost_idAndNickname(long post_id, String nickname);

	@Modifying
	@Query("UPDATE PostLikes SET post_id = :post_id, kinds = :kinds, likes = likes + 1, nickname = :nickname")
	public void LikeUp(@Param("post_id") long post_id, @Param("kinds") String kinds, @Param("nickname") String nickname);
}
