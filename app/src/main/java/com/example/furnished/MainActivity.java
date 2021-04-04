
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

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import Models.Users;
        import Prevalent.Prevalent;

public class MainActivity extends AppCompatActivity {
    private EditText loginphone,loginPw;
    private Button loginBtn;
    private TextView gotoRegister,AdminLogin;
    private ProgressDialog Loading;
    String parentDBName = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginphone = findViewById(R.id.Loginphone);
        loginPw = findViewById(R.id.Loginpw);
        loginBtn = findViewById(R.id.LoginBtn);
        gotoRegister = findViewById(R.id.toRegpage);
        AdminLogin = findViewById(R.id.Adminloginbtn);
        Loading = new ProgressDialog(this);


     //GO TO REGISTRATION PAGE
     gotoRegister.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             RegisterActivity();
         }
     });
        //LOGIN
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        //ADMIN LOGIN
        AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBtn.setText("Admin Login");
                AdminLogin.setVisibility(View.INVISIBLE);
                parentDBName = "Admin";
            }
        });
    }


    private void Login() {
        String LoginPhone = loginphone.getText().toString();
        String LoginPw = loginPw.getText().toString();


        if (TextUtils.isEmpty(LoginPhone))
        {
            Toast.makeText(MainActivity.this,"Please enter your phone number",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(LoginPw)){
            Toast.makeText(MainActivity.this,"Please enter your password",Toast.LENGTH_SHORT).show();
        }

        else{
            Loading.setTitle("Logging in");
            Loading.setMessage("Please Wait");
            Loading.setCanceledOnTouchOutside(false);
            Loading.show();
            Login(LoginPhone,LoginPw);
        }

    }

    private void Login(final String loginPhone,final String loginPw) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentDBName).child(loginPhone).exists()){
                    Users userdata = snapshot.child(parentDBName).child(loginPhone).getValue(Users.class);
                    if(userdata.getPhone().equals(loginPhone)) {

                        if(userdata.getPassword().equals(loginPw)){
                            if(parentDBName.equals("Admin")){
                                Toast.makeText(MainActivity.this,"Admin Login successful",Toast.LENGTH_SHORT).show();
                                Loading.dismiss();
                                Intent intent = new Intent(MainActivity.this,AddCategories.class);
                                startActivity(intent);
                            }
                            if(parentDBName.equals("Users")){
                                Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                                String username = userdata.getName();
                                Toast.makeText(MainActivity.this,username,Toast.LENGTH_SHORT).show();
                                Loading.dismiss();
                                Intent intent = new Intent(MainActivity.this,Home.class);
                                Prevalent.currentOnlineUser = userdata;
                                intent.putExtra("uid",username);
                                startActivity(intent);
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Password is incorrect",Toast.LENGTH_SHORT).show();
                            Loading.dismiss();
                        }
                    }

                }
                else {
                    Toast.makeText(MainActivity.this,"Account Does not exist, Please Register",Toast.LENGTH_SHORT).show();
                    Loading.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void RegisterActivity() {
        Intent register = new Intent(this, registration.class);
        startActivity(register);
    }


}

