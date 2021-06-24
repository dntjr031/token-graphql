package com.lama.graphqlserver.user.repository;

import com.lama.graphqlserver.user.entity.UserPwd;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserPWDRepo extends JpaRepository<UserPwd, String> {
  Optional<UserPwd> findByUserId(String userId);
}
