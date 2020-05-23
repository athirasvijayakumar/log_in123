package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edittextemail;
    private EditText edittextpassword;
    private Button buttonregister;
    private TextView textviewsignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittextemail=(EditText) findViewById(R.id.editTextEmail);
        edittextpassword=(EditText) findViewById(R.id.editTextpassword);
        buttonregister=(Button) findViewById(R.id.buttonRegister);
        textviewsignin=(TextView) findViewById(R.id.textviewsignin);
        buttonregister.setOnClickListener(this);
        textviewsignin.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
    }
    private void registerUser(){
        String email=edittextemail.getText().toString().trim();
        String password=edittextpassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"please enter the email",Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"please enter the password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering user.....");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Registerd successfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Registerd not successfully ....please try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view==buttonregister){
            registerUser();

        }
        if (view==textviewsignin){
            //will open login activity
        }

    }
}
