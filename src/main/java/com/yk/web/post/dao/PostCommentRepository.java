package com.yk.web.post.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yk.web.post.entity.PostComments;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComments, Long>{
	
	@Query("SELECT MAX(com_re_seq) FROM PostComments WHERE com_group_seq= ?1 AND post_id= ?2")
	public Long getComReMaxValue(long com_group_seq, long post_id);
	
	@Query("SELECT MAX(p.com_group_seq) FROM PostComments p WHERE post_id = ?1")
	public Long getComGroupMaxValue(long post_id);
	
	@Modifying
	@Query("UPDATE PostComments SET com_content = :com_content WHERE com_id= :com_id")
	public void modfiyCommentContent(@Param("com_content") String com_content, @Param("com_id") long com_id);
	
	@Modifying
	@Query("UPDATE PostComments SET com_content = ?1, isDeleted = ?2 WHERE com_id = ?3")
	public void deleteMessageInComment(String com_content, boolean isDeleted, long com_id);
}
