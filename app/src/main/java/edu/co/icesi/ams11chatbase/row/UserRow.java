package edu.co.icesi.ams11chatbase.row;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.co.icesi.ams11chatbase.ChatActivity;
import edu.co.icesi.ams11chatbase.R;


public class UserRow extends RecyclerView.ViewHolder {

    private TextView usernameTV;

    public UserRow(View itemView) {
        super(itemView);
        usernameTV = itemView.findViewById(R.id.usernameTV);
        itemView.setOnClickListener(this::showChat);
    }

    private void showChat(View view) {
        Context context = view.getContext();
        Intent i = new Intent(view.getContext(), ChatActivity.class);
        context.getSharedPreferences("data", Context.MODE_PRIVATE)
                .edit()
                .putString("username2", usernameTV.getText().toString()).apply();
        context.startActivity(i);

    }

    public TextView getUsernameTV() {
        return usernameTV;
    }

}
