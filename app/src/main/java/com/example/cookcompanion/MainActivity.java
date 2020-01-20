package com.example.cookcompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class MainActivity extends AppCompatActivity {
    private User user;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_my_ingredients:
                Toast.makeText(this, "Item 1 selected.",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_measurements:
                Toast.makeText(this, "Item 2 selected,",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_shopping_list:
                Toast.makeText(this, "shop",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_recipes:
                Toast.makeText(this, "Item 4 selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_planner:
                Toast.makeText(this, "Item 5 selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_start_cooking:
                Toast.makeText(this, "Item 6 selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_logout:
                saveUser();
                mAuth.signOut();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                return true;
            case R.id.menu_profile:
                startActivity(new Intent(this,MainActivity.class));
                return true;
             default:
                 return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                        Log.d("WHAT", "DocumentSnapshot data: " + document.getData());
                        user = document.toObject(User.class);
                        TextView welcomeText = findViewById(R.id.welcome_message);
                        welcomeText.setText(user.getPersonal_message());
                    }
                }
            }
        });


    }

    public void saveUser(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("WHAT", "DocumentSnapshot successfully written!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("WHAT", "Error writing document", e);
            }
        });
    }
}
