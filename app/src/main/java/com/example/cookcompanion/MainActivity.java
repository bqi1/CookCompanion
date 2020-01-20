package com.example.cookcompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class MainActivity extends AppCompatActivity {
    private MainMessage mainMessage;
    private User user;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainMessage = new MainMessage();
        mAuth = FirebaseAuth.getInstance();
        TextView welcomeText = findViewById(R.id.welcome_message);
        welcomeText.setText(mainMessage.getMessage());
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
                mAuth.signOut();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
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


}
