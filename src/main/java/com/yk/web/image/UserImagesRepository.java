package com.yk.web.image;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserImagesRepository extends JpaRepository<UserImages, Long>{

}
