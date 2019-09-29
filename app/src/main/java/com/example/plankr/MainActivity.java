package com.example.plankr;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private Button Register;
    private int counter = 5;
    private Button userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.register_username);
        Password = (EditText)findViewById(R.id.register_pass);
        Login = findViewById(R.id.register_sign_in);
        userRegistration = findViewById(R.id.register_ready);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this, Main.class));
        }

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                validate(Name.getText().toString(), Password.getText().toString());

            }
        });

        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this, Main.class));
        }
    }

    private void validate(String name, String password){

        progressDialog.setMessage("Wait, until the alcohol kicks in!");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Main.class));
                } else  {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    progressDialog.dismiss();

                    if(counter != 0){
                        Toast.makeText(MainActivity.this, "Login-Attempts: " + counter, Toast.LENGTH_LONG).show();
                    } else {
                        Login.setEnabled(false);
                        Toast.makeText(MainActivity.this, "Login-Button disabled" + counter, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void validate(String userName, String userPassword){
        progressDialog.setMessage("You can subscribe to DogeBrown");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Main.class));
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Toast.makeText(MainActivity.this, "Attempts " + counter, Toast.LENGTH_SHORT ).show();
                    if(counter == 0){
                        progressDialog.dismiss();
                        Login.setEnabled(false);
                    }
                }
            }
        });
    }

}
