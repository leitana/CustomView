package custom.lx.com.customview.path;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：GetSegmentView
 * @projectName CustomView
 * @description:
 *
 * 通过 PathMeasureView2.class中的getSegment方法
 * 来做路径加载动画
 * 不断地给getSegment()函数设置逐渐增长的路劲长度，就会相应得到逐渐增长的路径片段
 *
 *  路径绘制是PathMeasure最常用的功能
 * @data Created in 2021/01/24
 */
public class GetSegmentView extends View {

    private Paint mPaint;

    private Path mCirclePath;
    private Path mDstPath;
    private PathMeasure mPathMeasure;

    private float mCurAnimValue;

    private Matrix matrix;
    private Bitmap mArrowBmp;
    private float[] pos;
    private float[] tan;

    public GetSegmentView(Context context) {
        super(context);
        init();
    }

    public GetSegmentView(Context context, @Nullable AttributeSet attrs) {
        this(context);
    }

    public GetSegmentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
//        matrix = new Matrix();
        mArrowBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_arrow_right);
        pos = new float[2];
        tan = new float[2];

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);

        mDstPath = new Path();
        mCirclePath = new Path();

        mCirclePath.addCircle(100, 100, 50, Path.Direction.CW);

        mPathMeasure = new PathMeasure(mCirclePath, true);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatCount(ValueAnimator.INFINITE);//无线循环
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurAnimValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 最基础的画圆
         */
//        canvas.drawColor(Color.WHITE);
//        float stop = mPathMeasure.getLength() * mCurAnimValue;
//        //由于每次调用getSegment()函数得到的路径都被添加到mDstPath中，
//        // 所以要先调用mDstPath.reset()函数清空之前生成的路径
//        mDstPath.reset();
//        mPathMeasure.getSegment(0, stop, mDstPath, true);
//        canvas.drawPath(mDstPath, mPaint);

        /**
         * 仿加载Loading动画
         */
        canvas.drawColor(Color.WHITE);
        mDstPath.reset();
        float length = mPathMeasure.getLength();
        float stop = length * mCurAnimValue;
        float start = ((mCurAnimValue < 0.5) ? 0 : (2 * mCurAnimValue - 1)) * length;
        mPathMeasure.getSegment(start, stop, mDstPath, true);
        canvas.drawPath(mDstPath, mPaint);

        /**
         * getPosTan()函数
         * 用于得到路径上某一长度的位置以及该位置的正切值。
         * public boolean getPosTan(float distance, float pos[], float tan[])
         * float distance：距离path起始点的长度
         * float pos[]：该点的坐标值。传入空数组 会返回
         * float tan[]：改点的正切值
         */
        mPathMeasure.getPosTan(stop, pos, tan);
        float degress = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
        matrix = new Matrix();
        matrix.postRotate(degress, mArrowBmp.getWidth() / 2, mArrowBmp.getHeight() / 2);
        matrix.postTranslate(pos[0] - mArrowBmp.getWidth() / 2, pos[1] - mArrowBmp.getHeight() / 2);
        canvas.drawBitmap(mArrowBmp, matrix, mPaint);



    }
}
