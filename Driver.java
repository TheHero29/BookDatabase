import java.util.*;

public class Driver {
    public static void main(String[] args) {
        String filename = "data.csv";  // Ensure your dataset CSV is in project root
        List<Book> books = DatasetReader.readBooks(filename);

        // Print all books
        System.out.println("=== All Books in Dataset ===");
        for (Book b : books) {
            b.printDetails();
        }

        // Task 1
        String author = "George Orwell";
        System.out.println("\nTotal books by " + author + ": " +
                DatasetReader.countBooksByAuthor(books, author));

        // Task 2
        System.out.println("\n=== All Authors ===");
        DatasetReader.getAllAuthors(books).forEach(System.out::println);

        // Task 3
        System.out.println("\nBooks by " + author + ":");
        DatasetReader.getBooksByAuthor(books, author).forEach(System.out::println);

        // Task 4
        double rating = 4.7;
        System.out.println("\nBooks with rating " + rating + ":");
        for (Book b : DatasetReader.getBooksByRating(books, rating)) {
            b.printDetails();
        }

        // Task 5
        System.out.println("\nPrices of books by " + author + ":");
        DatasetReader.getBookPricesByAuthor(books, author)
                     .forEach((title, price) -> System.out.println(title + " -> $" + price));
    }
}
