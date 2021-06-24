package com.lama.graphqlserver.user.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "COM_MEM_PWD")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPwd {

  @Id
  @Column(name = "PWD_SEQ")
  private String pwdSeq;

  @Column(name = "USER_ID")
  private String userId;

  @Column(name = "USER_PWD")
  private String userPwd;

  @Column(name = "PWD_END_DE")
  private String pwdEndDe;

  @Column(name = "DEL_AT")
  private String delAt;

  @Column(name = "REG_DT")
  private LocalDateTime regDt;

  @Column(name = "REG_WORKER")
  private String regWorker;

  @Column(name = "MDF_DT")
  private LocalDateTime mdfDt;

  @Column(name = "MDF_WORKER")
  private String mdfWorker;

}
