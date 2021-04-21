package edu.co.icesi.ams11chatbase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.co.icesi.ams11chatbase.R;
import edu.co.icesi.ams11chatbase.model.Message;
import edu.co.icesi.ams11chatbase.row.MessageRow;

public class MessagesAdapter extends RecyclerView.Adapter<MessageRow> {

    private ArrayList<Message> messages;

    public MessagesAdapter() {
        messages = new ArrayList<>();
    }

    @Override
    public MessageRow onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.messagerow, parent, false);
        MessageRow row = new MessageRow(view);
        return row;
    }

    @Override
    public void onBindViewHolder(MessageRow holder, int position) {
        Message m = messages.get(position);
        holder.getMessageTV().setText(m.body);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(Message message){
        messages.add(message);
        notifyDataSetChanged();
    }

    public void clear() {
        messages.clear();
        notifyDataSetChanged();
    }
}
