package custom.lx.com.customview.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.widget.ImageView;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：WaterMarkActivity
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/03/15
 */
public class WaterMarkActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watermark);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_love);
//        Bitmap watermark = BitmapFactory.decodeResource(getResources(), R.mipmap.cucumber);
        String txt = "AsukaAsukaAsukaAsuka";
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(15);
        Bitmap txtBitmap = Bitmap.createBitmap((int)textPaint.measureText(txt) + 20,100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(txtBitmap);
        canvas.drawBitmap(txtBitmap, 0, 0, null);
        StaticLayout sl= new StaticLayout(txt, textPaint, txtBitmap.getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        sl.draw(canvas);
        Bitmap result = createWarkMark(bitmap, txtBitmap);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(result);
    }

    /**
     * 画水印
     * @param src
     * @param waterMark
     * @return
     */
    private Bitmap createWarkMark(Bitmap src, Bitmap waterMark){
        if (src == null) {
            return null;
        }

        int w = src.getWidth();
        int h = src.getHeight();
        int ww = waterMark.getWidth();
        int hh = waterMark.getHeight();

        //创建一个新的空白图像
        Bitmap wmBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(wmBitmap);
        //画原图
        canvas.drawBitmap(src, 0, 0, null);
        //在src的右下角画水印
        canvas.drawBitmap(waterMark, w - ww + 5, h - hh + 5, null);
        return wmBitmap;
    }
}
