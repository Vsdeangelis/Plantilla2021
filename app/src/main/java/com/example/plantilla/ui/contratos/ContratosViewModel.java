package com.example.plantilla.ui.contratos;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

import java.util.ArrayList;

public class ContratosViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmuebleAlqui;
    private MutableLiveData<Integer>avisoContrato;
    private ApiClient api;

    public LiveData<ArrayList<Inmueble>> getInmuebleAlqui() {
        if(inmuebleAlqui==null){
            inmuebleAlqui= new MutableLiveData<>();
        }
        return inmuebleAlqui;
    }
    public LiveData<Integer>getAvisoContrato(){
        if(avisoContrato==null){
            avisoContrato= new MutableLiveData<>();
        }
        return avisoContrato;
    }
    public void CargarInmuConContrato(){
        api= ApiClient.getApi();
        ArrayList<Inmueble>lista= api.obtenerPropiedadesAlquiladas();
        if(lista.size()>0){
            avisoContrato.setValue(View.INVISIBLE);
            inmuebleAlqui.setValue(lista);
        }else{
            avisoContrato.setValue(View.VISIBLE);
        }

    }
}