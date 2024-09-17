package com.example.UnderTheSea_Server;

import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.domain.UserStatus;
import com.example.UnderTheSea_Server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

// 실제 application.yaml 내용 기반으로 db 연결 및 활용
@SpringBootTest
// 실제 db 없이 인 메모리 db 활용
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UnderTheSeaServerApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	@Transactional // rollback 수행
	void contextLoads() {
		//given
		String email = "wyoung2@gmail.com";
		User user = User.builder()
				//.user_id(13l)
				.nickname("woo2")
				.email("wyoung2@gmail.com")
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
