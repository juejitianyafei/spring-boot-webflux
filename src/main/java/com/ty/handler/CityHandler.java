package com.ty.handler;

import com.ty.dao.CityRepository;
import com.ty.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wangfei
 * @date 2019/6/4 18:30
 */
@Component
public class CityHandler {

    private final CityRepository cityRepository;

    @Autowired
    public CityHandler(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Mono<City> save(City city) {
        return cityRepository.save(city);
    }

    public Mono<City> findCityById(Long id) {

        return cityRepository.findById(id);
    }

    public Flux<City> findAllCity() {

        return cityRepository.findAll();
    }

    public Mono<City> modifyCity(City city) {

        return cityRepository.save(city);
    }

    public Mono<Long> deleteCity(Long id) {
        return cityRepository.deleteById(id).flatMap(mono -> Mono.create(cityMonoSink -> cityMonoSink.success(id)));
    }

    public Mono<City> getByCityName(String cityName) {
        return cityRepository.findByCityName(cityName);
    }
}
