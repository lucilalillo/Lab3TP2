package com.example.lab3tp2.Ui.Registro;

import android.os.Bundle;

import com.example.lab3tp2.Modelo.Usuario;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lab3tp2.R;

public class Registro extends AppCompatActivity {

    private TextView tvInfo;
    private EditText etDni, etApellido, etNombre, etRMail, etRPassword;
    private Button btGuardar;
    private RegistroViewModel registroViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        registroViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);
        iniciarVista();
        registroViewModel.getUsuarioM().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etDni.setText(usuario.getDni()+"");
                etApellido.setText(usuario.getApellido());
                etNombre.setText(usuario.getNombre());
                etRMail.setText(usuario.getMail());
                etRPassword.setText(usuario.getPassword());
            }
        });
        registroViewModel.getEstadoM().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvInfo.setVisibility(integer);
            }
        });
        registroViewModel.getMensajeM().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvInfo.setText(s);
            }
        });
        registroViewModel.cargarUsuario();
    }
    private void iniciarVista(){
        tvInfo = findViewById(R.id.tvInfo);
        etDni = findViewById(R.id.etDni);
        etApellido = findViewById(R.id.etApellido);
        etNombre = findViewById(R.id.etNombre);
        etRMail = findViewById(R.id.etRMail);
        etRPassword = findViewById(R.id.etRPassword);
        btGuardar = findViewById(R.id.btGuardar);
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registroViewModel.guardarUsuario(
                        Integer.parseInt(etDni.getText().toString()),
                        etApellido.getText().toString(),
                        etNombre.getText().toString(),
                        etRMail.getText().toString(),
                        etRPassword.getText().toString()
                );
            }
        });
    }
}