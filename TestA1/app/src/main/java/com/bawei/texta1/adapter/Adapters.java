package com.bawei.texta1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.texta1.R;
import com.bawei.texta1.bean.Info;
import com.bumptech.glide.Glide;


import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/5/2.
 * <p/>
 * 类的用途：
 */
public class Adapters extends RecyclerView.Adapter<Adapters.MyViewHolders> {
    ArrayList<Info.Data> list;
    Context context;

    public Adapters(ArrayList<Info.Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.five_item, parent, false);
        MyViewHolders myViewHolders = new MyViewHolders(view);

        return myViewHolders;
    }

    @Override
    public void onBindViewHolder(MyViewHolders holder, int position) {
        holder.age.setText(list.get(position).userAge);
        holder.detail.setText(list.get(position).introduction);
        holder.office.setText(list.get(position).occupation);
        Log.i("图片",list.get(position).img);
        Glide.with(context).load(list.get(position).img).into(holder.img);
       // Picasso.with(context).load(list.get(position).img).into(holder.img);
        //设置缓存
       /* DisplayImageOptions options=new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(list.get(position).img,holder.img,options);*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolders extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final Button age;
        private final TextView detail;
        private final Button office;

        public MyViewHolders(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item5_img);
            age = (Button) itemView.findViewById(R.id.item5_age);
            detail = (TextView) itemView.findViewById(R.id.item5_detail);
            office = (Button) itemView.findViewById(R.id.item5_office);
        }
    }
}
