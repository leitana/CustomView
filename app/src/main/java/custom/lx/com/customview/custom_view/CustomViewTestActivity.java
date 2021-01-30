package custom.lx.com.customview.custom_view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：CostomViewTestActivity
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/01/10
 */
public class CustomViewTestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customview_activity);
    }
}
