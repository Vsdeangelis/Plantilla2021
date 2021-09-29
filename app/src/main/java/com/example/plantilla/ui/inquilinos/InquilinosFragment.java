package com.example.plantilla.ui.inquilinos;

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
import com.example.plantilla.adapter.InquilinosAdapter;
import com.example.plantilla.modelo.Inmueble;

import java.util.ArrayList;

public class InquilinosFragment extends Fragment {
    private RecyclerView rvInqui;
    private InquilinosAdapter inquilinosAdapter;
    private InquilinosViewModel vmInqui;

    public static InquilinosFragment newInstance() {
        return new InquilinosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.inquilinos_fragment, container, false);
        vmInqui=new ViewModelProvider(this).get(InquilinosViewModel.class);
        rvInqui=(RecyclerView) vista.findViewById(R.id.rvInquilinos);
        GridLayoutManager glInqui= new GridLayoutManager(getContext(), 2,
                GridLayoutManager.VERTICAL, false);
        vmInqui.getInmuAlquilados().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                rvInqui.setLayoutManager(glInqui);
                inquilinosAdapter= new InquilinosAdapter(inmuebles, vista, getLayoutInflater());
                rvInqui.setAdapter(inquilinosAdapter);
            }
        });
        vmInqui.CargarPropAlqui();
        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vmInqui = new ViewModelProvider(this).get(InquilinosViewModel.class);
        // TODO: Use the ViewModel
    }

}