package com.example.plantilla.ui.inquilinos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

import java.util.ArrayList;


public class InquilinosViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Inmueble>>inmuAlquilados;
    private ApiClient api;

    public LiveData<ArrayList<Inmueble>> getInmuAlquilados() {
        if(inmuAlquilados==null){
            inmuAlquilados= new MutableLiveData<>();
        }
        return inmuAlquilados;
    }
    public void CargarPropAlqui(){
        api= ApiClient.getApi();
        inmuAlquilados.setValue(api.obtenerPropiedadesAlquiladas());
    }
}