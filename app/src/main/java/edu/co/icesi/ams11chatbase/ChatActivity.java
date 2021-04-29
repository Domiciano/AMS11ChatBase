package edu.co.icesi.ams11chatbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Date;
import java.util.UUID;

import edu.co.icesi.ams11chatbase.adapter.MessagesAdapter;
import edu.co.icesi.ams11chatbase.model.Message;

public class ChatActivity extends AppCompatActivity {

    private TextView personTitle;
    private RecyclerView messagesList;
    private EditText messageET;
    private Button sendBtn;
    private FirebaseFirestore db;

    private MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        personTitle = findViewById(R.id.personTitle);
        messagesList = findViewById(R.id.messagesList);
        messageET = findViewById(R.id.messageET);
        sendBtn = findViewById(R.id.sendBtn);

        db = FirebaseFirestore.getInstance();

        messagesAdapter = new MessagesAdapter();
        messagesList.setAdapter(messagesAdapter);
        messagesList.setHasFixedSize(true);
        messagesList.setLayoutManager(new LinearLayoutManager(this));


        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        String username1 = sp.getString("username1", "NO_DATA");
        String username2 = sp.getString("username2", "NO_DATA");

        Log.e(">>>", username1);
        Log.e(">>>", username2);

        String concat = username1.compareTo(username2) >= 0 ? username1 + username2 : username2 + username1;

        db.collection("chat")
                .document(concat).collection("messages")
                .orderBy("date", Query.Direction.DESCENDING)
                .addSnapshotListener(
                        (value, error) -> {
                            messagesAdapter.clear();
                            for (DocumentSnapshot snapshot : value.getDocuments()) {
                                Message message = snapshot.toObject(Message.class);
                                messagesAdapter.addMessage(message);
                            }
                        }
                );


        sendBtn.setOnClickListener(
                v -> {



                    String id = UUID.randomUUID().toString();
                    Message message = new Message(id, messageET.getText().toString(), new Date().getTime());


                    db.collection("chat")
                            .document(concat)
                            .collection("messages")
                            .document(message.id).set(message);


                }
        );


    }
}