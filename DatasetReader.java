import java.io.*;
import java.util.*;

public class DatasetReader {

    // Reads CSV and returns list of Book objects
    public static List<Book> readBooks(String filename) {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { // skip header
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (data.length < 7) continue;

                try {
                    String title = data[0].replace("\"", "");
                    String author = data[1].replace("\"", "");
                    double userRating = Double.parseDouble(data[2]);
                    int reviews = Integer.parseInt(data[3]);
                    double price = Double.parseDouble(data[4]);
                    int year = Integer.parseInt(data[5]);
                    String genre = data[6].replace("\"", "");

                    books.add(new Book(title, author, userRating, reviews, price, year, genre));
                } catch (Exception e) {
                    // Skip malformed rows
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Task 1: Total number of books by an author
    public static long countBooksByAuthor(List<Book> books, String author) {
        return books.stream().filter(b -> b.getAuthor().equalsIgnoreCase(author)).count();
    }

    // Task 2: All authors
    public static Set<String> getAllAuthors(List<Book> books) {
        Set<String> authors = new HashSet<>();
        for (Book b : books) {
            authors.add(b.getAuthor());
        }
        return authors;
    }

    // Task 3: Names of all books by an author
    public static List<String> getBooksByAuthor(List<Book> books, String author) {
        List<String> titles = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                titles.add(b.getTitle());
            }
        }
        return titles;
    }

    // Task 4: Classify with user rating
    public static List<Book> getBooksByRating(List<Book> books, double rating) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getUserRating() == rating) {
                result.add(b);
            }
        }
        return result;
    }

    // Task 5: Price of all books by an author
    public static Map<String, Double> getBookPricesByAuthor(List<Book> books, String author) {
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                prices.put(b.getTitle(), b.getPrice());
            }
        }
        return prices;
    }
}
