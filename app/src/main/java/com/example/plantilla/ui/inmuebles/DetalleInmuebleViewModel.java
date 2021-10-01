package com.example.plantilla.ui.inmuebles;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

public class DetalleInmuebleViewModel extends ViewModel {
    private MutableLiveData<Inmueble> inmu;
    ApiClient api= ApiClient.getApi();
    private Inmueble i;
    public LiveData<Inmueble> getInmu() {
        if(inmu==null){
            inmu= new MutableLiveData<>();
        }
        return inmu;
    }

    public void setInmu(Bundle b) {
        i= (Inmueble) b.getSerializable("inmueble");
        inmu.setValue(i);
    }
    public void actualizarInmu(Boolean b){
        i.setEstado(b);
        api.actualizarInmueble(i);
    }
}