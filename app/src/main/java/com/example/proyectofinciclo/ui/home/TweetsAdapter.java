package com.example.proyectofinciclo.ui.home;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinciclo.R;
import com.github.redouane59.twitter.dto.tweet.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.MyViewHolder>{

    private List<Tweet> mDataset;
    private FragmentTransaction transaction;
    private Context context;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivProfileImage,ivTweetedImage;
        public TextView tvUserName,tvScreenName,tvCreatedAtD,tvBodyD;
        public MyViewHolder(View v) {
            super(v);
            ivProfileImage = v.findViewById(R.id.ivProfileImage);
            tvUserName = v.findViewById(R.id.tvUserName);
            tvScreenName = v.findViewById(R.id.tvScreenName);
            tvCreatedAtD = v.findViewById(R.id.tvCreatedAtD);
            tvBodyD = v.findViewById(R.id.tvBodyD);
            ivTweetedImage = v.findViewById(R.id.ivTweetedImage);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TweetsAdapter(List<Tweet> myDataset, Context context) {
        this.context=context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tweet, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // 1665264812

        holder.tvUserName.setText(mDataset.get(position).getAuthorId()); //Nombre de usuario
        //holder.tvScreenName.setText("@"+mDataset.get(position).getUser().getDisplayedName()); //Alias de usuario(@)
        holder.tvBodyD.setText(mDataset.get(position).getText()); //Texto del tweet
        holder.ivTweetedImage.setVisibility(View.GONE); //Ocultar fotos (CAMBIAR)
        holder.tvCreatedAtD.setText(getRelativeTimeAgo(mDataset.get(position).getCreatedAt().toString()));
        //String imageHttpAddress = mDataset.get(position).getUser().getfoto;
        //new LoadImage(holder.ivProfileImage).execute(imageHttpAddress);

        // CARGAR IMAGENES
        /*
        if (tweet.mediaFound) {
            holder.ivTweetedImage.setVisibility(View.VISIBLE);
            Glide.with(context).load(tweet.media.urlHTTPS)
                    .bitmapTransform(new RoundedCornersTransformation(context, 20, 0))
                    .placeholder(R.drawable.ic_picture_placeholder_svg)
                    .error(R.drawable.ic_picture_placeholder_svg)
                    .into(holder.ivTweetedImage);
        } else {
            holder.ivTweetedImage.setVisibility(View.GONE);
        }*/

        /*
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

}
