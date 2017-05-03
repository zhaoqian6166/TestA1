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
 * <p/>
 * 类的用途：
 */
public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyHolder2>{
        ArrayList<String> list;
        Context context;
        Click click;


//条目的点击事件
public interface Click{
    void onClick(View v, int position);
}
    public void setOnClick(Click click){
        this.click=click;
    }


    public MyAdapter2(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
        //注意这里不可以提为一个成员变量
        final MyHolder2 myHolder = new MyHolder2(view);

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
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder2 holder, int position) {
        holder.text.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

class MyHolder2 extends RecyclerView.ViewHolder{

    private final TextView text;

    public MyHolder2(View itemView) {
        super(itemView);
        text=  (TextView) itemView.findViewById(R.id.itemText);

    }
}
}

