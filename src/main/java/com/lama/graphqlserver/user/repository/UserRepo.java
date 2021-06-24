package com.lama.graphqlserver.user.repository;

import com.lama.graphqlserver.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

}
