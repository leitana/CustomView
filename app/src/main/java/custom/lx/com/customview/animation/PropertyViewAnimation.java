package custom.lx.com.customview.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import custom.lx.com.customview.R;
import custom.lx.com.customview.base.BaseActivity;

/**
 * Created by 11300 on 2017/8/25.
 */

public class PropertyViewAnimation extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertyanim);
        setBackBtn();
        setTitle("Custom Property View");
    }
}
