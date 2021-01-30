package custom.lx.com.customview.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author linxiao
 * @title：CustomView1
 * @projectName CustomView
 * @description: 自定义View学习
 *
 * 测试高度超过屏幕的场景
 *
 * ScrollView里面的子View高度设置不起作用。
 *
 * @data Created in 2021/01/10
 */
public class CustomView1 extends View {

    private final Paint mTextPaint = new Paint();

    public CustomView1(Context context) {
        super(context);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //xml或者代码设置的宽高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //默认的宽高
        int mWidth = 200;
        int mHeight = 4000;

        //设置为wrap_content的时候给一个默认值
        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT &&
                getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, height);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(width, mHeight);
        } else {
            setMeasuredDimension(width, height);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(23);
        mTextPaint.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawColor(Color.BLUE);
        for (int i = 0; i < 100; i++) {
            canvas.drawText("TEST" + i, 0, i * 40, mTextPaint);
        }

    }
}
