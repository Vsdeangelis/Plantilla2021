package com.example.plantilla.ui.contratos;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Pago;
import com.example.plantilla.request.ApiClient;

import java.util.ArrayList;

public class DetalleContratoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato>contrato;
    private Context context;
    private ApiClient api= ApiClient.getApi();

    public DetalleContratoViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
    }

    public LiveData<Contrato>GetContrato(){
        if(contrato==null){
            contrato= new MutableLiveData<>();
        }
        return contrato;
    }
    public void CargarContrato(Bundle bundle){
        Inmueble inmueble= (Inmueble) bundle.getSerializable("inmueble");
        contrato.setValue(api.obtenerContratoVigente(inmueble));
    }

}