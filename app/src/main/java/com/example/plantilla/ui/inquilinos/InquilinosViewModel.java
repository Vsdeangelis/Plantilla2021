package com.example.plantilla.ui.inquilinos;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

import java.util.ArrayList;

public class InquilinosViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Inmueble>>inmuAlquilados;
    private MutableLiveData<Integer>aviso;
    private ApiClient api;

    public LiveData<ArrayList<Inmueble>> getInmuAlquilados() {
        if(inmuAlquilados==null){
            inmuAlquilados= new MutableLiveData<>();
        }
        return inmuAlquilados;
    }
    public LiveData<Integer>GetAviso(){
        if(aviso==null){
            aviso= new MutableLiveData<>();
        }
        return aviso;
    }
    public void CargarPropAlqui(){
        api= ApiClient.getApi();
        ArrayList<Inmueble>lista= api.obtenerPropiedadesAlquiladas();
        if(lista.size()>0){
            aviso.setValue(View.INVISIBLE);
            inmuAlquilados.setValue(lista);
        }else{
            aviso.setValue(View.VISIBLE);
        }

    }
}