package com.example.myapplication.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.firebase.ConfigFirebase;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db_firestore;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference imageRef = storageRef.child("image/my_image2.jpg");

        ImageView image = findViewById(R.id.img_landscape);
        Button btUpload = findViewById(R.id.bt_upload);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        image.getDrawingCache(true);
        image.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] img_bytes = baos.toByteArray();
        
        Uri uri = Uri.fromFile(new File(getFilesDir(), "my_image.jpg"));

     /*   StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentType("images/jpg")
                .build();*/
        btUpload.setOnClickListener(view -> {
            ImageView image2 = findViewById(R.id.img_landscape2);
            Glide.with(this)
                    .load(imageRef)
                    .into(image2);
            //    UploadTask uploadTask = imageRef.putFile(uri);
                 /*   .addOnFailureListener(ex -> Toast.makeText(
                            this,
                            "Deu ruim lek " + ex.getMessage(),
                            Toast.LENGTH_SHORT).show())
                    .addOnSuccessListener(snapshot -> Toast.makeText(
                            this,
                            "Deu boa " + snapshot.getMetadata().getBucket(),
                            LENGTH_LONG).show());*/
            /*uploadTask.addOnProgressListener(snapshot -> {
                BigDecimal valor = new BigDecimal((100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount());
             //   int valor = new BigDecimal(progress).intValue();
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(valor.intValue());

                btUpload.setClickable(false);

                Log.i("progress", "upload " + valor.intValue() + "% done");

            });*/


            //create file
           /* try (FileOutputStream fos = openFileOutput("my_image.jpg", Context.MODE_PRIVATE)) {
                fos.write(img_bytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/


           /* UploadTask uploadTask = imageRef.putFile(uri);

            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    Task<Uri> downloadUrl = imageRef.getDownloadUrl();
                    return downloadUrl;
                }
            }).addOnCompleteListener(task -> {
                Uri link = task.getResult();
                Toast.makeText(this, link.toString(), LENGTH_LONG).show();
            }).addOnFailureListener(ex -> Toast.makeText(
                    this,
                    "Falha " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show());*/

        });





      /*  db_firestore = FirebaseFirestore.getInstance();
        HashMap<String, Object> userFirestore = new HashMap<>();
        userFirestore.put("nome", "Adriana");
        userFirestore.put("email", "adriana@gmail.com");
        userFirestore.put("aniversario", 0711);
        userFirestore.put("timestamp", FieldValue.serverTimestamp());

        if (getIntent().hasExtra("userId")) {
            userId = getIntent().getStringExtra("userId");
            Log.i("Id", userId);
            if (!userId.isEmpty()) {
                db_firestore.collection("users")
                        .document(userId)
                        .set(userFirestore)
                        .addOnSuccessListener(docRef -> {
                           *//* Toast.makeText(this,
                                    "salvo com sucesso",
                                    Toast.LENGTH_LONG).show();*//*
                        }).addOnFailureListener(ex -> {
                    Toast.makeText(this,
                            "Erro: " + ex.getMessage(),
                            Toast.LENGTH_LONG).show();
                });

            }
        }

        db_firestore.collection("users").orderBy("aniversario").get().addOnSuccessListener(snapshot -> {
            List<User> users = snapshot.toObjects(User.class);
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.caixa_dialogo);
            TextView dialogText = dialog.findViewById(R.id.editDialog);
            dialogText.setText("usuário: " + users.toString());
            dialog.create();
            dialog.show();
        }).addOnFailureListener(ex -> Toast.makeText(this,
                "falha ao recuperar a coleção " + ex.getMessage(),
                Toast.LENGTH_SHORT).show());*/

    /*    if (getIntent().hasExtra("userId")) {
            String userId = getIntent().getStringExtra("userId");
            Log.i("Id", userId);
            if (!userId.isEmpty()) {



                //  Toast.makeText(this, user.getNome(), LENGTH_LONG).show();
            }

            try {
                //   Toast.makeText(this, user.getNome(), Toast.LENGTH_SHORT).show();

            } catch (NullPointerException ex) {
                ex.fillInStackTrace();
                Log.i("Error", ex.getMessage());
                Toast.makeText(this,
                        "Usuário nulo " + ex.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }

        }*/

    }


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAuth firebaseAuth = ConfigFirebase.getFirebaseAuth();
        Log.i("Auth", "onResume: " + firebaseAuth.getUid());

        // Toast.makeText(this, "Estou aqui", Toast.LENGTH_LONG).show();


      /*  try {
            Thread.sleep(4000);
            new AlertDialog.Builder(this)
                    .setTitle("Dados dos usuário")
                    .setMessage("Dados do usuário " + user.getEmail() + " " + user.getNome() + " "
                            + user.getId())
                    .setPositiveButton("ok", null)
                    .create()
                    .show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}