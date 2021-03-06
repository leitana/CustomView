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
import custom.lx.com.customview.bezier.AnimWaveActivity;
import custom.lx.com.customview.bezier.BezierActivity;
import custom.lx.com.customview.bezier.BezierGestureTrackActivity;
import custom.lx.com.customview.bitmap.WaterMarkActivity;
import custom.lx.com.customview.custom_view.CustomViewTestActivity;
import custom.lx.com.customview.custom_view.NormalGestureTrackActivity;
import custom.lx.com.customview.custom_view.ScrollRulerViewActivity;
import custom.lx.com.customview.path.PathMeasureActivity;
import custom.lx.com.customview.recyclerview.RecyclerMainActivity;
import custom.lx.com.customview.shader.AvatorActivity;
import custom.lx.com.customview.shader.BitmapShaderActivity;
import custom.lx.com.customview.shape.MagnifierActivity;
import custom.lx.com.customview.shape.MagnifierView;
import custom.lx.com.customview.shape.ShapeInstanceActivity;
import custom.lx.com.customview.surfaceview.SurfaceViewActivity;
import custom.lx.com.customview.svg.SvgTestActivity;

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
                        break;
                    case 6:
                        intent.setClass(MainActivity.this, ScrollRulerViewActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent.setClass(MainActivity.this, SvgTestActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent.setClass(MainActivity.this, BezierActivity.class);
                        startActivity(intent);
                        break;
                    case 9:
                        intent.setClass(MainActivity.this, NormalGestureTrackActivity.class);
                        startActivity(intent);
                        break;
                    case 10:
                        intent.setClass(MainActivity.this, BezierGestureTrackActivity.class);
                        startActivity(intent);
                        break;

                    case 11:
                        intent.setClass(MainActivity.this, AnimWaveActivity.class);
                        startActivity(intent);
                        break;

                    case 12:
                        intent.setClass(MainActivity.this, BitmapShaderActivity.class);
                        startActivity(intent);
                        break;
                    case 13:
                        intent.setClass(MainActivity.this, AvatorActivity.class);
                        startActivity(intent);
                        break;

                    case 14:
                        intent.setClass(MainActivity.this, ShapeInstanceActivity.class);
                        startActivity(intent);
                        break;

                    case 15:
                        intent.setClass(MainActivity.this, MagnifierActivity.class);
                        startActivity(intent);
                        break;

                    case 16:
                        intent.setClass(MainActivity.this, WaterMarkActivity.class);
                        startActivity(intent);
                        break;

                    case 17:
                        intent.setClass(MainActivity.this, SurfaceViewActivity.class);
                        startActivity(intent);
                        break;
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

        HomeBean svgTest = new HomeBean();
        svgTest.setTitle("SVG");
        svgTest.setContent("svg测试");
        homeBeanList.add(svgTest);

        HomeBean bezier = new HomeBean();
        bezier.setTitle("贝塞尔曲线");
        bezier.setContent("贝塞尔曲线学习");
        homeBeanList.add(bezier);

        HomeBean normalTrack = new HomeBean();
        normalTrack.setTitle("普通的手势追踪");
        normalTrack.setContent("普通的手势追踪\n未加贝塞尔曲线");
        homeBeanList.add(normalTrack);

        HomeBean bezierTrack = new HomeBean();
        bezierTrack.setTitle("加上贝塞尔曲线的手势追踪");
        bezierTrack.setContent("手势追踪\n加贝塞尔曲线");
        homeBeanList.add(bezierTrack);

        HomeBean animWave = new HomeBean();
        animWave.setTitle("贝塞尔曲线实现波浪动画");
        animWave.setContent("贝塞尔曲线实现波浪动画");
        homeBeanList.add(animWave);

        HomeBean bitmapShader = new HomeBean();
        bitmapShader.setTitle("Shader");
        bitmapShader.setContent("绘图进阶Shader,BitmapShader\n实现望远镜效果");
        homeBeanList.add(bitmapShader);

        HomeBean avator = new HomeBean();
        avator.setTitle("Shader");
        avator.setContent("绘图进阶Shader,BitmapShader\n实现不规则头像");
        homeBeanList.add(avator);

        HomeBean shape = new HomeBean();
        shape.setTitle("shape");
        shape.setContent("GradientDrawable");
        homeBeanList.add(shape);

        HomeBean magnifer = new HomeBean();
        magnifer.setTitle("ShapeDrawable");
        magnifer.setContent("ShapeDrawable实现放大镜");
        homeBeanList.add(magnifer);

        HomeBean waterMark = new HomeBean();
        waterMark.setTitle("Bitmap");
        waterMark.setContent("Bitmap实现水印");
        homeBeanList.add(waterMark);

        HomeBean surfaceView = new HomeBean();
        surfaceView.setTitle("SurfaceView");
        surfaceView.setContent("SurfaceView实现动态Bitmap");
        homeBeanList.add(surfaceView);
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
