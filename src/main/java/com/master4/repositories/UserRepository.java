package com.master4.repositories;

import com.master4.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u where u.username=:username and u.password=:password")
    User findByUsernameAndPassword(@Param("username")String username,@Param("password") String password);
    @Query("from User u where u.email=:email")
    User findByEmail(@Param("email") String email);

}
