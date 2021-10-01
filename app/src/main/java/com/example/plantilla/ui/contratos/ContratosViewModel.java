package com.example.plantilla.ui.contratos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

import java.util.ArrayList;

public class ContratosViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmuebleAlqui;
    private ApiClient api;

    public LiveData<ArrayList<Inmueble>> getInmuebleAlqui() {
        if(inmuebleAlqui==null){
            inmuebleAlqui= new MutableLiveData<>();
        }
        return inmuebleAlqui;
    }
    public void CargarInmuConContrato(){
        api= ApiClient.getApi();
        inmuebleAlqui.setValue(api.obtenerPropiedadesAlquiladas());
    }
}