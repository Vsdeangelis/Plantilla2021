package com.example.plantilla.ui.inmuebles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

import java.util.ArrayList;

///se encarga de traer la lista de inmuebles de la api o de una BD
public class InmueblesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private ApiClient api;

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if(inmuebles==null){
            inmuebles= new MutableLiveData<>();
        }
        return inmuebles ;
    }
    public void CargarInmuebles(){
        api= ApiClient.getApi();
        inmuebles.setValue(api.obtnerPropiedades());
    }


}