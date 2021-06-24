package com.lama.graphqlserver.user.service;

import com.lama.graphqlserver.user.entity.User;
import com.lama.graphqlserver.user.entity.UserPwd;
import com.lama.graphqlserver.user.entity.UserView;
import com.lama.graphqlserver.user.repository.UserPWDRepo;
import com.lama.graphqlserver.user.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepo userRepo;
  private final UserPWDRepo pwdRepo;

  @Override
  public UserView loadUserByUsername(String userId) throws UsernameNotFoundException {
    User user = userRepo.findById(userId).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    UserPwd pwd = pwdRepo.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    return UserView.builder().userId(user.getUserId()).userNm(user.getUserNm()).userPwd(pwd.getUserPwd()).employerNo(user.getEmployerNo()).deptCd(user.getDeptCd()).build();
  }
}
