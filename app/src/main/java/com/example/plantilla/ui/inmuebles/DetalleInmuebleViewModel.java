package com.example.plantilla.ui.inmuebles;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;

public class DetalleInmuebleViewModel extends ViewModel {
    private MutableLiveData<Inmueble> inmu;
    public LiveData<Inmueble> getInmu() {
        if(inmu==null){
            inmu= new MutableLiveData<>();
        }
        return inmu;
    }

    public void setInmu(Bundle b) {
        Inmueble i= (Inmueble) b.getSerializable("inmueble");
        inmu.setValue(i);
    }
}