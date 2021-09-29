package com.example.plantilla.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class InquilinosAdapter extends RecyclerView.Adapter<InquilinosAdapter.Viewholder> {
    private ArrayList<Inmueble> listaInmu;
    private View vistaInqui;
    private LayoutInflater inflater;

    public InquilinosAdapter(ArrayList<Inmueble> listaInmu, View vistaInqui, LayoutInflater inflater) {
        this.listaInmu = listaInmu;
        this.vistaInqui = vistaInqui;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    ///Refrenciar a la vista item y pasarsela al ViewHolder
    public InquilinosAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.item_detalle_inquino, parent, false);
        return new Viewholder(view);
    }

    @Override
    ///se ejecuta por cada elemento de la lista
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Inmueble inmueble= listaInmu.get(position);
        holder.tvDireInqui.setText(listaInmu.get(position).getDireccion());
        Glide.with(vistaInqui.getContext())//obtiene el contexto dond poner la img
                .load(inmueble.getImagen())//url de la ig
                .diskCacheStrategy(DiskCacheStrategy.ALL)//optativa!guarda en el cache a la img
                .into(holder.ivInmuAlqui);//carga la imagen
        holder.btVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=new Bundle();
                b.putSerializable("inmueble", inmueble);
                Navigation.findNavController(vistaInqui).navigate(R.id.detalleInquinoFragment, b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaInmu.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView tvDireInqui;
        private ImageView ivInmuAlqui;
        private Button btVer;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvDireInqui= itemView.findViewById(R.id.tvDireInqui);
            ivInmuAlqui=itemView.findViewById(R.id.ivInmuInquilino);
            btVer= itemView.findViewById(R.id.btVer);
        }
    }

}
