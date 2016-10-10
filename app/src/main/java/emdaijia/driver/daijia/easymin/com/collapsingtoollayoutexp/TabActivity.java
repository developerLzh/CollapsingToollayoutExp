package emdaijia.driver.daijia.easymin.com.collapsingtoollayoutexp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/10.
 */

public class TabActivity extends AppCompatActivity {

    @Bind(R.id.tab_bar_layout)
    AppBarLayout barLayout;

//    @Bind(R.id.tab_coll_layout)
//    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.tab_tool_bar)
    Toolbar toolbar;

//    @Bind(R.id.tab_tab_layout)
//    TabLayout tabLayout;

    @Bind(R.id.tab_view_pager)
    ViewPager viewPager;

//    @Bind(R.id.tab_coll_img)
//    ImageView collImg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarTranslucent();
        setContentView(R.layout.tab_collapsable_layout);
        ButterKnife.bind(this);

        //        获取需要被模糊的原图bitmap
//        Resources res = getResources();
//        Bitmap scaledBitmap = BitmapFactory.decodeResource(res, R.drawable.secpic);
//
//        //        scaledBitmap为目标图像，10是缩放的倍数（越大模糊效果越高）
//        Bitmap blurBitmap = FastBlurUtil.toBlur(scaledBitmap, 5);
//        collImg.setScaleType(ImageView.ScaleType.FIT_XY);
//        collImg.setImageBitmap(blurBitmap);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        setupViewPager(viewPager);

        toolbar.setAlpha(0.0f);

        barLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                double percent = (double) Math.abs(verticalOffset) / (double) appBarLayout.getTotalScrollRange();
                Log.e("percent","--->"+percent);
                toolbar.setAlpha((float) percent);
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TabFragment(), "Tab 1");
        adapter.addFrag(new TabFragment(), "Tab 2");
        adapter.addFrag(new TabFragment(), "Tab 3");

        viewPager.setAdapter(adapter);

//        tabLayout.setupWithViewPager(viewPager);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    protected void setStatusBarTranslucent() {
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);
        }
    }

}
