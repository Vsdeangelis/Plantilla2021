package com.example.plantilla.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plantilla.R;
import com.example.plantilla.databinding.DetalleContratoFragmentBinding;
import com.example.plantilla.modelo.Contrato;

public class DetalleContratoFragment extends Fragment {
    private DetalleContratoViewModel vmDetalleContr;
    private DetalleContratoFragmentBinding binding;

    public static DetalleContratoFragment newInstance() {
        return new DetalleContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DetalleContratoFragmentBinding.inflate(inflater, container, false);
        View vistaDetalleContrato= binding.getRoot();
        vmDetalleContr=new ViewModelProvider(this).get(DetalleContratoViewModel.class);
        vmDetalleContr.GetContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
               binding.tvCodigoContrato.setText(String.valueOf(contrato.getIdContrato()));
               binding.tvFechaInicio.setText(contrato.getFechaInicio());
               binding.tvFechaFin.setText(contrato.getFechaFin());
               binding.tvMotoAlquiler.setText(String.valueOf(contrato.getInmueble().getPrecio()));
               binding.tvNameInquilino.setText(contrato.getInquilino().getApellido()+", "+contrato.getInquilino().getNombre());
               binding.tvDireInmueble.setText(contrato.getInmueble().getDireccion());
               binding.btPagos.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Bundle bundle= new Bundle();
                       bundle.putSerializable("contrato", contrato);
                       Navigation.findNavController(vistaDetalleContrato).navigate(R.id.detallePagoFragment, bundle);
                   }
               });
            }
        });
        vmDetalleContr.CargarContrato(getArguments());
        return vistaDetalleContrato;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vmDetalleContr = new ViewModelProvider(this).get(DetalleContratoViewModel.class);
    }

}