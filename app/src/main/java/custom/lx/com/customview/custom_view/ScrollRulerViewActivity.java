package custom.lx.com.customview.custom_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：ScrollRulerViewActivity
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/01/31
 */
public class ScrollRulerViewActivity extends AppCompatActivity {
    private ScrollRulerView rulerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rulerview);
        rulerView = (ScrollRulerView) findViewById(R.id.rulerView);
        rulerView.setCurrentValue(5);
    }
}
