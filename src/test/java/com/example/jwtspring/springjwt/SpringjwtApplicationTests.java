package com.example.jwtspring.springjwt;

import com.example.jwtspring.springjwt.Utils.Profiles;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;


@SpringBootTest
@Profile(Profiles.NO_AUTH)
class SpringjwtApplicationTests {

	@Test
	void contextLoads() {
	}

}
