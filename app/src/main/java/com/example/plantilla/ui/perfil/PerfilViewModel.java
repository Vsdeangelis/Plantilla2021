package com.example.plantilla.ui.perfil;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

public class PerfilViewModel extends ViewModel {
    private MutableLiveData<Integer> editar;
    private MutableLiveData<Integer> guardar;
    private MutableLiveData<Boolean>editTexts;
    private MutableLiveData<Propietario>usuario;
    private ApiClient api= ApiClient.getApi();

    public LiveData<Integer> getEditar() {
        if(editar==null){
            editar= new MutableLiveData<>();
        }
        return editar;
    }
    public LiveData<Integer> getGuardar() {
        if(guardar==null){
            guardar= new MutableLiveData<>();
        }
        return guardar;
    }
    public LiveData<Boolean> getEditTexts() {
        if(editTexts==null){
            editTexts= new MutableLiveData<>();
        }
        return editTexts;
    }
    public LiveData<Propietario> getUsuario() {
        if(usuario==null){
            usuario= new MutableLiveData<>();
        }
        return usuario;
    }
    public void ObtenerProp(){
        Propietario p= api.obtenerUsuarioActual();
        usuario.setValue(p);
    }
    public void ModificarProp(Propietario p){
        api.actualizarPerfil(p);
        guardar.setValue(View.INVISIBLE);
        editar.setValue(View.VISIBLE);
    }
    public void CambiarBoton(){
        editTexts.setValue(true);
        editar.setValue(View.INVISIBLE);
        guardar.setValue(View.VISIBLE);
    }

}