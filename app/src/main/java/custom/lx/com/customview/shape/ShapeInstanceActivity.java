package custom.lx.com.customview.shape;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：ShapeInstanceActivity
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/03/06
 */
public class ShapeInstanceActivity extends AppCompatActivity {

    private TextView tv;
    private Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
        tv = (TextView) findViewById(R.id.tv);
        bt = (Button) findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradientDrawable gradientDrawable = (GradientDrawable) tv.getBackground();
                gradientDrawable.setCornerRadius(20);
            }
        });
    }
}
