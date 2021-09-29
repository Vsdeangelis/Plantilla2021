package com.example.plantilla.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.plantilla.R;
import com.example.plantilla.databinding.PerfilFragmentBinding;
import com.example.plantilla.modelo.Propietario;


public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    private PerfilFragmentBinding binding;
    private TextView codigo, dni, nombre, apellido, mail, pass, tel;
    private Button edit, save;
    private ImageView foto;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel= new ViewModelProvider(this).get(PerfilViewModel.class);
        binding= PerfilFragmentBinding.inflate(inflater, container, false);
        View root= binding.getRoot();
        inicializar(root);
        mViewModel.getUsuario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
                codigo.setText(String.valueOf(p.getId()));
                dni.setText(p.getDni().toString());
                nombre.setText(p.getNombre());
                apellido.setText(p.getApellido());
                mail.setText(p.getEmail());
                pass.setText(p.getContraseña());
                tel.setText(p.getTelefono());
                foto.setImageResource(p.getAvatar());
            }
        });
        mViewModel.getEditar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer i) {
                edit.setVisibility(i);
            }
        });
        mViewModel.getGuardar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer n) {
                save.setVisibility(n);
            }
        });
        mViewModel.getEditTexts().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean edit) {
                dni.setEnabled(edit);
                nombre.setEnabled(edit);
                apellido.setEnabled(edit);
                mail.setEnabled(edit);
                pass.setEnabled(edit);
                tel.setEnabled(edit);
            }
        });
        mViewModel.ObtenerProp();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding= PerfilFragmentBinding.inflate(getLayoutInflater());
        registerForContextMenu(binding.getRoot());
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel
    }
    public void inicializar(View v){
        codigo= v.findViewById(R.id.etCodigo);
        dni= v.findViewById(R.id.etDni);
        nombre=v.findViewById(R.id.etNombre);
        apellido= v.findViewById(R.id.etApellido);
        mail=v.findViewById(R.id.etMail);
        pass=v.findViewById(R.id.etContrasenia);
        tel=v.findViewById(R.id.etTelefono);
        foto=v.findViewById(R.id.ivAvatarPerfil);
        edit=v.findViewById(R.id.btEditar);
        save=v.findViewById(R.id.btGuardar);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.CambiarBoton();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario p= new Propietario();
                p.setId(Integer.parseInt(codigo.getText().toString()));
                p.setDni(Long.parseLong(dni.getText().toString()));
                p.setNombre(nombre.getText().toString());
                p.setApellido(apellido.getText().toString());
                p.setEmail(mail.getText().toString());
                p.setContraseña(pass.getText().toString());
                p.setTelefono(tel.getText().toString());
                mViewModel.ModificarProp(p);
            }
        });
    }

}