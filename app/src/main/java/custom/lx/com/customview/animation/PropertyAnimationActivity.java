package custom.lx.com.customview.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import custom.lx.com.customview.R;
import custom.lx.com.customview.base.BaseActivity;

/**
 * Created by 11300 on 2017/8/24.
 */

public class PropertyAnimationActivity extends BaseActivity {
    @BindView(R.id.bt_alpha)
    Button btAlpha;
    @BindView(R.id.bt_scale)
    Button btScale;
    @BindView(R.id.bt_rotate)
    Button btRotate;
    @BindView(R.id.bt_translate)
    Button btTranslate;
    @BindView(R.id.tb_set)
    Button tbSet;
    @BindView(R.id.iv_wheel)
    ImageView ivWheel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        ButterKnife.bind(this);
        setBackBtn();
    }

    @OnClick({R.id.bt_alpha, R.id.bt_scale, R.id.bt_rotate, R.id.bt_translate, R.id.tb_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_alpha:
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(ivWheel, "alpha", 1f, 0f, 1f);
                alphaAnimator.setDuration(5000);
                alphaAnimator.start();
                break;
            case R.id.bt_scale:
                ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(ivWheel, "scaleY", 1f, 3f, 1f);
                scaleAnimator.setDuration(5000);
                scaleAnimator.start();
                break;
            case R.id.bt_rotate:
                ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(ivWheel, "rotation", 0f, 360f);
                rotateAnimator.setDuration(5000);
                rotateAnimator.start();
                break;
            case R.id.bt_translate:
                float curTranslationX = ivWheel.getTranslationX();
                ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(ivWheel, "translationX", curTranslationX, -500, curTranslationX);
                translateAnimator.setDuration(5000);
                translateAnimator.start();
                break;
            case R.id.tb_set:
                /**
                 * 代码
                 */
//                ObjectAnimator moveIn = ObjectAnimator.ofFloat(ivWheel, "translationX", -500f, 0f);
//                ObjectAnimator rotate = ObjectAnimator.ofFloat(ivWheel, "rotation", 0f, 360f);
//                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(ivWheel, "alpha", 1f, 0f, 1f);
//                AnimatorSet animSet = new AnimatorSet();
//                animSet.play(rotate).with(fadeInOut).after(moveIn);
//                animSet.setDuration(5000);
//                animSet.start();
                /**
                 * xml
                 */
                Animator animator = AnimatorInflater.loadAnimator(PropertyAnimationActivity.this, R.animator.anim_set);
                animator.setTarget(ivWheel);
                animator.start();
                break;
        }
   }
}
