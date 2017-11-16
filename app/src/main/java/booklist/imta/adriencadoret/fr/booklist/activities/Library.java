package booklist.imta.adriencadoret.fr.booklist.activities;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import booklist.imta.adriencadoret.fr.booklist.R;
import booklist.imta.adriencadoret.fr.booklist.fragments.Detail;
import booklist.imta.adriencadoret.fr.booklist.fragments.List;
import booklist.imta.adriencadoret.fr.booklist.model.Book;
import booklist.imta.adriencadoret.fr.booklist.services.BooksService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Library extends AppCompatActivity implements List.ListFragmentListener {

    private java.util.List<Book> bookList = new ArrayList<>();
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ON CREATE");


        int display_mode = getResources().getConfiguration().orientation;

        if (display_mode == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_library);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentLayout, new List(), List.class.getSimpleName())
                    .commit();
            this.getData();
        } else {
            setContentView(R.layout.activity_library_land);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_fragment, new List(), List.class.getSimpleName())
                    .commit();
            this.getData();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment, new Detail(), Detail.class.getSimpleName())
                    .commit();
        }
    }

    @Override
    public void onClickOnBook(Book book) {
        this.book = book;

        int display_mode = getResources().getConfiguration().orientation;
        if (display_mode == Configuration.ORIENTATION_PORTRAIT) {
            Detail detail = new Detail();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentLayout, detail, Detail.class.getSimpleName())
                    .commit();
            detail.setSelectedBook(book);
        } else{
            Detail detail = (Detail) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
            detail.setSelectedBook(book);
        }
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BooksService booksService = retrofit.create(BooksService.class);

        Call<java.util.List<Book>> call = booksService.getBooks();
        call.enqueue(new Callback<java.util.List<Book>>() {
            @Override
            public void onResponse(Call<java.util.List<Book>> call, Response<java.util.List<Book>> response) {
                Library.this.bookList = response.body();
                int display_mode = getResources().getConfiguration().orientation;
                List listFragment;
                if (display_mode == Configuration.ORIENTATION_PORTRAIT) {
                    listFragment = (List) getSupportFragmentManager().findFragmentById(R.id.fragmentLayout);
                } else{
                    listFragment = (List) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
                }
                listFragment.setData(Library.this.bookList);
            }

            @Override
            public void onFailure(Call<java.util.List<Book>> call, Throwable t) {
                System.out.println("Fail");
            }
        });
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        book = savedInstanceState.getParcelable("book");
        if(book != null && getSupportFragmentManager().findFragmentById(R.id.detail_fragment) != null){
            int display_mode = getResources().getConfiguration().orientation;
            if (display_mode == Configuration.ORIENTATION_PORTRAIT) {
            } else{
                Detail detail = (Detail) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
                detail.setSelectedBook(book);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("book", book);
        super.onSaveInstanceState(outState);
    }
}
