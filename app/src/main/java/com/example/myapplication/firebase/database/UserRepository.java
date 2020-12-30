package com.example.myapplication.firebase.database;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.firebase.ConfigFirebase;
import com.example.myapplication.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import static com.example.myapplication.firebase.ConstDataBase.TABLE_USERS;

public class UserRepository {

    private final FirebaseDatabase db;
    private final DatabaseReference tb_users;
    private final Context context;
    private User user = null;

    public UserRepository(Context context) {
        this.db = ConfigFirebase.getFirebaseDataBase();
        this.tb_users = db.getReference(TABLE_USERS);
        this.context = context;
    }

    public void saveAndModifyUser(User user) {
        tb_users
                .child(user.getId())
                .setValue(user)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(context,
                            "Sucesso ao salvar usuário",
                            LENGTH_SHORT).show();
                }).addOnFailureListener(error -> {
            Toast.makeText(context,
                    "Falha ao tentar salvar usuário",
                    LENGTH_LONG).show();
        });
    }

    public User getUserData(String id) {

        tb_users.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 user = snapshot.child(id)
                        .getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context,
                        "Não foi possível buscar os dados do usuário",
                        LENGTH_SHORT).show();
            }

        });
        return user;
    }

}
