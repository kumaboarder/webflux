package org.kura.webflux;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/{id}")
	public Mono<String> getUser(@PathVariable("id") String id){
		log.info("UserController.getUser START:" + id);
		Mono<String> res = userService.getUserDetail(id);
		log.info("UserController.getUser END  :" + id);
		return res;
	}
}
