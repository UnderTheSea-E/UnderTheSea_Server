package com.example.UnderTheSea_Server;

import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.domain.UserStatus;
import com.example.UnderTheSea_Server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UnderTheSeaServerApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		//given
		String email = "wyoung@gmail.com";
		User user = User.builder()
				.user_id(13l)
				.nickname("woo")
				.email("wyoung@gmail.com")
				.updated_at(new Date())
				.created_at(new Date())
				.profileImgUrl("http")
				.status(UserStatus.ACTIVE)
				.build();

		//when
		User saved = userRepository.save(user);

		//then
		assertThat(saved.getEmail()).isEqualTo(email);
	}

}
