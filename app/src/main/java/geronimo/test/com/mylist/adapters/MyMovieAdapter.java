package geronimo.test.com.mylist.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import geronimo.test.com.mylist.R;
import geronimo.test.com.mylist.models.MyMovie;
import geronimo.test.com.mylist.views.holder.ContentViewHolder;

/**
 * Created by Muthu on 11/02/2018.
 */

public class MyMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyMovie> myMovies;
    private Context context;

    public MyMovieAdapter(Context context){
        this.context = context;
        myMovies = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.rv_item_layout, parent, false);
        viewHolder = new ContentViewHolder(viewItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyMovie myMovie = myMovies.get(position);
        final ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
        //Set all the information
        //use glide to load images
        Glide.with(context)
                .load(myMovie.getImgSrc())
                .asBitmap()
                .placeholder(contentViewHolder.imgIcone.getDrawable())
                .into(contentViewHolder.imgIcone);

        contentViewHolder.txtTitle.setText(myMovie.getTitle());
        contentViewHolder.txtSubtitle.setText(myMovie.getSubtitle());
        contentViewHolder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Details : " + myMovie.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        contentViewHolder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareContent("Hello : " + myMovie.getTitle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return myMovies == null ? 0 : myMovies.size();
    }

    public List<MyMovie> getMovies(){
        return myMovies;
    }

    //Method to share simple texts to other apps through intent
    public void shareContent(String message){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    public void add(MyMovie myMovie) {
        myMovies.add(myMovie);
        notifyItemInserted(myMovies.size() - 1);
    }

    public void addAll(List<MyMovie> myMovies) {
        for (MyMovie myMovie : myMovies) {
            add(myMovie);

        }
    }

    public void remove(MyMovie myMovie) {
        int position = myMovies.indexOf(myMovie);
        if (position > -1) {
            myMovies.remove(position);
            notifyItemRemoved(position);
        }
    }
    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        try {
            final ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
            contentViewHolder.imgIcone.setImageResource(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
