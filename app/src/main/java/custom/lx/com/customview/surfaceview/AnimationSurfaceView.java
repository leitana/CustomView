package custom.lx.com.customview.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：AnimationSurfaceView
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/03/16
 */
public class AnimationSurfaceView extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private boolean flag = false;   // 线程标识
    private Bitmap bitmap_bg;       // 背景图

    private float mSurfaceWidth, mSurfaceHeight; // 屏幕宽、高
    private int mBitposX;    // 开始绘制图片的X坐标
    private Canvas mCanvas;
    private Thread thread;

    // 背景移动状态
    private enum State{
        LEFT, RIGHT
    }

    private State state = State.LEFT;

    private final int BITMAP_STEP = 1;  // 背景画布移动步伐


    public AnimationSurfaceView(Context context) {
        this(context, null);
    }

    public AnimationSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimationSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                flag = true;
                startAnimation();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                flag = false;
            }
        });
    }

    private void startAnimation() {
        mSurfaceWidth = getWidth();
        mSurfaceHeight = getHeight();
        /**
         * 将图片宽度放大到3 / 2倍，高度充满屏幕
         */
        int mWidth = (int) (mSurfaceWidth * 3 / 2);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_nianshou);
        bitmap_bg = Bitmap.createScaledBitmap(bitmap, mWidth, (int) mSurfaceHeight, true);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    mCanvas = surfaceHolder.lockCanvas();
                    drawView();
                    surfaceHolder.unlockCanvasAndPost(mCanvas);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    protected void drawView(){
        //绘制背景
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR); //清空屏幕
        mCanvas.drawBitmap(bitmap_bg, mBitposX, 0, null);//绘制当前屏幕背景

        switch (state) {
            case LEFT:
                mBitposX = mBitposX - BITMAP_STEP; //画布左移
                break;
            case RIGHT:
                mBitposX = mBitposX + BITMAP_STEP; //画布右移
                break;
            default:
                break;
        }

        if (mBitposX <= -mSurfaceWidth / 2) {
            state = State.RIGHT;
        }
        if (mBitposX >= 0) {
            state = State.LEFT;
        }
    }
}
