package custom.lx.com.customview.bezier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：AnimWaveView
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/03/03
 */
public class AnimWaveView extends View {

    private Paint mPaint;
    private Path mPath;
    private int mItemWaveLength = 1200;
    private int dx;

    public AnimWaveView(Context context) {
        this(context, null);
    }

    public AnimWaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.clickspan_color));
        mPaint.setStyle(Paint.Style.FILL);
        startAnim();
    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        int originY = getHeight() / 2;
        int halfWaveLen = mItemWaveLength / 2;
        mPath.moveTo(-mItemWaveLength + dx, originY);
        for (int i = -mItemWaveLength; i < getWidth() + mItemWaveLength; i += mItemWaveLength) {
            mPath.rQuadTo(halfWaveLen / 2, -100, halfWaveLen, 0);
            mPath.rQuadTo(halfWaveLen / 2, 100, halfWaveLen, 0);
        }
        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(0, getHeight());
        mPath.close();

        canvas.drawPath(mPath, mPaint);
    }
}
