package custom.lx.com.customview.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author linxiao
 * @title：PathMeasureView
 * @projectName CustomView
 * @description: PathMeasure最基础 画线
 * @data Created in 2021/01/24
 */
public class PathMeasureView1 extends View {

    private Paint paint = new Paint();
    private Path path = new Path();

    private PathMeasure measure1 = new PathMeasure();
    private PathMeasure measure2 = new PathMeasure();

    public PathMeasureView1(Context context) {
        super(context);
    }

    public PathMeasureView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathMeasureView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(50, 50);// 将画布移动到50，50
        path.moveTo(0, 0);
        path.lineTo(0 ,100);
        path.lineTo(100, 100);
        path.lineTo(100, 0);

        measure1.setPath(path, true);
        measure2.setPath(path, false);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawPath(path, paint);
    }


}
