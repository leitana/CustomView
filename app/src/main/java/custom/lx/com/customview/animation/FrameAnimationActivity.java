package custom.lx.com.customview.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import custom.lx.com.customview.R;
import custom.lx.com.customview.base.BaseActivity;

/**
 * Created by 11300 on 2017/8/18.
 */

public class FrameAnimationActivity extends BaseActivity {
    @BindView(R.id.load)
    ImageView load;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_frame_layout);
        ButterKnife.bind(this);
        setBackBtn();
        setTitle("Frame Animation");
        load.setImageResource(R.drawable.frame_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) load.getDrawable();
        animationDrawable.start();
    }
}
