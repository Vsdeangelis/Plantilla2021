package com.example.plantilla;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> mostrar;
    private final Context context;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
    }
    public LiveData<Integer> getMostrar(){
        if(mostrar==null){
            mostrar= new MutableLiveData<>();
        }
        return mostrar;
    }
    public void iniciarSesion(String u, String c){
        ApiClient api= ApiClient.getApi();
        Propietario p = api.login(u, c);

        if(p!=null){
            mostrar.setValue(View.INVISIBLE);
            Intent intent=new Intent(context, InicioActivity.class);
            Bundle bundle= new Bundle();
            bundle.putSerializable("propietario", p);
            intent.putExtra("propietario", bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else{
            mostrar.setValue(View.VISIBLE);
        }
    }

}
