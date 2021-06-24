package com.lama.graphqlserver.user.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "COM_MEM_INFO")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @Column(name = "USER_ID")
  private String userId;

  @Column(name = "DEPT_CD")
  private String deptCd;

  @Column(name = "USER_NM")
  private String userNm;

  @Column(name = "EMPLYR_NO")
  private String employerNo;

  @Column(name = "EMAIL_ADRES")
  private String email;

  @Column(name = "MBTLNUM")
  private String phone;

  @Column(name = "BRTHDY")
  private String birthday;

  @Column(name = "OFCPS_NM")
  private String officialNm;

  @Column(name = "EMPLYR_STTUS_CD")
  private String employerCd;

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
