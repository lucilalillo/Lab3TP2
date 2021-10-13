package com.example.lab3tp2.Ui.Login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab3tp2.R;
import com.example.lab3tp2.Ui.Registro.Registro;

public class MainActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private EditText etMail, etPassword;
    private TextView tvMensaje;
    private Button btLogin, btRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        iniciarVista();
        loginViewModel.getMensajeM().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer i) {
                tvMensaje.setVisibility(i);
            }
        });
    }
    private void iniciarVista(){
        tvMensaje = findViewById(R.id.tvMensaje);
        etMail = findViewById(R.id.etMail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegistro = findViewById(R.id.btRegistro);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                loginViewModel.iniciarSesion(etMail.getText().toString(), etPassword.getText().toString());
            }
        });
        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), Registro.class);
                startActivity(intent);
            }
        });
    }
}