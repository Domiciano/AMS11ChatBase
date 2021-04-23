package edu.co.icesi.ams11chatbase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

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
    }

    private void login(View view) {

        //Mirar primero si existe
        db.collection("users")
                .whereEqualTo("username", usernameET.getText().toString())
                .get().addOnSuccessListener(
                query -> {
                    if(query.getDocuments().size() == 0){
                        addUser();
                    }else{
                        //Ya el usuario estaba registrado
                        User user = query.getDocuments().get(0).toObject(User.class);

                        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                        sp.edit().putString("id1", user.id).apply();


                        goToUsersActivity();
                    }
                }
        );
    }

    public void addUser(){
        String id = UUID.randomUUID().toString();
        User user = new User(id, usernameET.getText().toString());
        db.collection("users").document(id).set(user).addOnSuccessListener(
                command -> {
                    SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                    sp.edit().putString("id1", user.id).apply();
                    goToUsersActivity();
                }
        );
    }

    public void goToUsersActivity() {
        Intent i = new Intent(this, UsersActivity.class);
        startActivity(i);
    }
}