package com.example.proyectofinciclo.ui.calendario;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinciclo.MainActivity;
import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.models.resul;
import com.example.proyectofinciclo.timelineprueba.LoadImage;

import java.util.List;

public class ResulsAdapter extends RecyclerView.Adapter<ResulsAdapter.MyViewHolder>{

    private List<resul> mDataset;
    private FragmentTransaction transaction;
    private Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView tvTime,tvResul;
        public TextView txtlocate;
        public TextView txtequi;
        public ImageView awayimg,homeimg;
        public MyViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.cvResul);
            tvResul = v.findViewById(R.id.tvResul);
            tvTime = v.findViewById(R.id.tvTime);
            homeimg = v.findViewById(R.id.ivHomeTeam);
            awayimg = v.findViewById(R.id.ivAwayTeam);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ResulsAdapter(List<resul> myDataset, Context context) {
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
        holder.tvResul.setText(mDataset.get(position).getHomegol()+ ":"+ mDataset.get(position).getAwaygol());
        if(mDataset.get(position).getPenals()=="1"){
            holder.tvTime.setText(mDataset.get(position).getDate());
        }else{

            holder.tvTime.setText("FINALIZADO");
        }
        // Imagenes
            /*
            String imageHttpAddress = mDataset.get(position).();
            new LoadImage(holder.img).execute(imageHttpAddress);
            comprobarimg(holder, position);
            */
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                String currentValue = mDataset.get(position).getName();
                Log.d("CardView", "CardView Clicked: " + currentValue.toString());

                Intent intent = new Intent(context, detailsActivity.class);
                intent.putExtra("position", mDataset.get(position).getEstadioId());
                context.startActivity(intent);
                */


            /* //METODO 2, NOT WORK
            androidx.fragment.app.Fragment nuevoFragmento = new EstadioInvFragment(mDataset.get(position).getEstadioId());
            transaction = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, nuevoFragmento);
            transaction.addToBackStack(null);
            transaction.commit();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
