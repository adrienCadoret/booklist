package booklist.imta.adriencadoret.fr.booklist.fragments;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import booklist.imta.adriencadoret.fr.booklist.R;
import booklist.imta.adriencadoret.fr.booklist.model.Book;


public class CustomListItem extends LinearLayout {
    private TextView titleTextView;
    private ImageView coverImageView;

    public CustomListItem(Context context) {
        this(context, null);
    }

    public CustomListItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        titleTextView = findViewById(R.id.title_item);
        coverImageView = findViewById(R.id.coverImage);
    }

    public void bindView(final Book book, final List.ListFragmentListener listener) {
        titleTextView.setText(book.getTitle());
        Picasso.with(this.getContext())
                .load(book.getCover())
                .into((ImageView) findViewById(R.id.coverImage));
        this.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onClickOnBook(book);
            }
        });
    }
}
