package custom.lx.com.customview.shape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：MagnifierView
 * @projectName CustomView
 * @description: 放大镜效果
 * @data Created in 2021/03/06
 */
public class MagnifierView extends View {

    private Bitmap bitmap;
    private ShapeDrawable drawable;
    //放大镜的半径
    private static final int RADIUS = 200;

    //放大倍数
    private static final int FACTOR = 3;

    private Matrix matrix = new Matrix();

    public MagnifierView(Context context) {
        this(context, null);
    }

    public MagnifierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MagnifierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        matrix.setTranslate(RADIUS - x * FACTOR, RADIUS - y * FACTOR);
        drawable.getPaint().getShader().setLocalMatrix(matrix);

        drawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap == null) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_love);
            bitmap = Bitmap.createScaledBitmap(bmp, getWidth(), getHeight(), false);
            BitmapShader shader = new BitmapShader(
                    Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * FACTOR, bitmap.getHeight() * FACTOR, true),
                    Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
            );
            drawable = new ShapeDrawable(new OvalShape());
            drawable.getPaint().setShader(shader);
            drawable.setBounds(0, 0, RADIUS * 2, RADIUS * 2);
        }
        canvas.drawBitmap(bitmap, 0, 0, null);
        drawable.draw(canvas);
    }
}
