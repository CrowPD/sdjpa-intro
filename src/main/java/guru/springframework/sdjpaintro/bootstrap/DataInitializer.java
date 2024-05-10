package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.hibernate.annotations.CollectionTypeRegistration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Book book1 = new Book("Domain Driven Design", "123", "RandomHouse");
        System.out.printf("id: %s%n", book1.getId());
        Book book1Saved = bookRepository.save(book1);
        System.out.printf("id: %s%n", book1Saved.getId());

        Book book2 = new Book("Testing Driven Development", "345", "O'Meily Pub");
        Book book2Saved = bookRepository.save(book2);

        System.out.printf("Total amount: %d%n", bookRepository.count());
        bookRepository.findAll().forEach(b -> System.out.println(b.getTitle()));
    }
}
