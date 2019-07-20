package com.example.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.Model.PeopleData;
import com.example.messenger3.R;

import java.util.ArrayList;


/**
 * Created by Jaison on 08/10/16.
 */

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.MyViewHolder> {

    public ArrayList<PeopleData> peopleDataList =new ArrayList<>();
    Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userNameTxt, msgTxt;
        ImageView userImage;
        public CardView ll_listitem;


        public MyViewHolder(View view) {
            super(view);
            userNameTxt = (TextView) view.findViewById(R.id.multi_selected_session);
            msgTxt = (TextView) view.findViewById(R.id.end);
            userImage = (ImageView) view.findViewById(R.id.user_image);

            ll_listitem=(CardView) view.findViewById(R.id.ll_listitem);

        }
    }


    public UserInfoAdapter(Context context, ArrayList<PeopleData> peopleDataList) {
        this.mContext=context;
        this.peopleDataList = peopleDataList;
//        this.completeCount = completeCount;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lvl_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PeopleData PeopleData = peopleDataList.get(position);

        holder.userNameTxt.setText(PeopleData.getName());
        holder.msgTxt.setText(PeopleData.getMsg());


//        holder.userImage.setImageBitmap(movie.getBitmap());




    }

    @Override
    public int getItemCount() {
        return peopleDataList.size();
    }
}

