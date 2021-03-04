package custom.lx.com.customview.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：BitmapShaderView
 * @projectName CustomView
 * @description: Shader, BitmapShader学习
 * @data Created in 2021/03/04
 */
public class BitmapShaderView extends View {

    private Paint mPaint;
    private Bitmap mBmp;

    public BitmapShaderView(Context context) {
        this(context, null);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mBmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_spaceman);
        /**
         * Shader.TileMode.REPEAT:重复原来的图像填充剩余空间
         *
         * Shader.TileMode.MIRROR:重复使用镜像模式来填充多余的空间
         *
         * Shader.TileMode.CLAMP:用边缘色彩来填充多余k空间         */
        mPaint.setShader(new BitmapShader(mBmp, Shader.TileMode.MIRROR, Shader.TileMode.REPEAT));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        float left = 0;
//        float top = 0;
//        float right = getWidth();
//        float bottom = getHeight();

        /**
         * 无论利用绘图函数绘制多大的图像、从哪里绘制，都与Shader无关。
         * 因为Shader总是从控件的左上角开始的，而我们绘制的只是显示出来的部分而已。
         * 没有绘制的部分虽然已经生成，但是不会显示出来。
         */
        float left = getWidth() / 3;
        float top = getHeight() / 3;
        float right = getWidth() * 2/3;
        float bottom = getHeight() * 2/3;
        canvas.drawRect(left, top, right, bottom, mPaint);
    }
}
