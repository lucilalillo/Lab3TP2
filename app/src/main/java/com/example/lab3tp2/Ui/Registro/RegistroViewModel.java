package com.example.lab3tp2.Ui.Registro;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lab3tp2.Modelo.Usuario;
import com.example.lab3tp2.Request.ApiClient;

public class RegistroViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioM;
    private MutableLiveData<String> mensajeM;
    private MutableLiveData<Integer> estadoM;
    private Context context;

    public RegistroViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuarioM() {
        if(usuarioM == null){
            usuarioM = new MutableLiveData<>();
        }
        return usuarioM;
    }

    public LiveData<String> getMensajeM() {
        if(mensajeM == null){
            mensajeM = new MutableLiveData<>();
        }
        return mensajeM;
    }

    public LiveData<Integer> getEstadoM() {
        if(estadoM == null){
            estadoM = new MutableLiveData<>();
        }
        return estadoM;
    }

    public void cargarUsuario(){
        Usuario user = ApiClient.leer(context);
        if(user != null){
            usuarioM.setValue(user);
            estadoM.setValue(4);
        }
    }
    public void guardarUsuario(int dni, String a, String n, String m, String p){
        Usuario usuario = new Usuario(dni, a, n, m, p);
        if(usuario != null){
            ApiClient.guardar(context, usuario);
            estadoM.setValue(0);
            mensajeM.setValue("Guardado con éxito");
        }else{
            estadoM.setValue(0);
            mensajeM.setValue("No se pudo guardar");
        }
    }
}
