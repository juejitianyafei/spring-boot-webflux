package com.ty.web;

import com.ty.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wangfei
 * @date 2019/6/5 11:14
 */
@RestController
@RequestMapping(value = "/city2")
public class CityWebFluxReactiveController {

    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    private static final String CITY_PREFIX_KEY = "city:";

    @GetMapping(value = "/{id}")
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        String key = CITY_PREFIX_KEY + id;
        ReactiveHashOperations<String, String, City> operations = reactiveRedisTemplate.opsForHash();
        Mono<City> city = operations.get(CITY_PREFIX_KEY,key);
        return city;
    }

    @GetMapping(value = "/list")
    public Flux<City> findAll() {
        ReactiveHashOperations<String, String, City> operations = reactiveRedisTemplate.opsForHash();
        Flux<City> citys = operations.values(CITY_PREFIX_KEY);
        return citys;
    }

    @PostMapping("/add")
    public Mono<Boolean> saveCity(@RequestBody City city) {
        String key = CITY_PREFIX_KEY + city.getId();
        ReactiveHashOperations<String, String, City> operations = reactiveRedisTemplate.opsForHash();
        return operations.put(CITY_PREFIX_KEY,key,city);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        String key = CITY_PREFIX_KEY + id;
        ReactiveHashOperations<String, String, City> operations = reactiveRedisTemplate.opsForHash();
        return operations.remove(CITY_PREFIX_KEY,key);
    }
}
