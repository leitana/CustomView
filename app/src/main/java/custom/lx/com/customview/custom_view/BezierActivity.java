package custom.lx.com.customview.custom_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：BezierActivity
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/03/02
 */
public class BezierActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
    }
}
