package com.example.plantilla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciar();
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
        vm.getMostrar().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mensaje.setVisibility(integer);
            }
        });
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
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ApiClient api=ApiClient.getApi();
        api.obtenerUsuarioActual();
    }
}