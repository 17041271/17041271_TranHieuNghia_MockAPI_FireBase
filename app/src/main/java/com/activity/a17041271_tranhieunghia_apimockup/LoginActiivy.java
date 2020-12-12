package com.activity.a17041271_tranhieunghia_apimockup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActiivy extends AppCompatActivity {

    Button btnLogin;
    EditText editUserName, editPassword;
    TextView tvSignUpNow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnLogin = findViewById(R.id.btnLogin);
        editUserName = findViewById(R.id.editNhapUserNameLogin);
        editPassword = findViewById(R.id.editNhapMKLogin);
        tvSignUpNow = findViewById(R.id.tvNewhereSignUp);

        tvSignUpNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActiivy.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editUserName.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if (userName.length() == 0 || password.length() == 0) {
                    Toast.makeText(LoginActiivy.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    auth.signInWithEmailAndPassword(userName, password)
                            .addOnCompleteListener(LoginActiivy.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        //Log.d(TAG, "signInWithEmail:success");
                                        Toast.makeText(LoginActiivy.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActiivy.this, MainActivity.class);
                                        startActivity(intent);

                                        //FirebaseUser user = mAuth.getCurrentUser();
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActiivy.this, "Tài khoản sai hoặc không tồn tại!",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }
            }
        });


    }

}
