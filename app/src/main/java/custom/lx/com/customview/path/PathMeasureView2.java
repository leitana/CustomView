package custom.lx.com.customview.path;

import android.annotation.SuppressLint;
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
 * @title：PathMeasureView2
 * @projectName CustomView
 * @description:
 *
 * getSegment()的用法
 * 这个API用于截取整个Path中的某个片段，通过参数startD和stopD来控制截取的长度。
 * 并将截取后的Path保存到参数dst中。
 * 最后一个参数startWithMoveTo 表示起始点是否使用moveTo将路径的新起始点移到结果Path的起点，通常设置为true。
 * 以保证每次截取的Path都是正常的、完整的，通常和dst一起使用，因为dst中保存的path是被不断添加的，而不是每次覆盖的；
 * 如果设置为false，则新增的片段会从上一次Path终点开始计算，这样保证截取的Path片段是连续的。
 *
 *
 * @data Created in 2021/01/24
 */
public class PathMeasureView2 extends View {

    private Paint paint = new Paint();

    private Path path = new Path();

    public PathMeasureView2(Context context) {
        super(context);
    }

    public PathMeasureView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathMeasureView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(100, 100);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);

        // Path.Direction.CW 顺时针
        // Path.Direction.CCW 逆时针
        path.addRect(-50, -50, 50, 50, Path.Direction.CW);

        Path dst = new Path();
        PathMeasure measure = new PathMeasure(path, false);
        /**
         * 截取长度为 0 - 150的这段路径。截取成功后，把新的路径添加到dst路径中，最后将dst画出来。
         *
         * 路径截取是从路径的左上角为起点开始的
         * 路径截取方向与路径生成方向相同
         * 如果dst中有路径，原有路径会被保存下来，截取片段添加到dst中，而不是覆盖。
         */
        measure.getSegment(0, 150, dst, true);
        canvas.drawPath(dst, paint);
    }
}
