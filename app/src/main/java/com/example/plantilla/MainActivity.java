package com.example.plantilla;

import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.plantilla.request.ApiClient;

public class MainActivity extends AppCompatActivity {
    private EditText usuario, password;
    private TextView mensaje;
    private MainViewModel vm;
    private SensorManager sensorMov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciar();
        sensorMov= (SensorManager) getSystemService(SENSOR_SERVICE);
        misPermisos();
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
        vm.getMostrar().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mensaje.setVisibility(integer);
            }
        });
        vm.getSensor().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel: " + s));
                startActivity(intent);
            }
        });
        vm.alertaMovimiento(sensorMov);
    }

    @SuppressLint("ObsoleteSdkInt")
    private void misPermisos(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M &&checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
        }
    }

    private void iniciar(){
        usuario=findViewById(R.id.etUsuario);
        password=findViewById(R.id.etContrasenia);
        mensaje=findViewById(R.id.tvMensaje);
        Button ingresar = findViewById(R.id.btLogin);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.iniciarSesion(usuario.getText().toString(), password.getText().toString());
                usuario.setText("");
                password.setText("");
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ApiClient api=ApiClient.getApi();
        api.obtenerUsuarioActual();
    }
    @Override
    protected void onPause() {
        super.onPause();
        vm.detenerAlerta(sensorMov);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        vm.alertaMovimiento(sensorMov);
    }

    @Override
    protected void onStop() {
        super.onStop();
        vm.detenerAlerta(sensorMov);
    }

}