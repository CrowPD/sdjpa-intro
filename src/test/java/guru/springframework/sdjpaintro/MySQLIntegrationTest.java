package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {
		"guru.springframework.sdjpaintro.bootstrap"
})
// SO, fun fact. Spring Boot by default for testing overrides DB settings to use an in memory H2 DB
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {
	@Autowired
	BookRepository bookRepository;

	@Test
	void testMySQLConnection() {
		long countBefore = bookRepository.count();
		assertThat(countBefore).isEqualTo(2);
	}
}
