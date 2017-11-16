package booklist.imta.adriencadoret.fr.booklist.services;

import java.util.List;

import booklist.imta.adriencadoret.fr.booklist.model.Book;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BooksService {

    @GET("books")
    public Call<List<Book>> getBooks();

}
