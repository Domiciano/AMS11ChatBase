package edu.co.icesi.ams11chatbase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.co.icesi.ams11chatbase.adapter.UsersAdapter;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameET;
    private Button signinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = findViewById(R.id.usernameET);
        signinBtn = findViewById(R.id.signinBtn);

        signinBtn.setOnClickListener(this::login);
    }

    private void login(View view) {
        Intent i = new Intent(this, UsersActivity.class);
        startActivity(i);
    }
}