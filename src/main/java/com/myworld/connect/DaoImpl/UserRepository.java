package com.myworld.connect.DaoImpl;

import com.myworld.connect.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {



}
