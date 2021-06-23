package com.lama.graphqlserver.domain;

import io.leangen.graphql.annotations.GraphQLQuery;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

/**
 * 테스트용 미터기 정보 엔티티.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "EM_METER_INFO")
public class Meter {

  @Id
  @GeneratedValue
  @Column(name = "METER_SEQ")
  @GraphQLQuery(name = "meterSeq")
  private Integer meterSeq;

  @Column(name = "METER_ID")
  @GraphQLQuery(name = "meterId")
  private String meterId;

  @Column(name = "HODI_MANAGEMENT_ID")
  @GraphQLQuery(name = "managementId")
  private String managementId;

  @Column(name = "AGG_CODE")
  @GraphQLQuery(name = "aggCode")
  private String aggCode;

  @Column(name = "INSTALL_LOCATION_ID")
  @GraphQLQuery(name = "locationId")
  private Integer locationId;

  @Column(name = "LOAD_POSSIBLE_SEQ")
  @GraphQLQuery(name = "possibleSeq")
  private Integer possibleSeq;

  @Column(name = "INSTALL_DATE")
  @GraphQLQuery(name = "installDt")
  private LocalDateTime installDt;

  @Column(name = "REMOVE_DATE")
  @GraphQLQuery(name = "removeDt")
  private LocalDateTime removeDt;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Meter meter = (Meter) o;

    return Objects.equals(meterSeq, meter.meterSeq);
  }

  @Override
  public int hashCode() {
    return 723479575;
  }
}
