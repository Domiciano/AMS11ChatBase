package edu.co.icesi.ams11chatbase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import edu.co.icesi.ams11chatbase.R;
import edu.co.icesi.ams11chatbase.model.User;
import edu.co.icesi.ams11chatbase.row.UserRow;

public class UsersAdapter extends RecyclerView.Adapter<UserRow> {

    private ArrayList<User> users;

    public UsersAdapter(){
        users = new ArrayList<>();
    }

    @Override
    public UserRow onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.userrow, parent, false);
        UserRow row = new UserRow(view);
        return row;
    }

    @Override
    public void onBindViewHolder(UserRow holder, int position) {
        User user = users.get(position);
        holder.getUsernameTV().setText(user.username);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void addUser(User user) {
        users.add(user);
        notifyDataSetChanged();
    }
}
