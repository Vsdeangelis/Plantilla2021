package com.example.plantilla.ui.inmuebles;

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
import com.example.plantilla.adapter.InmuebleAdapter;
import com.example.plantilla.modelo.Inmueble;

import java.util.ArrayList;

public class InmueblesFragment extends Fragment {
    private RecyclerView rvInmu;
    private InmuebleAdapter inmuAdap;
    private InmueblesViewModel inmuVm;

    public static InmueblesFragment newInstance() {
        return new InmueblesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.inmuebles_fragment, container, false);
        inmuVm=new ViewModelProvider(this).get(InmueblesViewModel.class);
        rvInmu= (RecyclerView) vista.findViewById(R.id.rvInmuebles);
        GridLayoutManager lmn= new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        inmuVm.getInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                rvInmu.setLayoutManager(lmn);
                inmuAdap= new InmuebleAdapter(inmuebles, vista, getLayoutInflater());
                rvInmu.setAdapter(inmuAdap);
            }
        });
        inmuVm.CargarInmuebles();
        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inmuVm = new ViewModelProvider(this).get(InmueblesViewModel.class);
        // TODO: Use the ViewModel
    }

}