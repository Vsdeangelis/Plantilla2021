package com.example.plantilla.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.plantilla.databinding.DetalleInmuebleFragmentBinding;
import com.example.plantilla.modelo.Inmueble;

public class DetalleInmuebleFragment extends Fragment {
    private DetalleInmuebleFragmentBinding binding;
    private DetalleInmuebleViewModel mViewModel;

    public static DetalleInmuebleFragment newInstance() {
        return new DetalleInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=DetalleInmuebleFragmentBinding.inflate(inflater, container, false);
        View root= binding.getRoot();
        mViewModel= new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);
        mViewModel.getInmu().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                binding.tvCodigo.setText("Codigo: \n"+inmueble.getIdInmueble());
                binding.tvAmbientes.setText("Ambientes: \n"+inmueble.getAmbientes());
                binding.tvDireccion.setText("Direccion: \n"+inmueble.getDireccion());
                binding.tvPrecio.setText("Precio: \n"+inmueble.getPrecio());
                binding.tvUso.setText("Uso: \n"+inmueble.getUso());

                Glide.with(getContext())//obtiene el contexto dond poner la img
                        .load(inmueble.getImagen())//url de la ig
                        .diskCacheStrategy(DiskCacheStrategy.ALL)//optativa!guarda en el cache a la img
                        .into(binding.ivInmuebleDetalle);//carga la imagen
                binding.tvTipo.setText("Tipo: \n"+inmueble.getTipo());
                binding.cbDisponible.setChecked(inmueble.isEstado());
                binding.cbDisponible.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewModel.actualizarInmu(binding.cbDisponible.isChecked());
                    }
                });
            }
        });
        mViewModel.setInmu(getArguments());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);
        // TODO: Use the ViewModel
    }

}