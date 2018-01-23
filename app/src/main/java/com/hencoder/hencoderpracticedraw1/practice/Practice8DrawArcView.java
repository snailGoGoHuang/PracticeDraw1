package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形

        long centerX = getWidth()/2;
        long centerY = getHeight()/2;

        float recWidth = 600f;
        float recHeight = 400f;

        float left = centerX - recWidth/2;
        float top = centerY - recHeight/2;
        float right = centerX + recWidth/2;
        float bottom = centerY + recHeight/2;
        RectF rectF = new RectF(left, top, right, bottom);

        //画扇形
        Paint paintSector2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintSector2.setColor(Color.BLACK);
        paintSector2.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF, -110, 100, true, paintSector2);

        //画实心弧形
        Paint paintSector = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintSector.setColor(Color.BLACK);
        paintSector.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF, 20, 140, false, paintSector);

        //画勾线弧形
        Paint paintArc = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintArc.setColor(Color.BLACK);
        paintArc.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF, 180, 60, false, paintArc);
    }
}
