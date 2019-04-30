package com.yk.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yk.web.entity.Boards;

@Repository
public interface BoardRepository extends JpaRepository<Boards, Long>{

}
