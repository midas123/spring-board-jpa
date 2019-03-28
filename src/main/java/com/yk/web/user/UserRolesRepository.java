package com.yk.web.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
    @Query("select a.role from UserRole a, Users b where b.username=?1 and a.userid=b.userid")
    public List<String> findRoleByUsername(String username);
    
    
    
}

