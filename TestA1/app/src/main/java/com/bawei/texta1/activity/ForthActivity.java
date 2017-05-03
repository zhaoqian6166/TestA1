package com.bawei.texta1.activity;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bawei.texta1.R;
import com.bawei.texta1.adapter.MyAdapter;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/5/2.
 * <p/>
 * 类的用途：
 */
public class ForthActivity extends Activity {
    private int i;//0表示list   1表示grid
    private MyAdapter myAdapter;
    private LinearLayoutManager manager;
    private RecyclerView re4;
    private GridLayoutManager gridLayoutManager;
    private TextView text;
    private ArrayList<String> list;
    private View pop;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_main);
        initView();
    }

    private void initView() {
        re4 = (RecyclerView) findViewById(R.id.re4);
        Button change= (Button) findViewById(R.id.change);
        list = new ArrayList<String>();
        for (int i=0;i<30;i++){
            list.add("条目："+i);
        }
        manager = new LinearLayoutManager(this);
        myAdapter = new MyAdapter(list, ForthActivity.this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        //pop的使用
        pop = View.inflate(this, R.layout.item_text, null);
        popupWindow = new PopupWindow(pop, ActionBar.LayoutParams.MATCH_PARENT, 300);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        text = (TextView) pop.findViewById(R.id.itemText);
        setData(manager,myAdapter);
        i=0;
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击切换按钮
                if (i==0){
                    setData(gridLayoutManager,myAdapter);
                    i=1;
                }else{
                    setData(manager,myAdapter);
                    i=0;
                }
            }
        });
        //单击使用pop展示详细信息
        myAdapter.setOnClick(new MyAdapter.Click() {
            @Override
            public void onClick(View v, int position) {
                Log.i("???",position+"");
                popupWindow.showAtLocation(re4, Gravity.CENTER,0,0);
                text.setText(list.get(position));
            }
        });
        myAdapter.setOnLongClick(new MyAdapter.LongClick() {
            @Override
            public void onLongClick(View v, int position) {
                list.remove(position);
                myAdapter.notifyDataSetChanged();
            }
        });

    }

    private void setData(LinearLayoutManager manager,MyAdapter adapter) {
        re4.setLayoutManager(manager);
        re4.setAdapter(myAdapter);
    }


}
