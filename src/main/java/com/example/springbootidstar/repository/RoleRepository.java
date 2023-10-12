package com.example.springbootidstar.repository;


import com.example.springbootidstar.model.oauth.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findOneByName(String name);
    List<Role> findByNameIn(String[] names);
}
