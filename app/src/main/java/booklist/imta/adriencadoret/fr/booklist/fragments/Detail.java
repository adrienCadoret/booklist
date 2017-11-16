package booklist.imta.adriencadoret.fr.booklist.fragments;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import booklist.imta.adriencadoret.fr.booklist.R;
import booklist.imta.adriencadoret.fr.booklist.model.Book;


public class Detail extends Fragment {

    private Book selectedBook;
    private ImageView coverImageView;
    private TextView titleTextView;
    private TextView priceTextView;
    private TextView synopsisTextView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        titleTextView = (TextView) view.findViewById(R.id.book_title);
        priceTextView = (TextView) view.findViewById(R.id.book_price);
        synopsisTextView = (TextView) view.findViewById(R.id.book_synopsis);
        coverImageView = (ImageView) view.findViewById(R.id.book_cover);
        System.out.println("onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void displayBook(){
        titleTextView = (TextView) getView().findViewById(R.id.book_title);
        titleTextView.setText(selectedBook.getTitle());
        priceTextView.setText(selectedBook.getPrice() + "€");
        synopsisTextView.setText(selectedBook.getSynopsis().toString());
        Picasso.with(this.getContext())
                .load(selectedBook.getCover())
                .into(coverImageView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(selectedBook != null){
            displayBook();
        }
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
        if(getView() != null){
            this.displayBook();
        }
    }

}
