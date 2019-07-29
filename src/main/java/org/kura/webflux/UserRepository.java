package org.kura.webflux;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Repository
@Slf4j
public class UserRepository {
	
	String baseurl = "http://localhost:8101";
	
	public Mono<String> getToken(){
		log.info("UserRepository.getToken START:");
		WebClient webClient = WebClient
				  .builder()
				    .baseUrl(baseurl)
				    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
				    .build();
		Mono<String> token = webClient
								.get()
								.uri("/gettoken")
								.retrieve()
								.bodyToMono(String.class)
								.log()
								.doOnSuccess(onSuccess -> log.info("return token :" + onSuccess))
								.doOnError(onError -> log.warn("error occured.", onError));
		log.info("UserRepository.getToken END  :");
		return token;
	}
	
	public Mono<String> getUserInfo(String token, String id){
		log.info("UserRepository.getToken START id:" + id + ", token:");
		WebClient webClient = WebClient
				  .builder()
				    .baseUrl(baseurl)
				    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
				    .build();
		Mono<String> userinfo = webClient
								.get()
								.uri("/user/" + id)
								.header("X-Token", token)
								.retrieve()
								.bodyToMono(String.class)
								.log()
								.doOnSuccess(onSuccess -> log.info("return userinfo :" + onSuccess))
								.doOnError(onError -> log.warn("error occured.", onError));
		log.info("UserRepository.getToken END  id:" + id + ", token:");
		return userinfo;
	}

}
