package custom.lx.com.customview.surfaceview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：SurfaceViewActivity
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/03/16
 */
public class SurfaceViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surfaceview);
    }
}
