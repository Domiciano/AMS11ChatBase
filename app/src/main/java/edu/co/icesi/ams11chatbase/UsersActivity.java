package edu.co.icesi.ams11chatbase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import edu.co.icesi.ams11chatbase.adapter.UsersAdapter;
import edu.co.icesi.ams11chatbase.model.User;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView usersList;
    private UsersAdapter userAdapter;
    private FirebaseFirestore db;
    private EditText searchET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        searchET = findViewById(R.id.searchET);


        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchUsername(s.toString());
            }
        });


        usersList = findViewById(R.id.usersList);
        userAdapter = new UsersAdapter();
        usersList.setLayoutManager(new LinearLayoutManager(this));
        usersList.setHasFixedSize(true);
        usersList.setAdapter(userAdapter);

        db = FirebaseFirestore.getInstance();

        getData("*");

        usersList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //BOTTOM
                if (!recyclerView.canScrollVertically(-1) && dy < 0) {
                    Log.e(">>>", "TOP");
                } else if (!recyclerView.canScrollVertically(1) && dy > 0) {
                    Log.e(">>>", "BOTTOM");
                    //Cargar los siguientes
                    String ultimousuario = userAdapter.getLastUser().username;



                    getData(ultimousuario);
                }
            }
        });

    }

    private void searchUsername(String username) {
        if(username.length() >= 1) {
            String ini = username;
            char[] array = username.toCharArray();
            array[array.length - 1]++;
            String end = String.valueOf(array);


            db.collection("users")
                    .whereGreaterThanOrEqualTo("username", ini)
                    .whereLessThan("username", end)
                    .get().addOnSuccessListener(
                    command -> {
                        userAdapter.clear();
                        for (DocumentSnapshot doc : command.getDocuments()) {
                            User user = doc.toObject(User.class);
                            userAdapter.addUser(user);
                        }
                    }
            );
        }else{
            userAdapter.clear();
            getData("*");
        }
    }

    private void getData(String lastusername) {
        db.collection("users").orderBy("username").limit(10).startAfter(lastusername)
                .get().addOnSuccessListener(
                command -> {
                    for (DocumentSnapshot doc : command.getDocuments()) {
                        User user = doc.toObject(User.class);
                        userAdapter.addUser(user);
                    }
                }
        );
    }



    public class Waiter extends Thread{

        public int millis = 400;

        @Override
        public void run(){
            while(millis<400){
                delay(10);
                millis-=10;
            }
            //ACCION
            searchUsername(searchET.getText().toString());
        }

        public void delay(long time){
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



}