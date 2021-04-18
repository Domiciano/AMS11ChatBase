package edu.co.icesi.ams11chatbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.co.icesi.ams11chatbase.adapter.MessagesAdapter;
import edu.co.icesi.ams11chatbase.model.Message;

public class ChatActivity extends AppCompatActivity {

    private TextView personTitle;
    private RecyclerView messagesList;
    private EditText messageET;
    private Button sendBtn;
    
    private MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        personTitle = findViewById(R.id.personTitle);
        messagesList = findViewById(R.id.messagesList);
        messageET = findViewById(R.id.messageET);
        sendBtn = findViewById(R.id.sendBtn);

        messagesAdapter = new MessagesAdapter();
        messagesList.setAdapter(messagesAdapter);
        messagesList.setHasFixedSize(true);
        messagesList.setLayoutManager(new LinearLayoutManager(this));
        
        //Información Dummy
        messagesAdapter.addMessage(new Message("Aa Bb Cc Dd"));
        messagesAdapter.addMessage(new Message("Ee Ff Gg Hh"));
        messagesAdapter.addMessage(new Message("Ii Jj Kk Ll"));
        messagesAdapter.addMessage(new Message("Mm Nn Oo Pp"));
        
    }
}