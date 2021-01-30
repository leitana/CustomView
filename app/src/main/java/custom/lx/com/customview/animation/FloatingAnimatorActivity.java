package custom.lx.com.customview.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：FloatingAnimatorActivity
 * @projectName CustomView
 * @description: 属性动画学习 ———— AnimatorSet
 * @data Created in 2021/01/13
 */
public class FloatingAnimatorActivity extends Activity {

    private Button menu;
    private Button mItemButton1;
    private Button mItemButton2;
    private Button mItemButton3;
    private Button mItemButton4;
    private Button mItemButton5;

    private boolean mIsMenuOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floating_activity);
        menu = (Button) findViewById(R.id.bt_menu);
        mItemButton1 = (Button) findViewById(R.id.menu1);
        mItemButton2 = (Button) findViewById(R.id.menu2);
        mItemButton3 = (Button) findViewById(R.id.menu3);
        mItemButton4 = (Button) findViewById(R.id.menu4);
        mItemButton5 = (Button) findViewById(R.id.menu5);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsMenuOpen) {
                    mIsMenuOpen = true;
                    openMenu();
                } else {
                    mIsMenuOpen = false;
                    closeMenu();
                }
            }
        });
    }

//    public void onClick(View v){
//        if (!mIsMenuOpen) {
//            mIsMenuOpen = true;
//            openMenu();
//        } else {
//            mIsMenuOpen = false;
//            closeMenu();
//        }
//    }

    private void openMenu(){
        doAnimateOpen(mItemButton1, 0, 5, 300);
        doAnimateOpen(mItemButton2, 1, 5, 300);
        doAnimateOpen(mItemButton3, 2, 5, 300);
        doAnimateOpen(mItemButton4, 3, 5, 300);
        doAnimateOpen(mItemButton5, 4, 5, 300);
    }

    private void closeMenu() {
        doAnimateClose(mItemButton1, 0, 5, 300);
        doAnimateClose(mItemButton2, 1, 5, 300);
        doAnimateClose(mItemButton3, 2, 5, 300);
        doAnimateClose(mItemButton4, 3, 5, 300);
        doAnimateClose(mItemButton5, 4, 5, 300);
    }

    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(90) / (total - 1) * index; //计算角度
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画

        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        );
        set.setDuration(500).start();
    }

    private void doAnimateClose(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI * index / ((total - 1) * 2); //计算角度
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画

        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        );
        set.setDuration(500).start();
    }

}
