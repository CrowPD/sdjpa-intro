package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        bookRepository.deleteAll();

        Book book1 = new Book("Domain Driven Design", "123", "RandomHouse", null);
		bookRepository.save(book1);

		Book book2 = new Book("Testing Driven Development", "345", "O'Meily Pub", null);
        bookRepository.save(book2);

        System.out.printf("Total amount: %d%n", bookRepository.count());
        bookRepository.findAll().forEach(b -> System.out.println(b.getTitle()));
    }
}
