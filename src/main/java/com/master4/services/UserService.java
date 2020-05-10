package com.master4.services;

import com.master4.entities.Article;
import com.master4.entities.User;
import com.master4.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserService {

    User findById(long id) throws ResourceNotFoundException;

    void save(User User);

    void deleteById(long id);

    User findByIdWithTags(@Param("id") long id);

    User findByUserUsername(String username);

    User findByEmail(String value);
}
