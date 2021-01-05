package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.firebase.auth.Authentication;
import com.example.myapplication.firebase.database.UserRepository;
import com.example.myapplication.model.User;
import com.google.firebase.auth.FirebaseUser;

public class CadastroActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editNome;
    private EditText editSenha;
    private Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        bindView();

        btCadastrar.setOnClickListener(this::onClick);


    }

    private void onClick() {

        User receivedUser = FillUser();

        new Authentication()
                .createUserAndPassword(receivedUser.getEmail(), receivedUser.getSenha(), new Authentication.CallbackAuth() {
                    @Override
                    public void isSuccessfullAuth(FirebaseUser user) {
                        if (user != null) {
                            receivedUser.setId(user.getUid());

                            new UserRepository(CadastroActivity.this)
                                    .saveOrModifyUser(receivedUser);
                            goToLoginActivity();

                        }
                    }

                    @Override
                    public void isFailureAuth(String error) {
                        Toast.makeText(CadastroActivity.this,
                                "Falha ao tentar criar a conta" + error,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void goToLoginActivity() {
        startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
        finish();
    }

    private User FillUser() {
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();
        String nome = editNome.getText().toString();
        return new User(nome, email, senha);
    }

    private void bindView() {
        editEmail = findViewById(R.id.editTextEmail);
        editNome = findViewById(R.id.editTextTextNome);
        editSenha = findViewById(R.id.editTextSenha);
        btCadastrar = findViewById(R.id.button);
    }

    private void onClick(View view) {
        onClick();
    }
}