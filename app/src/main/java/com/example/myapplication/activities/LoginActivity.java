package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.firebase.auth.Authentication;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private Button btLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView linkCadastro = findViewById(R.id.textLinkActivityCadastrar);
        linkCadastro.setOnClickListener(this::goToActivityCadastrar);

        bindViews();

        btLogar.setOnClickListener(view -> new Authentication().signWithEmailAndPassword(editEmail.getText().toString(),
                editSenha.getText().toString(), new Authentication.CallbackAuth() {
                    @Override
                    public void isSuccessfullAuth(FirebaseUser user) {
                        startActivity(new Intent(
                                LoginActivity.this,
                                MainActivity.class));
                    }

                    @Override
                    public void isFailureAuth(String error) {

                    }
                }));
    }

    private void bindViews() {
        editEmail = findViewById(R.id.editTextLoginEmail);
        editSenha = findViewById(R.id.editTextLoginSenha);
        btLogar = findViewById(R.id.btEntrar);
    }

    private void goToActivityCadastrar(View view) {
        startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
    }
}