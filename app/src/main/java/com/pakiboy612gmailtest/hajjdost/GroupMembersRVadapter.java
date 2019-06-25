package com.pakiboy612gmailtest.hajjdost;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GroupMembersRVadapter extends RecyclerView.Adapter<GroupMembersRVadapter.GroupMemberHolder> {

    private Context context;
    private ArrayList<String> groupMemList;

    public GroupMembersRVadapter(Context context,ArrayList<String> groupMemList) {
        this.context = context;
        this.groupMemList = groupMemList;
        Log.d("groupMemList", "GroupMembersRVadapter: "+groupMemList);
    }

    @NonNull
    @Override
    public GroupMemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_object_dsign,parent,false);
        GroupMemberHolder holder = new GroupMemberHolder(view);
        Log.d("onCreateViewHolder", "is called ");
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMemberHolder holder, int position) {
        String namee = groupMemList.get(position);
        Log.d("namee", "onBindViewHolder: "+namee);
        holder.name.setText(namee);

    }

    @Override
    public int getItemCount() {
        return groupMemList.size();
    }

    public class GroupMemberHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public GroupMemberHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_RV_TV);
        }
    }

//    public class GroupMemberHolder extends RecyclerView.ViewHolder {
//        public TextView name;
//
//        public GroupMemberHolder(View itemView) {
//            super(itemView);
//            name = (TextView) itemView.findViewById(R.id.name_RV_TV);
//        }
//    }

}
