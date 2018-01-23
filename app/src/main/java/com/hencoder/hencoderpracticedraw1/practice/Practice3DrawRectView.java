package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice3DrawRectView extends View {

    private static final int HALF_LINE = 200;

    public Practice3DrawRectView(Context context) {
        super(context);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//      练习内容：使用 canvas.drawRect() 方法画矩形

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        int centerX = Math.round(getWidth()/2);
        int centerY = Math.round(getHeight()/2);

        canvas.drawRect( centerX - HALF_LINE, centerY - HALF_LINE, centerX + HALF_LINE, centerY + HALF_LINE, paint);
    }
}
