package custom.lx.com.customview.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：AvatorView
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/03/04
 */

/**
 * 原理：
 * 首先将BitmapShader缩放到与控件的宽、高一致。
 * 由于我们要画的是一副圆形图像，所以必须缩放成一个正方形，只要正方形的边长与控件的宽度一致即可。
 * 同样是缩放BitmapShader，这里是使用Matrix来进行缩放
 */
public class AvatorView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;

    public AvatorView(Context context) {
        this(context, null);
    }

    public AvatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AvatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_nianshou);
        mPaint = new Paint();
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = new Matrix();
        float scale = getWidth() / mBitmap.getWidth();
        matrix.setScale(scale, scale);
        mBitmapShader.setLocalMatrix(matrix);
        mPaint.setShader(mBitmapShader);

        float half = getWidth() / 2;
        canvas.drawCircle(half, half, getWidth() / 2, mPaint);
    }
}
