package com.master4.services;

import com.master4.entities.Role;
import com.master4.entities.Tag;
import com.master4.exceptions.ResourceNotFoundException;

import java.util.List;

public interface RoleService {
    public List<Role> getAllroles();

    Role findById(long id) throws ResourceNotFoundException;

    void save(Role role);

    void deleteById(long id);
}
