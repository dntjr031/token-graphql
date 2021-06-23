package com.lama.graphqlserver.service;

import com.lama.graphqlserver.domain.Meter;
import com.lama.graphqlserver.repository.MeterRepository;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 테스트용 미터기 서비스.
 */
@Service
@GraphQLApi
@AllArgsConstructor
public class MeterService {
  private final MeterRepository repository;

  /*
  {
    meters{
      id
      title
    }
  }
  */
  @GraphQLQuery(name = "meters")
  public List<Meter> getMeters() {
    return repository.findAll();
  }

  /*
  {
    meter(id:1){
      id
      title
    }
  }
  */
  @GraphQLQuery(name = "meter")
  public Optional<Meter> getMeters(int seq) {
    return repository.findById(seq);
  }

  //mutation{
  //	saveMeter(post:{title:"title"}){
  //    id
  //    title
  //  }
  //}

  @GraphQLMutation(name = "saveMeter")
  public Meter saveMeter(Meter meter) {
    return repository.save(meter);
  }

  //mutation{
  //  deleteMeter(id:1)
  //}
  @GraphQLMutation(name = "deleteMeter")
  public void deleteMeter(int id) {
    repository.deleteById(id);
  }

  //{
  //  posts{
  //    meterId
  //    isGood
  //  }
  //}
  @GraphQLQuery(name = "isGood")
  public boolean isGood(@GraphQLContext Meter meter) {
    return !meter.getMeterId().equals("11111111");
  }
}
