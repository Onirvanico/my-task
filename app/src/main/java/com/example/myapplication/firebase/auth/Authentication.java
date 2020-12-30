package com.example.myapplication.firebase.auth;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.firebase.ConfigFirebase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication {

    private final FirebaseAuth auth;
    private CallbackAuth callbackAuth;

    public Authentication() {
        this.auth = ConfigFirebase.getFirebaseAuth();
    }

    public void createUserAndPassword(String email, String senha, CallbackAuth callback) {
        callbackAuth = callback;
        auth.createUserWithEmailAndPassword(
                email,
                senha)
                .addOnCompleteListener(this::inFinalize);
    }

    public void signWithEmailAndPassword(String email, String senha, CallbackAuth callback) {
        callbackAuth = callback;
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this::inFinalize);
    }

    private void inFinalize(Task<AuthResult> task) {
        if (task.isSuccessful()) {
            callbackAuth.isSuccessfullAuth(task.getResult().getUser());
        } else {
            task.getException().printStackTrace();
            Log.e("ErrorCreateAccount",
                    "inFinalize: " + task.getException().getMessage());
            callbackAuth.isFailureAuth(task.getException().getMessage());
        }
    }

    public interface CallbackAuth {
        void isSuccessfullAuth(FirebaseUser user);

        void isFailureAuth(String error);
    }
}
