package com.example.proyectofinciclo.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.models.partido;
import com.example.proyectofinciclo.Services.LoadImage;
import com.example.proyectofinciclo.ui.resultados.details.MatchDetailsActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ResulsHomeAdapter extends RecyclerView.Adapter<ResulsHomeAdapter.MyViewHolder>{

    private List<partido> mDataset;
    private FragmentTransaction transaction;
    private Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView tvTime,tvResul;
        public TextView txthome;
        public TextView txtaway;
        public TextView txtfecha;
        public ImageView awayimg,homeimg;
        public CardView cvTime;
        public MyViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.cvResul);
            tvResul = v.findViewById(R.id.tvResul);
            tvTime = v.findViewById(R.id.tvTime);
            homeimg = v.findViewById(R.id.ivHomeTeam);
            awayimg = v.findViewById(R.id.ivAwayTeam);
            txthome = v.findViewById(R.id.tvHomeTeam);
            txtaway = v.findViewById(R.id.tvAwayTeam);
            cvTime = v.findViewById(R.id.cvTime);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ResulsHomeAdapter(List<partido> myDataset, Context context) {
        this.context=context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_result, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // Cargar resultado
        if(mDataset.get(position).getGloc()!=null&&mDataset.get(position).getGloc()!=null){
            holder.tvResul.setText(mDataset.get(position).getGloc()+ " : "+ mDataset.get(position).getGvis());
            holder.tvTime.setText(mDataset.get(position).getMin());
        }else{
            // Formato a fecha y hora
            SimpleDateFormat sf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
            sf.setLenient(true);
            Date date = null;
            try {
                date = sf.parse(mDataset.get(position).getFecha()+"+02:00");
                String fecha = new SimpleDateFormat("d 'de' MMM",new Locale("es","ES")).format(date);
                holder.tvTime.setText(fecha.toUpperCase().charAt(0) + fecha.substring(1,fecha.length()));
                holder.tvTime.setTextColor(Color.BLACK);
                holder.cvTime.setCardBackgroundColor(Color.TRANSPARENT);
                holder.cvTime.setCardElevation(0);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Cargar estadio
            holder.tvResul.setText(mDataset.get(position).getEstadio());
            holder.tvResul.setTextSize(12);
            holder.tvResul.setTypeface(Typeface.DEFAULT);
        }
        // Cargar escudos
        String imageHttpAddresshome = mDataset.get(position).getLoc().replace(" ","");
        new LoadImage(holder.homeimg).execute("https://res.adriandiarteprieto.tk/escudos/"+imageHttpAddresshome+".png");
        String imageHttpAddressaway = mDataset.get(position).getVis().replace(" ","");
        new LoadImage(holder.awayimg).execute("https://res.adriandiarteprieto.tk/escudos/"+imageHttpAddressaway+".png");
        // Cargar nombres
        holder.txthome.setText(mDataset.get(position).getLoc());
        holder.txtaway.setText(mDataset.get(position).getVis());
        // Onclic cardview
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("date", holder.tvTime.getText().toString());
                bundle.putString("resul", mDataset.get(position).getGloc()+ " : "+ mDataset.get(position).getGvis());
                bundle.putString("min", holder.tvTime.getText().toString());
                bundle.putString("loc", mDataset.get(position).getLoc());
                bundle.putString("vis", mDataset.get(position).getVis());
                bundle.putString("estadio", mDataset.get(position).getEstadio());

                Intent intent = new Intent(view.getContext(), MatchDetailsActivity.class);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
