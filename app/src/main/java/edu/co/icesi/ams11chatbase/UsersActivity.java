package edu.co.icesi.ams11chatbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.co.icesi.ams11chatbase.adapter.UsersAdapter;
import edu.co.icesi.ams11chatbase.model.User;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView usersList;
    private UsersAdapter userAdapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        usersList = findViewById(R.id.usersList);
        userAdapter = new UsersAdapter();
        usersList.setLayoutManager(new LinearLayoutManager(this));
        usersList.setHasFixedSize(true);
        usersList.setAdapter(userAdapter);

        //Lectura
        db = FirebaseFirestore.getInstance();

        db.collection("users").get().addOnSuccessListener(
                command -> {
                    //Respuesta de firebase está en command
                    for(DocumentSnapshot doc : command.getDocuments()){
                        //doc -> User
                        User user = doc.toObject(User.class);
                        userAdapter.addUser(user);
                    }
                }
        );


    }
}