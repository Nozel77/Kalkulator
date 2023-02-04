package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    SharedPreferences sharedpreferences;
    String email, password;

    //list widget yang akan dikenakan aksi
    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // konekkan semua komponen dengan xml nya
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                if (username.equalsIgnoreCase("admin") &&
                        password.equalsIgnoreCase("admin")){
                    sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(EMAIL_KEY, txtUsername.getText().toString());
                    editor.putString(PASSWORD_KEY, txtPassword.getText().toString());
                    // to save our data with key and value.
                    editor.apply();




//                    Toast.makeText(getApplicationContext(), "Sukses Login", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Sukses")
                            .setMessage("Login Sukses")
                            .setPositiveButton(android.R.string.yes,(dialog,which)-> startActivity(new Intent(MainActivity.this, kalkulator.class))

                            )

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal Login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (email != null && password != null) {
            Intent i = new Intent(MainActivity.this, kalkulator.class);
            startActivity(i);
        }
    }
}