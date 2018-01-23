package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice4DrawPointView extends View {

    private static final float MARGIN_LINE_SPACING = 300f;
    private static final float MARGIN_VETICAL_SPACING = 200f;
    private static final float POINT_SIZE = 80f;
    private static final int ROWS = 1;
    private static final int COLS = 2;

    public Practice4DrawPointView(Context context) {
        super(context);
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPoint() 方法画点
//        一个圆点，一个方点
//        圆点和方点的切换使用 paint.setStrokeCap(cap)：`ROUND` 是圆点，`BUTT` 或 `SQUARE` 是方点

        PointInfo[][] points = generatePointsInfo(ROWS,COLS);
        Paint[][] paints = getPaints(ROWS,COLS);

        for(int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                PointInfo point = points[row][col];
                Paint paint = paints[row][col];

                if ( point != null && paint != null){
                    canvas.drawPoint(point.cx, point.cy, paint);
                }
            }
        }
    }

    /**
     * 根据点的总数量 和 点之间的间距, 动态计算每个点的坐标 (整体居中显示).
     * 点的大小由 @POINT_SIZE 固定.
     * @param row
     * @param col
     * @return
     */
    private PointInfo[][] generatePointsInfo(int row, int col){
        PointInfo[][] retInfo = new PointInfo[row][col];

        int width = this.getWidth();
        int height = this.getHeight();

        float leftWidthSpace = width - col * POINT_SIZE - (col - 1) * MARGIN_LINE_SPACING;
        float leftHeightSpace = height - row * POINT_SIZE - (row - 1) * MARGIN_VETICAL_SPACING;

        int marginLeft = Math.round(leftWidthSpace/2);
        int marginTop = Math.round(leftHeightSpace/2);

        float cx = 0;
        float cy = 0;
        for( int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cx = marginLeft + (2 * (j+1) - 1) * (POINT_SIZE/2) + j * MARGIN_LINE_SPACING;
                cy = marginTop + (2 * (i+1) - 1) * (POINT_SIZE/2) + i * MARGIN_VETICAL_SPACING;
                retInfo[i][j] = new PointInfo(cx, cy);
            }
        }

        return retInfo;
    }

    /**
     * 设置每个点的公有属性
     * @param row
     * @param col
     * @return
     */

    private Paint[][] getPaints(int row, int col){
        Paint[][] retPaints = new Paint[row][col];

        Paint paint0_0 = new Paint();
        paint0_0.setStrokeCap(Paint.Cap.ROUND);
        paint0_0.setStrokeWidth(POINT_SIZE);
        retPaints[0][0] = paint0_0;

        Paint paint0_1 = new Paint();
        paint0_1.setStrokeCap(Paint.Cap.SQUARE);
        paint0_1.setStrokeWidth(POINT_SIZE);
        retPaints[0][1] = paint0_1;

        return retPaints;
    }

    private class PointInfo{
        float cx;
        float cy;

        PointInfo(float cx, float cy){
            this.cx = cx;
            this.cy = cy;
        }
    }
}

