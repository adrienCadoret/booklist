package booklist.imta.adriencadoret.fr.booklist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import booklist.imta.adriencadoret.fr.booklist.R;
import booklist.imta.adriencadoret.fr.booklist.fragments.CustomListItem;
import booklist.imta.adriencadoret.fr.booklist.model.Book;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<Book> books;
    private final LayoutInflater inflater;
    private booklist.imta.adriencadoret.fr.booklist.fragments.List.ListFragmentListener listener;

    public BookAdapter(LayoutInflater layoutInflater, List<Book> books, booklist.imta.adriencadoret.fr.booklist.fragments.List.ListFragmentListener listener) {
        this.books = books;
        inflater = layoutInflater;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.sample_custom_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CustomListItem)holder.itemView).bindView(books.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public void updateList (List<Book> newBooks) {
        if (newBooks != null && newBooks.size() > 0) {
            books.clear();
            books.addAll(newBooks);
            notifyDataSetChanged();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
