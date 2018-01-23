package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice7DrawRoundRectView extends View {

    public Practice7DrawRoundRectView(Context context) {
        super(context);
    }

    public Practice7DrawRoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice7DrawRoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawRoundRect() 方法画圆角矩形

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        float centerX = getWidth()/2;
        float centerY = getHeight()/2;

        float recWidth = 400f;
        float recHeight = 200f;

        float left = centerX - recWidth/2;
        float top = centerY - recHeight/2;
        float right = centerX + recWidth/2;
        float bottom = centerY + recHeight/2;

        RectF rectF = new RectF(left, top, right, bottom);

        float rx = 50f;
        float ry = 50f;
        canvas.drawRoundRect(rectF, rx, ry, paint);
    }
}
