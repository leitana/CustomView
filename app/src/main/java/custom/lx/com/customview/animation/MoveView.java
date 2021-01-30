package custom.lx.com.customview.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import custom.lx.com.customview.R;

/**
 * Created by linxiao on 2018/5/30.
 */

public class MoveView extends View{
    private Context mContext;
    public static final float RADIUS = 50f;

    private Point currentPoint;
    private Paint mPaint;

    private int mLastX;
    private int mLastY;

    public MoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.clickspan_color));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
        } else {
            drawCircle(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
//                scrollTo(-(int)event.getX(),-(int) event.getY());
                setTranslationX(getX() + event.getX() - mLastX);
                setTranslationY(getY() + event.getY() - mLastY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;

    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        mLastX = (int) x;
        mLastY = (int) y;
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }
}
