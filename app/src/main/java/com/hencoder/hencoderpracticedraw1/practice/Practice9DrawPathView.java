package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形

        float radius = 70f;
        float heartHeight = 180f;
        float centerX = getWidth()/2;
        float centerY = getHeight()/2;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        //1. 右边弧形部分
        Path pathR = new Path();
        RectF rectFR = new RectF(centerX, centerY - radius, centerX + 2 * radius, centerY + radius);
        pathR.addArc(rectFR, -180, 220);
        canvas.drawPath(pathR, paint);

        //2. 右边斜线部分
        pathR.lineTo(centerX, centerY + heartHeight);
        pathR.close();
        canvas.drawPath(pathR, paint);

        //3. 左边弧形部分
        Path pathL = new Path();
        //pathL.moveTo(centerX, centerY);
        RectF rectFL = new RectF(centerX - 2 * radius, centerY - radius, centerX, centerY + radius);
        pathL.addArc(rectFL, 0, -220);
        canvas.drawPath(pathL, paint);

        //4. 左边斜线部分
        pathL.lineTo(centerX, centerY + heartHeight);
        //pathL.close();
        canvas.drawPath(pathL, paint);
    }
}
