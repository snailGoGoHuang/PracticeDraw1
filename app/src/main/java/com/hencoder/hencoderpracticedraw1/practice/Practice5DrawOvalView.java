package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice5DrawOvalView extends View {

    private static final float RECF_WIDTH = 400f;
    private static final float RECF_LENGTH = 200f;

    public Practice5DrawOvalView(Context context) {
        super(context);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//      练习内容：使用 canvas.drawOval() 方法画椭圆

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        float centerX = this.getWidth()/2;
        float centerY = this.getHeight()/2;

        float left = centerX - RECF_WIDTH/2;
        float top = centerY - RECF_LENGTH/2;
        float right = centerX + RECF_WIDTH/2;
        float bottom = centerY + RECF_LENGTH/2;
        canvas.drawOval(left, top, right, bottom, paint);

    }
}
