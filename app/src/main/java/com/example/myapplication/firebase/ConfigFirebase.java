package com.example.myapplication.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigFirebase {

    private static FirebaseAuth auth;
    private static FirebaseDatabase db;
    public static FirebaseAuth getFirebaseAuth() {
        return auth == null ?
                auth = FirebaseAuth.getInstance() :
                auth;
    }

    public static FirebaseDatabase getFirebaseDataBase() {
        return db == null ?
                db = FirebaseDatabase.getInstance() :
                db;

    }
}
