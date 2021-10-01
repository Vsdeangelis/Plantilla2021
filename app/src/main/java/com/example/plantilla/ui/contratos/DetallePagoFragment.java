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

import com.example.plantilla.R;
import com.example.plantilla.adapter.PagosAdapter;
import com.example.plantilla.modelo.Pago;

import java.util.ArrayList;

public class DetallePagoFragment extends Fragment {
    private DetallePagoViewModel pagoViewModel;
    private PagosAdapter pagosAdapter;
    private RecyclerView rvPagos;
    public static DetallePagoFragment newInstance() {
        return new DetallePagoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vistaPago= inflater.inflate(R.layout.detalle_pago_fragment, container, false);
        pagoViewModel=new ViewModelProvider(this).get(DetallePagoViewModel.class);
        rvPagos=(RecyclerView) vistaPago.findViewById(R.id.rvPagos);
        GridLayoutManager glPago= new GridLayoutManager(getContext(), 1,
                GridLayoutManager.VERTICAL, false);

        pagoViewModel.Getlistapago().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
            @Override
            public void onChanged(ArrayList<Pago> pagos) {
                rvPagos.setLayoutManager(glPago);
                pagosAdapter= new PagosAdapter(pagos, getLayoutInflater(), vistaPago);
                rvPagos.setAdapter(pagosAdapter);
            }
        });
        pagoViewModel.CargarPagos(getArguments());
        return vistaPago;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pagoViewModel = new ViewModelProvider(this).get(DetallePagoViewModel.class);
    }

}