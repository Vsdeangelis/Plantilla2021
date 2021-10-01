package com.example.plantilla.ui.logout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.plantilla.databinding.LogoutFragmentBinding;

public class LogoutFragment extends Fragment {
    private LogoutFragmentBinding binding;

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= LogoutFragmentBinding.inflate(inflater, container, false);
        View root=binding.getRoot();
        mostrarDialogo();
        return root;
    }
       @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    private void mostrarDialogo(){
        new AlertDialog.Builder(getContext())
                .setTitle("SALIR")
                .setMessage("Desea cerrar la aplicación?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Usted no cerró su sesión", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

}