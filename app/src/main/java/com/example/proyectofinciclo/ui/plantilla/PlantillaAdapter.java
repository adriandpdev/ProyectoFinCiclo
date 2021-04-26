package com.example.proyectofinciclo.ui.plantilla;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.models.jugador;
import com.example.proyectofinciclo.timelineprueba.LoadImage;

import java.util.List;


public class PlantillaAdapter extends RecyclerView.Adapter<PlantillaAdapter.MyViewHolder>{

    private List<jugador> mDataset;
    private FragmentTransaction transaction;
    private Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgjugador;
        public MyViewHolder(View v) {
            super(v);
            imgjugador = v.findViewById(R.id.imgjugador);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PlantillaAdapter(List<jugador> myDataset, Context context) {
        this.context=context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PlantillaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jugador, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // Cargamos el nombre

        // Cargamos la foto
        String imageHttpAddress = mDataset.get(position).getImg();
        new LoadImage(holder.imgjugador).execute("https://res.adriandiarteprieto.tk/"+imageHttpAddress+".png");
        holder.imgjugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                String currentValue = mDataset.get(position).getName();

                Intent intent = new Intent(context, detailsActivity.class);
                intent.putExtra("position", mDataset.get(position).getEstadioId());
                context.startActivity(intent);
                */


                /*
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
