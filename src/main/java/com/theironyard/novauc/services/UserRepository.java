package com.theironyard.novauc.services;

import org.springframework.data.repository.CrudRepository;
import com.theironyard.novauc.entities.User;
/**
 * Created by octavio on 3/17/17.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}

