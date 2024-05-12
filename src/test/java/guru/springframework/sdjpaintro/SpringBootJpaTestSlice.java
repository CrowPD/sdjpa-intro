package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {
        "guru.springframework.sdjpaintro.bootstrap"
})
public class SpringBootJpaTestSlice {
    @Autowired
    BookRepository bookRepository;

    /**
     * Spring runs tests inside a transaction.
     * After the test, transaction is rolled back.
     * {@code @Commit} or {@code @Rollback(false)} prevents the rollback and data can be used later
     */
    @Test
    @Order(1)
    @Commit
    void testJpaTestSlice() {
        long countBefore = bookRepository.count();

        assertThat(countBefore).isEqualTo(2);

        bookRepository.save(new Book("My Book", "4566", "Self Publish"));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Test
    @Order(2)
    void testJpaTestSliceTransaction() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);
    }
}