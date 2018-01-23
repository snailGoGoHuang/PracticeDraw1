package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Practice11PieChartView extends View {

    //饼图数据排序字段
    private static final int ORDER_BY_NAME_ASC = 1;
    private static final int ORDER_BY_PER_ASC = 2;

    //饼图列表总数据
    private float mTotal = 0f;

    //饼图中最大数据
    private float mMax = 0f;

    //饼图的半径
    private static final float radius = 250f;

    //第一段直线结束位置的外扩圆半径
    private static final float lineOneRadius = radius + 50f;

    //第二段直线的 X 坐标长度
    private static final float lineTwoLength = 50f;

    //文字与直线的 X 轴间距
    private static final float wordGap = 5f;

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mTotal = 0f;
        mMax = 0f;

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        float centerX = getWidth()/2;
        float centerY = getHeight()/2;
        //将坐标原点(0,0)移动到中心位置
        canvas.translate(centerX, centerY);

        //底部文字: 饼图
        String text = "饼图";
        Paint textP = new Paint();
        textP.setColor(Color.WHITE);
        textP.setTextSize(45f);
        canvas.drawText(text, (getWidth() - textP.measureText(text))/2, getHeight()*0.85f, textP);

        //初始化饼图数据
        List<PieGraphItem> lists = initPieGraphItems();

        //对数据进行排序, 按顺序顺时针绘图
        lists = orderItems(lists, ORDER_BY_NAME_ASC);

        RectF rectF = new RectF();
        rectF.set(-radius, -radius, radius, radius);

        Paint paintPie = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintPie.setStyle(Paint.Style.FILL);

        float startAngle = 0f;  // 当前扇形的开始角度
        float sweepAngle = 0f;  // 当前扇形扫过的角度
        float lineAngle = 0f;   // 当前扇形一半的角度
        float lineStartX = 0f;  // 直线开始的X坐标
        float lineStartY = 0f;  // 直线开始的Y坐标
        float lineEndX = 0f;    // 直线结束的X坐标
        float lineEndY = 0f;    // 直线结束的Y坐标

        int itemNum = lists.size();
        paintPie.setColor(Color.RED);
        //canvas.drawOval(-20,-20,20,20,paintPie);

        Paint testPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        testPaint.setColor(Color.WHITE);
        testPaint.setStrokeWidth(4);
        //绘制饼图
        for (int i = 0; i < itemNum; i++) {
            PieGraphItem item = lists.get(i);

            paintPie.setColor(item.getColor());

            sweepAngle = (item.getNumber()/mTotal) * 360f;

            //画线: 在扇形圆弧的中间位置开始画.
            lineAngle = startAngle + sweepAngle / 2;

            //根据单位圆中角度, 利用三角函数计算出点的(x,y)坐标值.
            lineStartX = radius * (float) Math.cos(lineAngle / 180 * Math.PI);
            lineStartY = radius * (float) Math.sin(lineAngle / 180 * Math.PI);

            lineEndX = (lineOneRadius) * (float) Math.cos(lineAngle / 180 * Math.PI);
            lineEndY = (lineOneRadius) * (float) Math.sin(lineAngle / 180 * Math.PI);

            //画扇形
            paintPie.setColor(item.getColor());
            if (item.getNumber() == mMax) {
                canvas.save();
                canvas.translate(lineStartX * 0.1f, lineStartY * 0.1f);
                canvas.drawArc(rectF, startAngle, sweepAngle, true, paintPie);
            } else {
                canvas.drawArc(rectF, startAngle, sweepAngle - 1.5f, true, paintPie);
            }

            //第一段直线
            paintPie.setColor(Color.WHITE);
            paintPie.setTextSize(30);
            paintPie.setStrokeWidth(4);

            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paintPie);

            //第二段直线
            lineStartX = lineEndX;
            lineStartY = lineEndY;

            /*  左右半圆的绘制差异
               1. 左半圆: 直线向 X 轴负方向延伸; 文字结束后紧接直线
               2. 右半圆: 直线向 X 轴正方向延伸; 直线后紧接文字
             */
            if(lineAngle > 90 && lineAngle <= 270){ //左半圆
                lineEndX = lineStartX - lineTwoLength;
                canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paintPie);
                canvas.drawText(item.getName(), lineEndX - wordGap - paintPie.measureText(item.getName()), lineEndY,paintPie);
            } else { //右半圆
                lineEndX = lineStartX + lineTwoLength;
                canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paintPie);
                canvas.drawText(item.getName(), lineEndX + wordGap, lineEndY,paintPie);
            }

            startAngle += sweepAngle;
            if (item.getNumber() == mMax) {
                canvas.restore();
            }
        }
    }

    private List<PieGraphItem> initPieGraphItems(){
        List<PieGraphItem> items = new ArrayList<PieGraphItem>();

        items.add( new PieGraphItem("Froyo", 2, Color.parseColor("#FFFFFFFF")));
        items.add( new PieGraphItem("Gingerbread", 1, Color.parseColor("#FFFF3399")));
        items.add( new PieGraphItem("Ice Cream Sandwich", 1, Color.parseColor("#FFFFCCCC")));
        items.add( new PieGraphItem("Jelly Beam", 23, Color.parseColor("#FF009900")));
        items.add( new PieGraphItem("Kitkat", 24, Color.parseColor("#FF3333FF")));
        items.add( new PieGraphItem("Lollipop", 35, Color.parseColor("#FFFF3333")));
        items.add( new PieGraphItem("Marshmallow", 15, Color.parseColor("#FFFF8000")));

        for (PieGraphItem pie : items){
            mTotal += pie.getNumber();
            mMax = Math.max(mMax, pie.getNumber());
        }
        return items;
    }

    private List<PieGraphItem> orderItems(List<PieGraphItem> items, int orderBy){

        switch(orderBy){
            case ORDER_BY_NAME_ASC:
                Collections.sort(items, new Comparator<PieGraphItem>() {
                    @Override
                    public int compare(PieGraphItem o1, PieGraphItem o2) {
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    }
                });
            case ORDER_BY_PER_ASC:
                Collections.sort(items, new Comparator<PieGraphItem>() {
                    @Override
                    public int compare(PieGraphItem o1, PieGraphItem o2) {
                        return Float.compare(o1.getNumber(),o2.getNumber());
                    }
                });
        }
        return items;
    }

    private class PieGraphItem{

        String name;
        float number;
        int color;

        public PieGraphItem(String name, float number, int color){
            this.name = name;
            this.number = number;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public float getNumber() {
            return number;
        }

        public int getColor() {
            return color;
        }

    }
}
