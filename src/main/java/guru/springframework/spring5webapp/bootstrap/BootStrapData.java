package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner
{
  private AuthorRepository    authorRepository;
  private BookRepository      bookRepository;
  private PublisherRepository publisherRepository;

  public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository)
  {
    //DI - injecting the dependencies
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception
  {
    Publisher publisher = new Publisher("Mike", "Prolet", "Gabrovo", "Bulgaria", "5300");
    publisherRepository.save(publisher);

    Author eric = new Author("Eric", "Evans");
    Book ddd = new Book("Domain Driver Design", "123123");
    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);
    authorRepository.save(eric);
    bookRepository.save(ddd);
    ddd.setPublisher(publisher);
    publisher.getBooks().add(ddd);
    publisherRepository.save(publisher);

    Author robert = new Author("Steve", "McConnell");
    Book cc = new Book("Code Complete", "45678");
    robert.getBooks().add(cc);
    cc.getAuthors().add(robert);
    authorRepository.save(robert);
    bookRepository.save(cc);
    cc.setPublisher(publisher);
    publisher.getBooks().add(cc);
    publisherRepository.save(publisher);

    System.out.println("Publisher: " + publisher);
    System.out.println("Bootstrap started");
    System.out.println("Number of books: " + bookRepository.count());
    System.out.println("Publisher books: " + publisher.getBooks().size());
  }
}
