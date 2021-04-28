package com.example.proyectofinciclo.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.models.news;
import com.example.proyectofinciclo.timelineprueba.LoadImage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class NewsDetailsAdapter extends RecyclerView.Adapter<NewsDetailsAdapter.MyViewHolder>{

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
            public CardView cardView;
            public MyViewHolder(View v) {
                super(v);
                imgnews = v.findViewById(R.id.ivNdetails);
                tvtitle = v.findViewById(R.id.txTitleNdetails);
                tvdesc = v.findViewById(R.id.txDescNdetails);
                tvdate = v.findViewById(R.id.txDateNdetails);
                cardView = v.findViewById(R.id.cardViewNewsDetails);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public NewsDetailsAdapter(List<news> myDataset, Context context) {
            this.context=context;
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public NewsDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_news_details, parent, false);
            // set the view's size, margins, paddings and layout parameters
            NewsDetailsAdapter.MyViewHolder vh = new NewsDetailsAdapter.MyViewHolder(v);
            return vh;
        }


        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            // Cargamos el titulo
            holder.tvtitle.setText(mDataset.get(position).getTitle());
            // Cargamos la noticia
            holder.tvdesc.setText(mDataset.get(position).getDesc());
            // Cargamos la fecha
            SimpleDateFormat sf = new SimpleDateFormat("yyyy MM dd"); // #ToDo revisar formato
            sf.setLenient(true);
            Date date = null;
            try {
                date = sf.parse(mDataset.get(position).getDate());
                String fecha = new SimpleDateFormat("EEEE, d 'de' MMMM").format(date);
                holder.tvdate.setText(fecha.toUpperCase().charAt(0) + fecha.substring(1,fecha.length()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Cargamos la foto
            String imageHttpAddress = mDataset.get(position).getImg();
            new LoadImage(holder.imgnews).execute(imageHttpAddress);
            // Añadimos la función al hacer click
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

}
