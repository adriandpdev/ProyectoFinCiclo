package com.example.proyectofinciclo.timelineprueba;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinciclo.R;

import java.security.AccessControlContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import twitter4j.ResponseList;
import twitter4j.Status;


public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.MyViewHolder>{

    private List<Status> mDataset;
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
    public TweetsAdapter(List<Status> myDataset, Context context) {
        this.context=context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TweetsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
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
        holder.tvUserName.setText(mDataset.get(position).getUser().getName()); //Nombre de usuario
        holder.tvScreenName.setText("@"+mDataset.get(position).getUser().getScreenName()); //Alias de usuario(@)
        holder.tvBodyD.setText((CharSequence) mDataset.get(position).getText()); //Texto del tweet
        holder.ivTweetedImage.setVisibility(View.GONE); //Ocultar fotos (CAMBIAR)
        //mDataset.get(position).getCreatedAt().toString()
        SimpleDateFormat df = new SimpleDateFormat("E L dd HH:mm:ss O yyyy") ;
        //DateTimeFormatter tf = DateTimeFormatter.ofPattern("E L dd HH:mm:ss O yyyy");
        try {
            Date fecha = df.parse(mDataset.get(position).getCreatedAt().toString());
            Date fechahoy = new Date();
            fechahoy.getDate();
            //ChronoUnit.DAYS.between(fecha,fechahoy);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //LocalDate myDate = LocalDate.parse("Fri Mar 12 18:07:46 GMT+01:00 2021", df);
        //LocalDate currentDate = LocalDate.now();
        //long numberOFDays = ChronoUnit.DAYS.between(myDate, currentDate);
        //System.out.println(String.format("The diff of days is %d",));


        //holder.tvCreatedAtD.setText((CharSequence) mDataset.get(position).getCreatedAt());
        Log.d("FECHA", "onBindViewHolder: "+ mDataset.get(position).getCreatedAt());
        String imageHttpAddress = mDataset.get(position).getUser().get400x400ProfileImageURL();

        //String imageHttpAddress = "https://www.unionistascf.com/wp-content/uploads/2018/10/Escudo_Unionistas_Salamanca_peq.png";
        new LoadImage(holder.ivProfileImage).execute(imageHttpAddress);

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

}
