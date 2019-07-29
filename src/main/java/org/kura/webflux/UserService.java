package org.kura.webflux;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
@Slf4j
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public Mono<String> getUserDetail(String userid){
		log.info("UserService.getUserDetail START:" + userid);
		Mono<String> tokenMono = userRepository.getToken();
		Mono<String> res = tokenMono.flatMap(token -> userRepository.getUserInfo(token , userid));
		log.info("UserService.getUserDetail END  :" + userid);
		return res;
	}
}
