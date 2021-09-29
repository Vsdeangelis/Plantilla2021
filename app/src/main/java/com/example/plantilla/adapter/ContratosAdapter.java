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

public class ContratosAdapter extends RecyclerView.Adapter<ContratosAdapter.ContratoHolder>{
    private ArrayList<Inmueble> inmuContratoList;
    private View vistaContrato;
    private LayoutInflater liContrato;

    public ContratosAdapter(ArrayList<Inmueble> inmuContratoList, View vistaContrato, LayoutInflater liContrato) {
        this.inmuContratoList = inmuContratoList;
        this.vistaContrato = vistaContrato;
        this.liContrato = liContrato;
    }

    @NonNull
    @Override
    ///Refrenciar a la vista item y pasarsela al ViewHolder
    public ContratosAdapter.ContratoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= liContrato.inflate(R.layout.item_detalle_contrato, parent, false);
        return new ContratosAdapter.ContratoHolder(view);
    }

    @Override
    ///se ejecuta por cada elemento de la lista
    public void onBindViewHolder(@NonNull ContratoHolder holder, int position) {
        Inmueble inmueble= inmuContratoList.get(position);
        holder.tvInmuDire.setText(inmuContratoList.get(position).getDireccion());
        Glide.with(vistaContrato.getContext())//obtiene el contexto dond poner la img
                .load(inmueble.getImagen())//url de la ig
                .diskCacheStrategy(DiskCacheStrategy.ALL)//optativa!guarda en el cache a la img
                .into(holder.ivInmuContrato);//carga la imagen
        holder.btVerContrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=new Bundle();
                b.putSerializable("inmueble", inmueble);
                Navigation.findNavController(vistaContrato).navigate(R.id.detalleContratosFragment, b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inmuContratoList.size();
    }

    public class ContratoHolder extends RecyclerView.ViewHolder{
        private ImageView ivInmuContrato;
        private TextView tvInmuDire;
        private Button btVerContrato;

        public ContratoHolder(@NonNull View itemView) {
            super(itemView);
            ivInmuContrato= itemView.findViewById(R.id.ivContrato);
            tvInmuDire=itemView.findViewById(R.id.tvDireContrato);
            btVerContrato= itemView.findViewById(R.id.btVerContrato);
        }
    }
}
