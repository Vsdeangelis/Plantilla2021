package com.example.plantilla.ui.inquilinos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Inquilino;
import com.example.plantilla.request.ApiClient;

public class DetalleInquinoViewModel extends ViewModel {
    private MutableLiveData<Inquilino>inqui;
    private ApiClient api;
    private Inmueble inmueble;
    public LiveData<Inquilino> getInqui() {
        if(inqui==null){
            inqui= new MutableLiveData<>();
        }
        return inqui;
    }
    public void setInqui(Bundle bundle){
        api= ApiClient.getApi();
        inmueble=(Inmueble) bundle.getSerializable("inmueble");
        inqui.setValue(api.obtenerInquilino(inmueble));

    }

}