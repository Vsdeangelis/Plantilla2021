package com.example.plantilla.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.plantilla.R;
import com.example.plantilla.adapter.ContratosAdapter;
import com.example.plantilla.modelo.Inmueble;

import java.util.ArrayList;

public class ContratosFragment extends Fragment {
    private RecyclerView rvContratos;
    private ContratosAdapter contratosAdapter;
    private ContratosViewModel contratosVM;
    private TextView tvAviso;

    public static ContratosFragment newInstance() {
        return new ContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vistaContrato= inflater.inflate(R.layout.contratos_fragment, container, false);
        contratosVM=new ViewModelProvider(this).get(ContratosViewModel.class);
        rvContratos=(RecyclerView) vistaContrato.findViewById(R.id.rvContratos);
        GridLayoutManager glContrato= new GridLayoutManager(getContext(), 2,
                GridLayoutManager.VERTICAL, false);
        tvAviso=vistaContrato.findViewById(R.id.tvMensajeContrato);
        contratosVM.getAvisoContrato().observe(getViewLifecycleOwner(),
                new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        tvAviso.setVisibility(integer);
                    }
                });
        contratosVM.getInmuebleAlqui().observe(getViewLifecycleOwner(),
                new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                rvContratos.setLayoutManager(glContrato);
                contratosAdapter= new ContratosAdapter(inmuebles, vistaContrato, getLayoutInflater());
                rvContratos.setAdapter(contratosAdapter);
            }
        });
        contratosVM.CargarInmuConContrato();
        return vistaContrato;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contratosVM = new ViewModelProvider(this).get(ContratosViewModel.class);
        // TODO: Use the ViewModel
    }

}