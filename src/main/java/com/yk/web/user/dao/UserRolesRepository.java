package com.yk.web.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yk.web.user.entity.UserRole;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
    //@Query("select a.role from UserRole a, Users b where b.username=?1 and a.userid=b.userid")
	@Query("SELECT b.role FROM Users u JOIN u.userRole b ON u.userid=b.users.userid")
    public List<String> findRoleByUsername(String username);
    
}

