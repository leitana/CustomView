package custom.lx.com.customview.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import custom.lx.com.customview.R;
import custom.lx.com.customview.base.BaseActivity;
import custom.lx.com.customview.base.BaseRecyclerAdapter;
import custom.lx.com.customview.base.ViewHolder;
import custom.lx.com.customview.beans.HomeBean;

/**
 * Created by 11300 on 2017/8/18.
 */

public class AnimationMainActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private Context mContent = AnimationMainActivity.this;
    private List<HomeBean> homeBeanList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBackBtn();
//        initView();
        setTitle("Android Animation");
        initData();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(mContent, R.layout.list_item, homeBeanList);
        rvList.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                Intent intent = new Intent();
                switch (position){
                    case 0:
                        intent.setClass(AnimationMainActivity.this, FrameAnimationActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(AnimationMainActivity.this, TweenAnimationActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(AnimationMainActivity.this, PropertySimpleAnimationActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(AnimationMainActivity.this, PropertyViewAnimation.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
//                Snackbar.make(view, "111", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    private void initData() {
        homeBeanList = new ArrayList<>();

        HomeBean frame = new HomeBean();
        frame.setTitle("Frame Animation");
        frame.setContent("帧动画");
        homeBeanList.add(frame);

        HomeBean tweened = new HomeBean();
        tweened.setTitle("Tweened Animation");
        tweened.setContent("补间动画");
        homeBeanList.add(tweened);

        HomeBean property = new HomeBean();
        property.setTitle("Property Animation");
        property.setContent("属性动画");
        homeBeanList.add(property);

        HomeBean property2 = new HomeBean();
        property2.setTitle("Property Animation CustomView");
        property2.setContent("属性动画");
        homeBeanList.add(property2);
    }

    public class MyAdapter extends BaseRecyclerAdapter<HomeBean>{

        public MyAdapter(Context context, int layoutId, List<HomeBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, HomeBean homeBean) {
            TextView tvName = holder.getView(R.id.tvName);
            TextView tvDesc = holder.getView(R.id.tvDesc);
            tvName.setText(homeBean.getTitle());
            tvDesc.setText(homeBean.getContent());
        }
    }
}
