package com.bawei.texta2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/5/3.
 * <p/>
 * 类的用途：
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Info.Data> list;

    public MyAdapter(Context context, ArrayList<Info.Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(inflate);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.age.setText(list.get(position).userAge);
        holder.name.setText(list.get(position).userName);
        holder.occu.setText(list.get(position).occupation);
        holder.title.setText(list.get(position).introduction);
        Glide.with(context).load(list.get(position).img).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final TextView name;
        private final Button age;
        private final Button occu;
        private final TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_img);
            name = (TextView) itemView.findViewById(R.id.item_name);
            age = (Button) itemView.findViewById(R.id.item_age);
            occu = (Button) itemView.findViewById(R.id.item_occu);
            title = (TextView) itemView.findViewById(R.id.item_title);
        }
    }
}
