package emdaijia.driver.daijia.easymin.com.collapsingtoollayoutexp;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.bar_1)
    RelativeLayout bar_1;

    @Bind(R.id.bar_2)
    RelativeLayout bar_2;

    @Bind(R.id.app_bar)
    AppBarLayout barLayout;

    @Bind(R.id.bar_2_img1)
    ImageView bar2Img1;

    @OnClick(R.id.bar_2_img1)
    void bar2Img1() {
        Toast.makeText(this, "bar2Img1 clicked", Toast.LENGTH_SHORT).show();
    }

    @Bind(R.id.bar_2_img2)
    ImageView bar2Img2;

    @OnClick(R.id.bar_2_img2)
    void bar2Img2() {
        Toast.makeText(this, "bar2Img2 clicked", Toast.LENGTH_SHORT).show();
    }

    @Bind(R.id.bar_2_img3)
    ImageView bar2Img3;

    @OnClick(R.id.bar_2_img3)
    void bar2Img3() {
        Toast.makeText(this, "bar2Img3 clicked", Toast.LENGTH_SHORT).show();
    }

    @Bind(R.id.bar_2_img4)
    ImageView bar2Img4;

    @OnClick(R.id.bar_2_img4)
    void bar2Img4() {
        Toast.makeText(this, "bar2Img4 clicked", Toast.LENGTH_SHORT).show();
    }

    @Bind(R.id.bar_1_img1)
    ImageView bar1Img1;

    @OnClick(R.id.bar_1_img1)
    void bar1Img1() {
        Toast.makeText(this, "bar1Img1 clicked", Toast.LENGTH_SHORT).show();
    }

    @Bind(R.id.bar_1_img2)
    ImageView bar1Img2;

    @OnClick(R.id.bar_1_img2)
    void bar1Img2() {
        Toast.makeText(this, "bar1Img2 clicked", Toast.LENGTH_SHORT).show();
    }

    @Bind(R.id.bar_1_img3)
    ImageView bar1Img3;

    @OnClick(R.id.bar_1_img3)
    void bar1Img3() {
        Toast.makeText(this, "bar1Img3 clicked", Toast.LENGTH_SHORT).show();
    }

    @Bind(R.id.bar_1_txt1)
    TextView bar1Txt1;

    @OnClick(R.id.bar_1_txt1)
    void bar1Txt1() {
        Toast.makeText(this, "bar1Txt1 clicked", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.duolaameng);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onGenerated(Palette palette) {
                //这里我们获取的是图片充满活力的黑的色调
                List<Palette.Swatch> swatchs = palette.getSwatches();

                Palette.Swatch swatch = null;
                if (null != swatchs && swatchs.size() != 0) {
                    swatch = swatchs.get(0);
                }

                //设置Toolbar颜色
                if (swatch == null) {
                    return;
                }

                barLayout.setBackgroundColor(swatch.getRgb());

                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(swatch.getRgb()));

                //设置系统状态栏颜色
                getWindow().setStatusBarColor(swatch.getRgb());
            }
        });

        barLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { //barLayout偏移量的监听
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("verticalOffset", "verticalOffset--->" + verticalOffset);
                Log.e("totalScrollRange", "appBarLayout.getTotalScrollRange()--->" + appBarLayout.getTotalScrollRange());
                if (verticalOffset == 0) {
                    //张开
                    bar_1.setVisibility(View.VISIBLE);
                    bar_2.setVisibility(View.GONE);
                    setToolbar1Alpha(255);
                    toolbar.setAlpha(1);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    //收缩
                    bar_1.setVisibility(View.GONE);
                    bar_2.setVisibility(View.VISIBLE);
                    setToolbar2Alpha(255);
                    toolbar.setAlpha(1);
                } else {
                    Log.e("Math", "Math.abs(verticalOffset)--->" + Math.abs(verticalOffset));
                    double percent = (double) Math.abs(verticalOffset) / (double) appBarLayout.getTotalScrollRange();

                    if (percent > 0.5 && percent <= 1) {
                        int alpha = (int) ((percent - 0.5) * 2 * 255);
                        bar_1.setVisibility(View.GONE);
                        bar_2.setVisibility(View.VISIBLE);
                        setToolbar2Alpha(Math.abs(alpha));

                        toolbar.setAlpha((float) ((percent - 0.5) * 2));

                    } else {
                        int alpha = (int) ((0.5 - percent) * 2 * 255);
                        bar_1.setVisibility(View.VISIBLE);
                        bar_2.setVisibility(View.GONE);
                        setToolbar1Alpha(alpha);
                        toolbar.setAlpha((float) ((0.5 - percent) * 2));
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //设置展开时各控件的透明度
    public void setToolbar1Alpha(int alpha) {
        bar1Img1.getDrawable().setAlpha(alpha);
        bar1Txt1.setTextColor(Color.argb(alpha, 255, 255, 255));
        bar1Img2.getDrawable().setAlpha(alpha);
        bar1Img3.getDrawable().setAlpha(alpha);
    }

    //设置闭合时各控件的透明度
    public void setToolbar2Alpha(int alpha) {
        bar2Img1.getDrawable().setAlpha(alpha);
        bar2Img2.getDrawable().setAlpha(alpha);
        bar2Img3.getDrawable().setAlpha(alpha);
        bar2Img4.getDrawable().setAlpha(alpha);
    }

}
