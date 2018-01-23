package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {

    //最大的百分比: 决定了 Y 轴的高度
    private float mMaxValue;

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        List<Bar> bars = initData();
        if ( bars == null || bars.isEmpty()) return;
        int barsNum = bars.size();

        /** 先写 "直方图" 字样 **/
        String text = "直方图";
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50f);

        float textX = (getWidth() - textPaint.measureText(text))/2;
        float textY = (float)(getHeight() * 0.85);
        canvas.drawText(text, textX, textY, textPaint); //移动坐标原点位置

        /** 画坐标轴 **/
        Paint axisPaint = new Paint();
        axisPaint.setColor(Color.WHITE);
        axisPaint.setStrokeWidth(2f);
        axisPaint.setStyle(Paint.Style.STROKE);

        //坐标轴原点坐标
        float aixsX_X = (float)(getWidth() * 0.15);
        float aixsX_Y = (float)(getHeight() * 0.7);

        //设定 X 轴 的长度
        float aixsX_size = (float)(getWidth() * 0.7);
        //设定 Y 轴 的长度
        float aixsY_size = (float)(getWidth() * 0.4);

        //每一项条形柱的宽度
        float recWidth = (aixsX_size/barsNum) * 0.8f;
        //条形柱间隔的距离
        float recGap = (aixsX_size/barsNum) * 0.2f;

        //画 X 轴
        canvas.translate(aixsX_X,aixsX_Y);
        canvas.drawLine(0,0, aixsX_size + recGap, 0, axisPaint);

        //画 Y 轴
        canvas.drawLine(0,0, 0, 0 - aixsY_size, axisPaint);

        /** 依次画条形柱 **/
        // 说明文字的 Y 轴坐标是不变的
        Paint barP = new Paint();
        barP.setTextSize(30f);
        barP.setColor(Color.WHITE);

        float startX = 0f;

        for (Bar bar: bars){

            //计算条形柱的绘制点坐标
            float barLeft = startX + recGap;
            float barTop = 0 - bar.getTotal()/mMaxValue * aixsY_size;
            float barRight = startX + recGap + recWidth;
            float barBottom = 0;

            //绘制条形柱
            barP.setColor(bar.color);
            canvas.drawRect(barLeft, barTop, barRight, barBottom, barP);

            //绘制底部文字
            barP.setColor(Color.WHITE);
            float barTextX = startX + recGap + (float)(recWidth - barP.measureText(bar.getName()))/2;
            canvas.drawText(bar.name, barTextX, 30, barP);

            startX = startX + recGap + recWidth;
        }
    }

    private List<Bar> initData(){
        List<Bar> lists = new ArrayList<Bar>();

        lists.add(new Bar("Froyo", 0.5f, Color.GREEN));
        lists.add(new Bar("GB", 1, Color.GREEN));
        lists.add(new Bar("ICS", 1, Color.GREEN));
        lists.add(new Bar("JB", 10, Color.GREEN));
        lists.add(new Bar("KitKat", 20, Color.GREEN));
        lists.add(new Bar("L", 24, Color.GREEN));
        lists.add(new Bar("M", 8, Color.GREEN));

        mMaxValue = Float.MIN_VALUE;
        for (Bar bar: lists){
            mMaxValue = Math.max(mMaxValue, bar.getTotal());
        }

        return lists;
    }

    private class Bar{
        String name;
        float total;
        int color;

        public Bar(String name, float total, int color){
            this.name = name;
            this.total = total;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public float getTotal() {
            return total;
        }

        public int getColor() {
            return color;
        }
    }
}
