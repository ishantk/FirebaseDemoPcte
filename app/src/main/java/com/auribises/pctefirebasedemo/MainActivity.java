package com.auribises.pctefirebasedemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/*
Android
	Android Studio

	1. Start a New Project
	2. Note the Application ID (com.auribises.pctefirebasedemo) | Find in build.gradle of Module:app
	3. Add Dependencies in build.gradle
	4. Select Project View and copy google-services.json file into app folder

Cloud Computing
	Google Firebase

	NoSQL | Documets are stored
	Node JS

	RDBMS | MySQL


	1. Create a New Project
	2. Add Android to Firebase app
	3. Configure gradle scripts in Android App from here
	4. Enable SignIn Method in Authentication
	5. Downalod google-services.json file

 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    EditText eTxtName;
    EditText eTxtEmail;
    EditText eTxtPassword;
    Button btnRegister;

    String name, email, password;

    FirebaseAuth firebaseAuth;

    void initViews(){

        firebaseAuth = FirebaseAuth.getInstance();

        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);
        eTxtPassword = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.buttonRegister);

        btnRegister.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    void registerUserOnFirebase(){

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity.this, "User Not Registered Successfully", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        name = eTxtName.getText().toString();
        email = eTxtEmail.getText().toString();
        password = eTxtPassword.getText().toString();

        registerUserOnFirebase();
    }
}
