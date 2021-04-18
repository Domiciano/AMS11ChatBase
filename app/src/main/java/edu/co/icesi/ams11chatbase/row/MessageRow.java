package edu.co.icesi.ams11chatbase.row;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import edu.co.icesi.ams11chatbase.R;

public class MessageRow extends RecyclerView.ViewHolder {

    private TextView messageTV;

    public MessageRow(View itemView) {
        super(itemView);
        messageTV = itemView.findViewById(R.id.messageTV);
    }

    public TextView getMessageTV() {
        return messageTV;
    }
}
