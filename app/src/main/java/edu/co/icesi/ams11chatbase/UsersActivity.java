package edu.co.icesi.ams11chatbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import edu.co.icesi.ams11chatbase.adapter.UsersAdapter;
import edu.co.icesi.ams11chatbase.model.User;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView usersList;
    private UsersAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        usersList = findViewById(R.id.usersList);
        userAdapter = new UsersAdapter();
        usersList.setLayoutManager(new LinearLayoutManager(this));
        usersList.setHasFixedSize(true);
        usersList.setAdapter(userAdapter);

        //Información Dummy
        userAdapter.addUser(new User("Alfa"));
        userAdapter.addUser(new User("Beta"));
        userAdapter.addUser(new User("Gamma"));
    }
}