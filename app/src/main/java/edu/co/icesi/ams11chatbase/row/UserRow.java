package edu.co.icesi.ams11chatbase.row;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.co.icesi.ams11chatbase.ChatActivity;
import edu.co.icesi.ams11chatbase.R;


public class UserRow extends RecyclerView.ViewHolder {

    private TextView usernameTV;
    private String id;

    public UserRow(View itemView) {
        super(itemView);
        usernameTV = itemView.findViewById(R.id.usernameTV);
        itemView.setOnClickListener(this::showChat);
    }

    private void showChat(View view) {
        Context context = view.getContext();

        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        sp.edit().putString("id2",id).apply();

        Intent i = new Intent(view.getContext(), ChatActivity.class);
        context.startActivity(i);
    }

    public TextView getUsernameTV() {
        return usernameTV;
    }

    public void setId(String id) {
        this.id = id;
    }
}
