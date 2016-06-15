package foodorderingapp.apporio.com.suprisem;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.adapter.MyAdapter1;
import foodorderingapp.apporio.com.suprisem.adapter.MyAdapter11;

public class Photoinneractivity extends FragmentActivity {
    ArrayList<String> arrayc;
    ViewPager pager;
    ImageView back;
    ImageView img,delete;
    CirclePageIndicator titleIndicator;
    public  static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoinneractivity);
        back= (ImageView)findViewById(R.id.close);
        Bundle bundle2= getIntent().getExtras();
        int position1= bundle2.getInt("position", 0);

        pager = (ViewPager) findViewById(R.id.pager);
        arrayc = new ArrayList<String>();
       arrayc = getIntent().getStringArrayListExtra("list");


        pager.setAdapter(new MyAdapter11(getSupportFragmentManager(), position1, arrayc));
        titleIndicator= (CirclePageIndicator)findViewById(R.id.titles);
        pager.setCurrentItem(position1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Photoinneractivity.this.finish();
            }
        });



        titleIndicator.setViewPager(pager);

        titleIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Photoinneractivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Photoinneractivity.this.getResources().getColor(R.color.statusbarcolor));
        } else {
            Window window = Photoinneractivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
