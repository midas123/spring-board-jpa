package com.yk.web.image;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserImagesRepository extends JpaRepository<UserImages, Long>{
	@Query(value="SELECT u FROM UserImages u WHERE users_images.userid=:userid")
	public UserImages getOneUserImage(@Param("userid") long userid);
}
