package ua.donetc.security;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;

@SpringBootApplication
public class SecuritySpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritySpringApplication.class, args);
	}


		@Bean
		public ModelMapper modelMapper(){
			return new ModelMapper();
		}


		}
