package me.changchao.spring.springwebfluxasyncjdbcsample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.changchao.spring.springwebfluxasyncjdbcsample.domain.City;
import me.changchao.spring.springwebfluxasyncjdbcsample.service.CityService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/rx")
public class CityControllerRx {
	@Autowired
	private CityService cityService;

	@GetMapping("/search")
	@ResponseBody
	public Mono<City> searchOne() {
		return this.cityService.getCity("Bath", "UK");
	}

	@GetMapping("/")
	@ResponseBody
	public Flux<City> list() {
		return this.cityService.findAll();
	}

	@GetMapping("/add")
	@ResponseBody
	public Mono<Long> add() {
		String name = "city:" + Math.random();
		String country = "country:" + Math.random();
		return this.cityService.add(name, country).map(city -> city.getId());
	}
}
