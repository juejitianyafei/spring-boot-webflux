package com.ty.web;

import com.ty.domain.City;
import com.ty.handler.CityHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wangfei
 * @date 2019/6/4 18:31
 */
@Controller
@RequestMapping(value = "/city")
public class CityWebFluxController {

    @Autowired
    private CityHandler cityHandler;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        return cityHandler.findCityById(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public Flux<City> findAllCity() {
        return cityHandler.findAllCity();
    }

    @PostMapping("/add")
    @ResponseBody
    public Mono<City> saveCity(@RequestBody City city) {
        return cityHandler.save(city);
    }

    @PostMapping("/update")
    @ResponseBody
    public Mono<City> modifyCity(@RequestBody City city) {
        return cityHandler.modifyCity(city);
    }

    @PostMapping(value = "/{id}")
    @ResponseBody
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        return cityHandler.deleteCity(id);
    }

    private static final String CITY_LIST_PATH_NAME = "cityList";
    private static final String CITY_PATH_NAME = "city";

    @GetMapping("/page/list")
    public String listPage(final Model model) {
        final Flux<City> cityFluxList = cityHandler.findAllCity();
        model.addAttribute("cityList", cityFluxList);
        return CITY_LIST_PATH_NAME;
    }

    @GetMapping("/getByName")
    public String getByCityName(final Model model,
                                @RequestParam("cityName") String cityName) {
        final Mono<City> city = cityHandler.getByCityName(cityName);
        model.addAttribute("city", city);
        return CITY_PATH_NAME;
    }
}
