package com.example.plantilla.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plantilla.R;
import com.example.plantilla.databinding.DetalleInmuebleFragmentBinding;
import com.example.plantilla.databinding.DetalleInquinoFragmentBinding;
import com.example.plantilla.modelo.Inquilino;
import com.example.plantilla.ui.inmuebles.DetalleInmuebleViewModel;

public class DetalleInquinoFragment extends Fragment {
    private DetalleInquinoFragmentBinding binding;
    private DetalleInquinoViewModel detInquiVM;

    public static DetalleInquinoFragment newInstance() {
        return new DetalleInquinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=DetalleInquinoFragmentBinding.inflate(inflater, container, false);
        View root= binding.getRoot();
        detInquiVM= new ViewModelProvider(this).get(DetalleInquinoViewModel.class);
        detInquiVM.getInqui().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                binding.tvCodInqui.setText("Codigo: \n"+inquilino.getIdInquilino());
                binding.tvNomInqui.setText("Nombre: \n"+inquilino.getNombre());
                binding.tvApeInqui.setText("Apellido: \n"+inquilino.getApellido());
                binding.tvDniInqui.setText("Dni: \n"+inquilino.getDNI());
                binding.tvMailInqui.setText("E-Mail: \n"+inquilino.getEmail());
                binding.tvTelInqui.setText("Teléfono: \n"+inquilino.getTelefono());
                binding.tvGarante.setText("Garante: \n"+inquilino.getNombreGarante());
                binding.tvTelGarante.setText("Tel. garante: \n"+inquilino.getTelefonoGarante());
            }
        });
        detInquiVM.setInqui(getArguments());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detInquiVM = new ViewModelProvider(this).get(DetalleInquinoViewModel.class);
        // TODO: Use the ViewModel
    }

}