package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {

    private static final float MARGIN_LAYOUT_HORIZONTAL = 200f;
    private static final float MARGIN_LINE_SPACING = 60f;
    private static final float MARGIN_VETICAL_SPACING = 60f;
    private static final int ROWS = 2;
    private static final int COLS = 2;

    public Practice2DrawCircleView(Context context) {
        super(context);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆

        CircleInfo[][] infos = generateCirclesInfo(ROWS,COLS);
        Paint[][] paints = getPaints(ROWS,COLS);

        for(int row = 0; row < ROWS; row++){
            for (int col = 0; col < COLS; col++){
                CircleInfo  circleInfo = infos[row][col];
                Paint paint = paints[row][col];

                if( circleInfo != null && paint != null){
                    canvas.drawCircle(circleInfo.cx, circleInfo.cy, circleInfo.radius, paint);
                }
            }
        }

    }

    /**
     * 根据某些布局参数, 自动生成每个圆的原点坐标.
     * 每个圆的半径相同, 是根据需要显示的圆的数量动态决定的.
     * @param row
     * @param col
     * @return
     */
    private CircleInfo[][] generateCirclesInfo(int row, int col){
        CircleInfo[][] retInfo = new CircleInfo[row][col];

        int width = this.getWidth();
        int height = this.getHeight();

        int radius = 0 ;
        //计算每个圆的半径.  直径为剩余空间 / 每行的圆形数量, 半径再除2.
        if ( width > height) {
            float leftLineSpace = height - (col + 1) * MARGIN_VETICAL_SPACING;
            radius = Math.round((leftLineSpace/col)/2);
        } else {
            float leftLineSpace = width - 2 * MARGIN_LAYOUT_HORIZONTAL - (col) * MARGIN_LINE_SPACING;
            radius = Math.round((leftLineSpace/col)/2);
        }

        float cx = 0;
        float cy = 0;
        for( int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                //每一列的原点坐标的 x轴 位置相同
                //每一行的原点坐标的 y轴 位置相同
                cy = (i + 1) * MARGIN_VETICAL_SPACING + ((i+1)*2 - 1) * radius;
                cx = MARGIN_LAYOUT_HORIZONTAL + ( j + 1) * MARGIN_LINE_SPACING + ( (j+1)*2 - 1) * radius;
                CircleInfo info = new CircleInfo(cx, cy, radius);
                retInfo[i][j] = info;
            }
        }

        return retInfo;
    }

    /**
     * 定义每个圆的共有信息
     * @param row
     * @param column
     * @return
     */
    private Paint[][] getPaints(int row, int column){
        Paint[][] retPaints = new Paint[row][column];

//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        Paint paint0_0 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint0_0.setColor(Color.BLACK);
        paint0_0.setStyle(Paint.Style.FILL);
        retPaints[0][0] = paint0_0;

        Paint paint0_1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint0_1.setColor(Color.BLACK);
        paint0_1.setStyle(Paint.Style.STROKE);
        paint0_1.setStrokeWidth(3);
        retPaints[0][1] = paint0_1;

        Paint paint1_0 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1_0.setColor(Color.BLUE);
        paint1_0.setStyle(Paint.Style.FILL_AND_STROKE);
        retPaints[1][0] = paint1_0;

        Paint paint1_1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1_1.setColor(Color.BLACK);
        paint1_1.setStyle(Paint.Style.STROKE);
        paint1_1.setStrokeWidth(60);
        retPaints[1][1] = paint1_1;

        return retPaints;
    }

    private class CircleInfo{
        float radius;
        float cx;
        float cy;

        CircleInfo(float cx, float cy, float radius){
            this.radius = radius;
            this.cx = cx;
            this.cy = cy;
        }
    }
}
