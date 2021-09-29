package com.example.plantilla.ui.contratos;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Pago;
import com.example.plantilla.request.ApiClient;

import java.util.ArrayList;

public class DetallePagoViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Pago>> listapago;
    private ApiClient api= ApiClient.getApi();

    public LiveData<ArrayList<Pago>> Getlistapago(){
        if(listapago==null){
            listapago= new MutableLiveData<>();
        }
        return listapago;
    }
    public void CargarPagos(Bundle bundle){
        Contrato contrato=(Contrato) bundle.getSerializable("contrato");
        listapago.setValue(api.obtenerPagos(contrato));
    }
}