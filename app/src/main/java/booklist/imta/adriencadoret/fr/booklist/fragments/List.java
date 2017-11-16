package booklist.imta.adriencadoret.fr.booklist.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;

import booklist.imta.adriencadoret.fr.booklist.R;
import booklist.imta.adriencadoret.fr.booklist.adapters.BookAdapter;
import booklist.imta.adriencadoret.fr.booklist.model.Book;
import booklist.imta.adriencadoret.fr.booklist.services.BooksService;

public class List extends Fragment {

    private ListFragmentListener listener;
    private java.util.List<Book> books = new ArrayList<>();
    private BookAdapter adapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ListFragmentListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.bookListView);
        adapter = new BookAdapter(LayoutInflater.from((Context) listener), books, listener);
        recyclerView.setLayoutManager(new GridLayoutManager((Context) listener,1));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public interface ListFragmentListener {

        void onClickOnBook(Book book);

    }

    public void setData(java.util.List<Book> data) {
        this.books = data;
        this.adapter.updateList(this.books);
    }

    public java.util.List<Book> getData() {
        return books;
    }
}
