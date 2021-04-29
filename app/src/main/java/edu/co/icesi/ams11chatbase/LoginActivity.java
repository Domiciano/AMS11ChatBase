package edu.co.icesi.ams11chatbase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.UUID;

import edu.co.icesi.ams11chatbase.adapter.UsersAdapter;
import edu.co.icesi.ams11chatbase.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameET;
    private Button signinBtn;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = findViewById(R.id.usernameET);
        signinBtn = findViewById(R.id.signinBtn);

        db = FirebaseFirestore.getInstance();

        signinBtn.setOnClickListener(this::login);

        FirebaseMessaging.getInstance().subscribeToTopic("promocion");

    }

    private void login(View view) {
        db.collection("users")
                .whereEqualTo("username", usernameET.getText().toString())
                .get().addOnSuccessListener(
                command -> {
                    if (command.getDocuments().size() == 0) {
                        postUser();
                    }else{
                        goToUsersActivity();
                    }
                }
        );
    }

    public void postUser(){
        //Escribir dato
        User user = new User(UUID.randomUUID().toString(), usernameET.getText().toString());
        //Request
        db.collection("users").document(user.id).set(user).addOnSuccessListener(
                command -> {
                    //Response, UI Thread
                    goToUsersActivity();
                }
        );
    }


    public void goToUsersActivity() {
        Intent i = new Intent(this, UsersActivity.class);
        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        prefs.edit().putString("username1", usernameET.getText().toString()).apply();
        startActivity(i);
    }


}