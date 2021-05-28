package com.example.proyectofinciclo.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.models.news;
import com.example.proyectofinciclo.Services.LoadImage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder>{

    private List<news> mDataset;
    private FragmentTransaction transaction;
    private Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgnews;
        public TextView tvtitle;
        public TextView tvdesc;
        public TextView tvdate;
        public MyViewHolder(View v) {
            super(v);
            imgnews = v.findViewById(R.id.ivNlist);
            tvtitle = v.findViewById(R.id.txTitleNlist);
            //tvdesc = v.findViewById(R.id.txDescNlist);
            tvdate = v.findViewById(R.id.txDateNlist);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NewsListAdapter(List<news> myDataset, Context context) {
        this.context=context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NewsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_news, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // Cargamos el titulo
        holder.tvtitle.setText(mDataset.get(position).getTitle());
        // Cargamos la noticia
        //holder.tvdesc.setText(mDataset.get(position).getDesc());
        // Cargamos la fecha
        SimpleDateFormat sf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        sf.setLenient(true);
        Date date = null;
        String Formateddate = null;
        try {
            date = sf.parse(mDataset.get(position).getDate()+"+02:00");
            String fecha = new SimpleDateFormat("EEEE, d 'de' MMMM",new Locale("es","ES")).format(date);
            Formateddate = fecha.toUpperCase().charAt(0) + fecha.substring(1,fecha.length());
            holder.tvdate.setText(Formateddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Cargamos la foto
        String imageHttpAddress = mDataset.get(position).getImg();
        new LoadImage(holder.imgnews).execute(imageHttpAddress);
        // Añadimos la función al hacer click
        Date finalDate = date;
        String finalFormateddate = Formateddate;
        holder.imgnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsDetailsActivity.class);
                intent.putExtra("id",mDataset.get(position).getId());
                intent.putExtra("title",mDataset.get(position).getTitle());
                intent.putExtra("desc",mDataset.get(position).getDesc());
                intent.putExtra("date", finalFormateddate);
                intent.putExtra("img",mDataset.get(position).getImg());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
