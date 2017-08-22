package custom.lx.com.customview.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import custom.lx.com.customview.R;
import custom.lx.com.customview.base.BaseActivity;

/**
 * Created by 11300 on 2017/8/22.
 */

public class TweenAnimationActivity extends BaseActivity {
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

    private Animation alphaAnimation;
    private Animation scaleAnimation;
    private AnimationSet animationSet;

    /**
     *
     AccelerateInterpolator 加速，开始时慢中间加速

     DecelerateInterpolator 减速，开始时快然后减速

     AccelerateDecelerateInterolator 先加速后减速，开始结束时慢，中间加速

     AnticipateInterpolator 反向，先向相反方向改变一段再加速播放

     AnticipateOvershootInterpolator 反向加超越，先向相反方向改变，再加速播放，会超出目的值然后缓慢移动至目的值

     BounceInterpolator 跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100

     CycleIinterpolator 循环，动画循环一定次数，值的改变为一正弦函数：Math.sin(2* mCycles* Math.PI* input)

     LinearInterpolator 线性，线性均匀改变

     OvershootInterpolator超越，最后超出目的值然后缓慢改变到目的值
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        ButterKnife.bind(this);
        setBackBtn();
        setTitle("Tween Animation");
        //加载xml资源里面的动画
        alphaAnimation = AnimationUtils.loadAnimation(TweenAnimationActivity.this, R.anim.anim_alpha);
        scaleAnimation = AnimationUtils.loadAnimation(TweenAnimationActivity.this, R.anim.anim_scale);
        animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.anim_set);

        /**
         * 动画监听器
         */
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始时调用
                Toast.makeText(TweenAnimationActivity.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束时调用
                Toast.makeText(TweenAnimationActivity.this, "end", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //动画重复时调用
                Toast.makeText(TweenAnimationActivity.this, "repetition", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.bt_alpha, R.id.bt_scale, R.id.bt_rotate, R.id.bt_translate, R.id.tb_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_alpha:
//                Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
                ivWheel.startAnimation(alphaAnimation);
                break;
            case R.id.bt_scale:
//                Animation scaleAnimation = new ScaleAnimation(0.0f, 1.5f, 0.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                scaleAnimation.setDuration(500);//设置动画持续时间为500毫秒
//                scaleAnimation.setFillAfter(false);//如果fillAfter的值为true,则动画执行后，控件将停留在执行结束的状态
//                scaleAnimation.setFillBefore(true);//如果fillBefore的值为true，则动画执行后，控件将回到动画执行之前的状态
//                scaleAnimation.setRepeatCount(3);//设置动画循环次数
//                scaleAnimation.setRepeatMode(Animation.REVERSE);
//                scaleAnimation.setStartOffset(0);
//                scaleAnimation.setInterpolator(this, android.R.anim.decelerate_interpolator);//设置动画插入器
                ivWheel.startAnimation(scaleAnimation);
                break;
            case R.id.bt_rotate:
                Animation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(this, android.R.anim.accelerate_decelerate_interpolator);//设置动画插入器
                ivWheel.startAnimation(rotateAnimation);
                break;
            case R.id.bt_translate:
                Animation translateAnimation = new TranslateAnimation(0, 100, 0, 0);
                translateAnimation.setDuration(500);
                translateAnimation.setInterpolator(this, android.R.anim.cycle_interpolator);//设置动画插入器
                translateAnimation.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
                ivWheel.startAnimation(translateAnimation);
                break;
            case R.id.tb_set:
                ivWheel.startAnimation(animationSet);
                break;
        }
    }
}
