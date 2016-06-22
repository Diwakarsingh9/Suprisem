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
import android.widget.TextView;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.Database.DBManager;
import foodorderingapp.apporio.com.suprisem.adapter.Productlistadapter;
import foodorderingapp.apporio.com.suprisem.adapter.Sub_catlistadapter;

public class Sub_categoryActivity extends Activity {
    GridView lv;
    Sub_catlistadapter asp;
    ImageView back;
    FrameLayout cartll;
    String intentword;
    TextView totlitem;
    static DBManager dbm;
    public static ArrayList<String> s_catname = new ArrayList<String>();
    public static ArrayList<String> s_catid = new ArrayList<String>();
    public static ArrayList<String> s_catimg = new ArrayList<String>();
    public static ArrayList<String> s_catstatus = new ArrayList<String>();
    public static  Sub_categoryActivity sdc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        lv= (GridView)findViewById(R.id.listView22);
        back= (ImageView)findViewById(R.id.imageView2);
        cartll = (FrameLayout) findViewById(R.id.cartll);
        dbm = new DBManager(Sub_categoryActivity.this);
        totlitem = (TextView) findViewById(R.id.total_item);
        sdc = Sub_categoryActivity.this;

        s_catid = getIntent().getStringArrayListExtra("sub_category_id");
        s_catname = getIntent().getStringArrayListExtra("sub_category_name");
        s_catimg = getIntent().getStringArrayListExtra("sub_category_img");
        s_catstatus = getIntent().getStringArrayListExtra("sub_category_status");

         intentword = ""+getIntent().getStringExtra("act");
        asp = new Sub_catlistadapter(Sub_categoryActivity.this,s_catid,s_catname,s_catimg,s_catstatus);
        lv.setAdapter(asp);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(Sub_categoryActivity.this, Product_list_Activity.class);
                in.putExtra("act",""+intentword);
                in.putExtra("category_id",""+s_catid.get(position));

                startActivity(in);
            }
        });
        cartll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Sub_categoryActivity.this, CartActivity.class);
                startActivity(in);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        totlitem.setText("" + dbm.getFullTable().size());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Sub_categoryActivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Sub_categoryActivity.this.getResources().getColor(R.color.statusbarcolor));
        } else {
            Window window = Sub_categoryActivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
