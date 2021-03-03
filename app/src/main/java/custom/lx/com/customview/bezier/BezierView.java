package custom.lx.com.customview.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：BezierView
 * @projectName CustomView
 * @description: 贝塞尔曲线学习
 * @data Created in 2021/03/02
 */
public class BezierView extends View {

    public BezierView(Context context) {
        super(context);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path = new Path();
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        path.moveTo(100, 300);
        //贝塞尔曲线函数 x1 y1是控制点坐标， x2 y2是终点坐标
        path.quadTo(200, 200, 300, 300);
        path.quadTo(400, 400, 500, 300);
        canvas.drawPath(path, paint);
    }
}
