package custom.lx.com.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import custom.lx.com.customview.animation.AnimationMainActivity;
import custom.lx.com.customview.animation.FloatingAnimatorActivity;
import custom.lx.com.customview.animation.viewgroupanimation.ViewGroupActivity;
import custom.lx.com.customview.beans.HomeBean;
import custom.lx.com.customview.custom_view.CustomViewTestActivity;
import custom.lx.com.customview.custom_view.ScrollRulerViewActivity;
import custom.lx.com.customview.path.PathMeasureActivity;
import custom.lx.com.customview.recyclerview.RecyclerMainActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private List<HomeBean> homeBeanList;
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        homeAdapter = new HomeAdapter(R.layout.list_item, homeBeanList);
        homeAdapter.openLoadAnimation();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        View top = getLayoutInflater().inflate(R.layout.top_view, (ViewGroup) rvList.getParent(), false);
        homeAdapter.addHeaderView(top);
        rvList.setAdapter(homeAdapter);

        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                switch (position){
                    case 0:
                        intent.setClass(MainActivity.this, RecyclerMainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, AnimationMainActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this, FloatingAnimatorActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(MainActivity.this, CustomViewTestActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(MainActivity.this, ViewGroupActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(MainActivity.this, PathMeasureActivity.class);
                        startActivity(intent);
                    case 6:
                        intent.setClass(MainActivity.this, ScrollRulerViewActivity.class);
                        startActivity(intent);
                    default:
                        break;
                }
            }
        });
    }

    private void initData() {
        homeBeanList = new ArrayList<>();
        HomeBean recyclerView = new HomeBean();
        recyclerView.setTitle("RecyclerView Demo");
        recyclerView.setContent("RecyclerView Demos With BRVAH");
        homeBeanList.add(recyclerView);

        HomeBean animation = new HomeBean();
        animation.setTitle("Android Animation");
        animation.setContent("Three Types Of Animation For Android");
        homeBeanList.add(animation);

        HomeBean animatorSet = new HomeBean();
        animatorSet.setTitle("属性动画学习------FloatingButton");
        animatorSet.setContent("AnimatorSet");
        homeBeanList.add(animatorSet);

        HomeBean custom = new HomeBean();
        custom.setTitle("自定义View");
        custom.setContent("自定义View测试");
        homeBeanList.add(custom);

        HomeBean animateLayoutChanges = new HomeBean();
        animateLayoutChanges.setTitle("animateLayoutChanges");
        animateLayoutChanges.setContent("ViewGroup中的动画");
        homeBeanList.add(animateLayoutChanges);

        HomeBean pathMeasure = new HomeBean();
        pathMeasure.setTitle("PathMeasure");
        pathMeasure.setContent("PathMeasure实现路径动画");
        homeBeanList.add(pathMeasure);

        HomeBean scrollRuler = new HomeBean();
        scrollRuler.setTitle("scrollRuler");
        scrollRuler.setContent("可滑动刻度尺自定义View");
        homeBeanList.add(scrollRuler);
    }

    public class HomeAdapter extends BaseQuickAdapter<HomeBean, BaseViewHolder>{

        public HomeAdapter(@LayoutRes int layoutResId, @Nullable List<HomeBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeBean item) {
            helper.setText(R.id.tvName, item.getTitle());
            helper.setText(R.id.tvDesc, item.getContent());
        }
    }
}
