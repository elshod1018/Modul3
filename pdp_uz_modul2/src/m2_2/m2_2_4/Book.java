package m2_2.m2_2_4;
import java.util.List;

public class Book {
   private String name;
   private List<String>authors;
   private String  ISBN;

    public Book() {
    }

    public Book(String name, List<String> authors,String ISBN) {
        this.name = name;
        this.authors = authors;
        this.ISBN=ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", authors=" + authors +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
