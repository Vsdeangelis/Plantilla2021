package com.example.plantilla.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.plantilla.R;
import com.example.plantilla.modelo.Inmueble;

import java.util.ArrayList;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.Viewholder>{
    private ArrayList<Inmueble> listaInmu;
    private View vistaInmu;
    private LayoutInflater inflater;

    public InmuebleAdapter(ArrayList<Inmueble> listaInmu, View vistaInmu, LayoutInflater inflater) {
        this.listaInmu = listaInmu;
        this.vistaInmu = vistaInmu;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    ///Refrenciar a la vista item y pasarsela al ViewHolder
    public InmuebleAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.item_detalle_inmueble, parent, false);
        return new Viewholder(view);
    }

    @Override
    ///se ejecuta por cada elemento de la lista
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Inmueble inmueble= listaInmu.get(position);
        holder.tvDireccion.setText(listaInmu.get(position).getDireccion());
        holder.tvPrecio.setText("$"+listaInmu.get(position).getPrecio()+".-");
        Glide.with(vistaInmu.getContext())//obtiene el contexto dond poner la img
                .load(inmueble.getImagen())//url de la ig
                .diskCacheStrategy(DiskCacheStrategy.ALL)//optativa!guarda en el cache a la img
                .into(holder.ivInmueble);//carga la imagen
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=new Bundle();
                b.putSerializable("inmueble", inmueble);
                Navigation.findNavController(vistaInmu).navigate(R.id.detalleInmuebleFragment, b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaInmu.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView tvDireccion, tvPrecio;
        private ImageView ivInmueble;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvDireccion= itemView.findViewById(R.id.tvDireccion);
            tvPrecio= itemView.findViewById(R.id.tvPrecio);
            ivInmueble=itemView.findViewById(R.id.ivInmueble);
        }
    }
}
