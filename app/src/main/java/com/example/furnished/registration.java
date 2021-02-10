package com.example.furnished;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class registration extends AppCompatActivity {
private EditText RegName,Regphone,Regpw;
private Button RegisterBtn;
private TextView ToLogin;
private ProgressDialog Loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        RegName = findViewById(R.id.RegName);
        Regphone = findViewById(R.id.Regphone);
        Regpw = findViewById(R.id.Regpw);
        RegisterBtn = findViewById(R.id.RegBtn);
        ToLogin = findViewById(R.id.toLogin);
        Loading = new ProgressDialog(this);

     //GO TO LOGIN PAGE
        ToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotologin();
            }
        });

        //REGISTRATION
        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });


    }

    private void Register() {
        String name = RegName.getText().toString();
        String phone = Regphone.getText().toString();
        String password = Regpw.getText() .toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(registration.this,"Please enter your name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(registration.this,"Please enter your phone number",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(registration.this,"Please enter a valid password",Toast.LENGTH_SHORT).show();
        }
        else{
            Loading.setTitle("Creating Account");
            Loading.setCanceledOnTouchOutside(false);
            Loading.setMessage("Please Wait");
            Loading.show();
            Validate(name,phone,password);
        }
    }
//Registration Validation
    private void Validate(final String name,final String phone,final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(phone).exists())){
                    HashMap<String, Object> UserData = new HashMap<>();
                    UserData.put("name",name);
                    UserData.put("phone",phone);
                    UserData.put("password",password);

                    RootRef.child("Users").child(phone).updateChildren(UserData)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isComplete()){
                                       Toast.makeText(registration.this,"Your account has been created successfully",Toast.LENGTH_SHORT).show();
                                       Loading.dismiss();
                                       Intent intent = new Intent(registration.this,MainActivity.class);
                                       startActivity(intent);
                                   }
                                   else {
                                       Loading.dismiss();
                                       Toast.makeText(registration.this,"Network Error",Toast.LENGTH_SHORT).show();
                                   }
                                }
                            });
                }
                else{
                    Toast.makeText(registration.this,"This phone number" +phone+"already exists",Toast.LENGTH_SHORT).show();
                    Loading.dismiss();
                    Toast.makeText(registration.this,"Please try again using a different phone number",Toast.LENGTH_SHORT).show();
                    Intent register = new Intent(registration.this, registration.class);
                    startActivity(register);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void gotologin() {
        Intent gotologin = new Intent(this, MainActivity.class);
        startActivity(gotologin);
    }
}
