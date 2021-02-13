package custom.lx.com.customview.svg;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：SvgTestActivity
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/02/10
 */
public class SvgTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat =
                AnimatedVectorDrawableCompat.create(SvgTestActivity.this, R.drawable.svg_animated_vector);
        imageView.setImageDrawable(animatedVectorDrawableCompat);
        ((Animatable)imageView.getDrawable()).start();

    }
}
