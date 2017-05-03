package com.bawei.texta1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.texta1.R;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/5/2.
 * <p>
 * 类的用途：
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{
    ArrayList<String> list;
    Context context;
    Click click;
    private LongClick longClick;

    //条目的点击事件
    public interface Click{
        void onClick(View v,int position);
    }
    public interface LongClick{
        void onLongClick(View v,int position);
    }
    public void setOnClick(Click click){
        this.click=click;
    }
    public void setOnLongClick(LongClick longClick){
        this.longClick=longClick;
    }


    public MyAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        final MyHolder myHolder = new MyHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 int i = myHolder.getLayoutPosition();
                Log.i("适配器中",i+"");
                if (click!=null){
                    click.onClick(v,i);
                }

            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int i = myHolder.getLayoutPosition();
                Log.i("长按事件",i+"");
                if (click!=null){
                    longClick.onLongClick(v,i);
                }

                return true;
            }
        });

        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.text.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        private final TextView text;

        public MyHolder(View itemView) {
            super(itemView);
            text=  (TextView) itemView.findViewById(R.id.text);

        }
    }
}
