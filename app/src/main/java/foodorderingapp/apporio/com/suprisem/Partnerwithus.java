package foodorderingapp.apporio.com.suprisem;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import foodorderingapp.apporio.com.suprisem.adapter.PartnerwithusAdapter;
import foodorderingapp.apporio.com.suprisem.adapter.Productlistadapter;

public class Partnerwithus extends Activity {
    GridView lv;

    ImageView back;
    FrameLayout cartll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partnerwithus);
        lv= (GridView)findViewById(R.id.listView22);
        back= (ImageView)findViewById(R.id.imageView2);
        cartll = (FrameLayout) findViewById(R.id.cartll);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       lv.setAdapter(new PartnerwithusAdapter(Partnerwithus.this));
        cartll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Partnerwithus.this, CartActivity.class);
                startActivity(in);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Partnerwithus.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Partnerwithus.this.getResources().getColor(R.color.statusbarcolor));
        } else {
            Window window = Partnerwithus.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
