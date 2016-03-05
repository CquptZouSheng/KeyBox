package com.example.zousheng.keybox;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Zou Sheng on 2016/2/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public List<KeyBox> mdatas;
    public Context context;
    public MyAdapter(List<KeyBox> mdatas,Context context){
        this.mdatas=mdatas;
        this.context=context;
    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.name.setText(mdatas.get(position).getName());
        holder.account.setText(mdatas.get(position).getAccount());
        holder.password.setText(mdatas.get(position).getPassword());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,InformationActivity.class);
                intent.putExtra("id",mdatas.get(position).getId());
                intent.putExtra("name",mdatas.get(position).getName());
                intent.putExtra("account",mdatas.get(position).getAccount());
                intent.putExtra("password",mdatas.get(position).getPassword());
                intent.putExtra("remark",mdatas.get(position).getRemark());
                intent.putExtra("data",mdatas.get(position).getTime());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mdatas.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,account,password,remark;
        ImageView content,stripe;
        RelativeLayout relativeLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            relativeLayout= (RelativeLayout) itemView.findViewById(R.id.item);
            name= (TextView) itemView.findViewById(R.id.name);
            account= (TextView) itemView.findViewById(R.id.account);
            password= (TextView) itemView.findViewById(R.id.password);
            remark= (TextView) itemView.findViewById(R.id.remark);
            content= (ImageView) itemView.findViewById(R.id.content);
            stripe= (ImageView) itemView.findViewById(R.id.stripe);
        }
    }
}
