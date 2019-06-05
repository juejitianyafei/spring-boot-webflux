package com.ty.dao;

import com.ty.domain.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


/**
 * @author wangfei
 * @date 2019/6/4 18:28
 */
@Repository
public interface CityRepository extends ReactiveMongoRepository<City, Long> {

    Mono<City> findByCityName(String cityName);
}
