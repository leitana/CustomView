package custom.lx.com.customview.animation.viewgroupanimation;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import custom.lx.com.customview.R;

/**
 * @author linxiao
 * @title：ViewGroupActivity
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/01/17
 */
public class ViewGroupActivity extends Activity {

    private int i = 0;

    private Button btAdd;
    private Button btDelete;
    private LinearLayout linearlayoutContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgroup);
        btAdd = (Button) findViewById(R.id.bt_add);
        btDelete = (Button) findViewById(R.id.bt_delete);
        linearlayoutContainer = (LinearLayout) findViewById(R.id.linearlayoutcontainer);

        LayoutTransition transition = new LayoutTransition();
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 0f, 360f, 0f);
        transition.setAnimator(LayoutTransition.APPEARING, animIn);

        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotation", 0f, 90f, 0f);
        transition.setAnimator(LayoutTransition.DISAPPEARING, animOut);

        linearlayoutContainer.setLayoutTransition(transition);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView();
            }
        });


    }

    @SuppressLint("SetTextI18n")
    private void addView(){
        i++;
        Button button = new Button(this);
        button.setText("button" + i);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        linearlayoutContainer.addView(button);
    }

    private void removeView(){
        if (i > 0) {
            linearlayoutContainer.removeViewAt(0);
            i--;
        }
    }
}
