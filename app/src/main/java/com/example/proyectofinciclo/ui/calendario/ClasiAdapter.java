package com.example.proyectofinciclo.ui.calendario;

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
import com.example.proyectofinciclo.models.equipo;
import com.example.proyectofinciclo.timelineprueba.LoadImage;

import java.util.List;

public class ClasiAdapter extends RecyclerView.Adapter<ClasiAdapter.MyViewHolder>{

    private List<equipo> mDataset;
    private FragmentTransaction transaction;
    private Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView tvPosi;
        public TextView tvEqui;
        public TextView tvPuntos;
        public ImageView ivEscudo;
        public MyViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.cvResul);
            tvPosi = v.findViewById(R.id.tvPosi);
            tvEqui = v.findViewById(R.id.tvEqui);
            tvPuntos = v.findViewById(R.id.tvPuntos);
            ivEscudo = v.findViewById(R.id.ivEscudo);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ClasiAdapter(List<equipo> myDataset, Context context) {
        this.context=context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ClasiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_clasi, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // Cargar datos
        holder.tvPosi.setText(position+1);
        holder.tvEqui.setText(mDataset.get(position).getEqui());
        holder.tvPuntos.setText(mDataset.get(position).getPuntos());
        // Cargar escudo
        String imageHttpAddresshome = mDataset.get(position).getEqui().replace(" ","");
        new LoadImage(holder.ivEscudo).execute("https://res.adriandiarteprieto.tk/escudos/"+imageHttpAddresshome+".png");
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
