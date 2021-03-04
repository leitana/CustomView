package custom.lx.com.customview.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：TelescopeView
 * @projectName CustomView
 * @description: 望远镜效果
 * @data Created in 2021/03/04
 */

/**
 * 原理：先准备一张背景图，然后将背景图作为BitmapShader。只需要在手指所在位置画一个圆
 */
public class TelescopeView extends View {

    private Paint mPaint;
    private Bitmap mBitmap, mBitmapBG;
    private int mDx = -1, mDy = -1;

    public TelescopeView(Context context) {
        this(context, null);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_love);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                postInvalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mDx = -1;
                mDy = -1;
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    /**
     *
     * @param canvas
     *
     * 这里分两步。
     * 第一步：将图片缩放到控件大小，以完全覆盖控件，否则就会使用BitmapShader的填充模式。
     * 这里先新建一张空白的位图，这张空白位图的大小与控件的大小一样，然后对背景图进行拉伸，画到这张空白的位图上。
     * 之所以在onDraw函数中创建mBitmapBG 而不是在代码中创建是因为在初始化时，getWidth和getHeight函数是获取不到值的。
     *
     * 第二步：在mDx、mDy都不是-1时，将新建的mBitmapBG作为BitmapShader设置给Paint，然后在手指所在的位置画一个圆，把圆部分的图像显示出来
     *
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmapBG == null) {
            mBitmapBG = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvasBG = new Canvas(mBitmapBG);
            canvasBG.drawBitmap(mBitmap, null, new Rect(0, 0, getWidth(), getHeight()), mPaint);
        }

        if (mDx != -1 && mDy != -1) {
            mPaint.setShader(new BitmapShader(mBitmapBG, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            canvas.drawCircle(mDx, mDy, 150, mPaint);
        }

    }
}
