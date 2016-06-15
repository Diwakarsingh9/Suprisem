package foodorderingapp.apporio.com.suprisem;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.Parsing.parsingforcategories;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforproductlist;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_pro_options;
import foodorderingapp.apporio.com.suprisem.adapter.Productlistadapter;
import views.ProgressWheel;

public class Product_list_Activity extends Activity {
   public static GridView lv;
    public static ProgressWheel pb;
    Productlistadapter asp;
    ImageView back;
    FrameLayout cartll;
    public static ArrayList<String> prod_img = new ArrayList<String>();
    public static ArrayList<Innermost_all_pro_options> pro_options = new ArrayList<>();
    public  static Product_list_Activity pdc;
    String intentword,categoryid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_);
        lv= (GridView)findViewById(R.id.listView22);
        pb= (ProgressWheel)findViewById(R.id.pb11);
        back= (ImageView)findViewById(R.id.imageView2);
        cartll = (FrameLayout) findViewById(R.id.cartll);
        pdc = Product_list_Activity.this;
        categoryid = ""+getIntent().getStringExtra("category_id");
        parsingforproductlist.parsing(Product_list_Activity.this,categoryid);
        //asp = new Productlistadapter(Product_list_Activity.this);
       // lv.setAdapter(asp);
        intentword = getIntent().getStringExtra("act");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prod_img.clear();
                pro_options.clear();
                StoreCommonValues.optionpro.clear();
                for(int j=0;j< parsingforproductlist.pro_imagess.get(position).size();j++){
                    prod_img.add(parsingforproductlist.pro_imagess.get(position).get(j).image);
                }
                for(int j=0;j< parsingforproductlist.pro_options.get(position).size();j++){
                    pro_options.add(parsingforproductlist.pro_options.get(position).get(j));
                }
                Intent i = new Intent(Product_list_Activity.this, InnerPrductActivity.class);
                i.putExtra("act",""+intentword);
                i.putExtra("product_id", parsingforproductlist.pro_id.get(position));
                i.putExtra("product_price", parsingforproductlist.pro_price.get(position));
                i.putExtra("product_descp", parsingforproductlist.pro_desc.get(position));
                i.putExtra("product_name", parsingforproductlist.pro_name.get(position));
                i.putStringArrayListExtra("pro_imagess", prod_img);
                StoreCommonValues.optionpro = pro_options;
                startActivity(i);
            }
        });
        cartll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Product_list_Activity.this, CartActivity.class);
                startActivity(in);
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Product_list_Activity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Product_list_Activity.this.getResources().getColor(R.color.statusbarcolor));
        } else {
            Window window = Product_list_Activity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
