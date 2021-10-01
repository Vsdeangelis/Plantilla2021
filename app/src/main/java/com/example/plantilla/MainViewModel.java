package com.example.plantilla;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

import java.util.List;


public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<String>movimiento;
    private MutableLiveData<Integer> mostrar;
    private final Context context;
    private LeeSensor leeSensor;

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
    public LiveData<String> getSensor(){
        if(movimiento==null){
            movimiento= new MutableLiveData<>();
        }
        return movimiento;
    }
    public void alertaMovimiento(SensorManager sensorManager){
        leeSensor= new LeeSensor();
        List<Sensor>sensorList= sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(sensorList.size()>0){
            sensorManager.registerListener(leeSensor, sensorList.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public void detenerAlerta(SensorManager sensorManager){
        sensorManager.unregisterListener(leeSensor);
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
    private class LeeSensor implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.values[0]>5 &&event.values[1]>5){
                movimiento.setValue("2664501231");
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    }

}
