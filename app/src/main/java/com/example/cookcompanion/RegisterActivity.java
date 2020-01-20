package com.example.cookcompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        Button register_confirm_bt = findViewById(R.id.RegisterAcceptButton);
        register_confirm_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password_verifies()) {
                    String password = ((EditText) findViewById(R.id.RegisterPassword)).getText().toString();
                    String email = ((EditText) findViewById(R.id.RegisterEmail)).getText().toString();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                                Toast.LENGTH_LONG).show();
                                        updateUI(null);
                                    }
                                }
                            });


                }
            }
        });
    }

    public boolean password_verifies(){
        EditText password1 = findViewById(R.id.RegisterPassword);
        EditText password2 = findViewById(R.id.RegisterPasswordConfirm);
        if (password1.length() <= 6){
            Toast.makeText(getApplicationContext(), "Your password must be greater than 6 characters!",Toast.LENGTH_LONG).show();
            return false;
        }else if (!password1.getText().toString().equals(password2.getText().toString())){
            Toast.makeText(getApplicationContext(), "Passwords are not matching!",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    public void updateUI(FirebaseUser user){
        if(user != null){
            Toast.makeText(getApplicationContext(), "Register successful!",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}