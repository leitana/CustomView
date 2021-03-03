package custom.lx.com.customview.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author linxiao
 * @title：NormalGestureTrackView
 * @projectName CustomView
 * @description: 手势追踪（path追踪手指画图形加入贝塞尔曲线）
 * @data Created in 2021/03/02
 */
public class BezierGestureTrackView extends View {

    private Path mPath = new Path();
    private Paint mPaint;

    public BezierGestureTrackView(Context context) {
        this(context, null);
    }

    public BezierGestureTrackView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierGestureTrackView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    private float mPreX, mPreY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_MOVE:
//                mPath.lineTo(event.getX(), event.getY());
                float endX = (mPreX + event.getX()) / 2;
                float endY = (mPreY + event.getY()) / 2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX = event.getX();
                mPreY = event.getY();
                /**
                 * 也可以使用 invalidate();
                 * 这两个函数都是重绘控件，区别就是 invalidate 一定要在主线程执行
                 * 否则会报错，而 postInvalidate 可以在任何线程中执行，因为它是通过发送消息来实现的
                 * 所以速度可能没有直接调用 invalidate()函数那么快
                 */
                postInvalidate();

                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
