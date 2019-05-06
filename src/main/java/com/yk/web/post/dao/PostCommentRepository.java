package com.yk.web.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yk.web.post.entity.PostComments;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComments, Long>{
	@Modifying
	@Query("UPDATE PostComments SET com_content = :com_content WHERE com_id= :com_id")
	public void updateCommentContent(@Param("com_content") String com_content, @Param("com_id") long com_id);
	
/*	@Query
	public void likeUpComment();
	
	@Query
	public void likeDownComment();
	*/
}
