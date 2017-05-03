package com.bawei.texta1.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.bawei.texta1.activity.ForthActivity;
import com.bawei.texta1.activity.SecondActivity;
import com.bawei.texta1.activity.ThirdActivity;
import com.bawei.texta1.activity.TryActivity;

/**
 * Created by 赵倩 on 2017/5/1.
 * <p/>
 * 类的用途：
 */
public class MyView extends View {
    private float currentX = 300;
    private float currentY = 300;
    private float rBig = 300;
    private float rSmall = 150;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画布背景颜色
        canvas.drawColor(Color.WHITE);
        //画方框
        Paint paint3 = new Paint();
        paint3.setColor(Color.GREEN);
        //设置画笔的样式为空心
        paint3.setStyle(Paint.Style.FILL);
        //设置画笔的宽度
        paint3.setStrokeWidth(10);
        //抗锯齿
        paint3.setAntiAlias(true);
        canvas.drawRect(0, 0, 600, 600, paint3);//直接构造
        //画大圆
        paint3.setColor(Color.YELLOW);
        canvas.drawCircle(currentX, currentY, rBig, paint3);

        //画第二个圆
        paint3.setColor(Color.WHITE);
        canvas.drawCircle(currentX, currentY, rSmall, paint3);
        //写字
        paint3.setColor(Color.BLACK);

        //  写文本
        String text = "圆环";
        paint3.setTextSize(75);
        Rect rect = new Rect();
        paint3.getTextBounds(text, 0, text.length(), rect);

        canvas.drawText(text,
                0 - rect.left + (600 - rect.width())/2,
                0 - rect.top + (600 - rect.height())/2,
                paint3);//居中显示文本

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //设置触摸事件的监听
        //得到当前的坐标点位置
        float x = event.getX();
        float y = event.getY();
        int k = event.getAction();
        //点下是2  松开是1
       // Log.i("---",k+"事件");
        if (k == 1) {
            //  distance = getDistance(event);
            float sqrt = (float) Math.sqrt((x - currentX) * (x - currentX) + (y - currentY) * (y - currentY));
            if (sqrt < rSmall) {
                Log.i("---","小圆中");
                Toast.makeText(getContext(), "在小圆内", Toast.LENGTH_SHORT).show();
            } else if(sqrt > rSmall&&sqrt<600){
                Log.i("---","大圆中");
                Toast.makeText(getContext(), "在大圆内", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), TryActivity.class);
                getContext().startActivity(intent);

            }

        }

        return true;
    }

}
