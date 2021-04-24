package com.example.proyectofinciclo.ui.calendario;

import android.content.Context;
import android.graphics.Typeface;
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
import com.example.proyectofinciclo.timelineprueba.LoadImage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ResulsAdapter extends RecyclerView.Adapter<ResulsAdapter.MyViewHolder>{

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
        public MyViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.cvResul);
            tvResul = v.findViewById(R.id.tvResul);
            tvTime = v.findViewById(R.id.tvTime);
            homeimg = v.findViewById(R.id.ivHomeTeam);
            awayimg = v.findViewById(R.id.ivAwayTeam);
            txtfecha = v.findViewById(R.id.tvdate);
            txthome = v.findViewById(R.id.tvHomeTeam);
            txtaway = v.findViewById(R.id.tvAwayTeam);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ResulsAdapter(List<partido> myDataset, Context context) {
        this.context=context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ResulsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_result, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // Formato a fecha y hora
        SimpleDateFormat sf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        sf.setLenient(true);
        Date date = null;
        try {
            date = sf.parse(mDataset.get(position).getFecha()+"+02:00");
            String fecha = new SimpleDateFormat("EEEE, d 'de' MMMM",new Locale("es","ES")).format(date);
            holder.txtfecha.setText(fecha.toUpperCase().charAt(0) + fecha.substring(1,fecha.length()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Cargar resultado
        if(mDataset.get(position).getGloc()!=null&&mDataset.get(position).getGloc()!=null){
            holder.tvResul.setText(mDataset.get(position).getGloc()+ " : "+ mDataset.get(position).getGvis());
            holder.tvTime.setText("FINALIZADO"); // #ToDo cambiar al minuto de partido
        }else{
            String hora = new SimpleDateFormat("H : mm",new Locale("es","ES")).format(date);
            if(!hora.equals("0 : 00")){
                holder.tvResul.setText(hora);
                holder.tvResul.setTextSize(20);
                holder.tvResul.setTypeface(Typeface.DEFAULT);
            }else{
                holder.tvResul.setVisibility(View.INVISIBLE);
            }
            holder.tvTime.setText("Estadio "+mDataset.get(position).getEstadio());
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

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
