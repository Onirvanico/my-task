package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.firebase.ConfigFirebase;
import com.example.myapplication.firebase.database.UserRepository;
import com.example.myapplication.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth auth = ConfigFirebase.getFirebaseAuth();
       // user = new UserRepository(this).getUserData(auth.getCurrentUser().getUid());
    }

    @Override
    protected void onResume() {
        try {
            Thread.sleep(4000);
            new AlertDialog.Builder(this)
                    .setTitle("Dados dos usuário")
                 /*   .setMessage("Suas informações foram salvas e retornadas" +
                                user.getNome() + " " + user.getEmail() + " " + user.getId())*/
                    .setPositiveButton("ok", null)
                    .create()
                    .show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onResume();
    }
}