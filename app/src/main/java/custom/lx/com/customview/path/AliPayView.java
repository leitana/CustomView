package custom.lx.com.customview.path;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * @author linxiao
 * @title：SuccessView
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/01/27
 */
public class AliPayView extends View {

    private Paint mPaint;

    private Path mCirclePath;
    private Path mDstPath;
    private PathMeasure mPathMeasure;

    private float mCentX = 100;
    private float mCentY = 100;
    private float mRadius = 50;

    private float mCurAnimValue;

    private boolean isOne = true;

    public AliPayView(Context context) {
        super(context);
        init();
    }

    public AliPayView(Context context, @Nullable AttributeSet attrs) {
        this(context);
    }

    public AliPayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);

        mDstPath = new Path();
        mCirclePath = new Path();

        mCirclePath.addCircle(mCentX, mCentY, mRadius, Path.Direction.CW);

        mCirclePath.moveTo(mCentX - mRadius / 2, mCentY);
        mCirclePath.lineTo(mCentX, mCentY + mRadius / 2);
        mCirclePath.lineTo(mCentX + mRadius / 2, mCentY - mRadius / 3);

        mPathMeasure = new PathMeasure(mCirclePath, false);

        /**
         * mCurAnimValue 0 ， 2肯定会出现
         * 不一定会出现 1 这个值
         */
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f, 2f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurAnimValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(4000);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        Log.i("AliPayView", mCurAnimValue + "");
        if (mCurAnimValue < 1) {
            isOne = true;
            float stop = mPathMeasure.getLength() * mCurAnimValue;
            mPathMeasure.getSegment(0, stop, mDstPath, true);
        }
//        else if (mCurAnimValue == 1.0) {
//            mPathMeasure.getSegment(0, mPathMeasure.getLength(), mDstPath, true);
//            /**
//             * path可以由多条曲线构成，
//             * getLength()、getSegment()还是其他函数，都只会针对其中第一条线段进行计算
//             * 而nextContour()就是用于跳转到下一条曲线的函数
//             */
//            mPathMeasure.nextContour();
//
//            Log.i("AliPayView", mPathMeasure.nextContour() + "");
//        }
        else {
            if (isOne) {
                mPathMeasure.getSegment(0, mPathMeasure.getLength(), mDstPath, true);
                mPathMeasure.nextContour();
                isOne = false;
            }
            float stop = mPathMeasure.getLength() * (mCurAnimValue - 1);
            mPathMeasure.getSegment(0, stop, mDstPath, true);
        }
        canvas.drawPath(mDstPath, mPaint);
    }
}
