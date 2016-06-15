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
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.adapter.FaqAdapter;

public class Faq_Activity extends Activity {
    ListView lv;
    ImageView back;
    FrameLayout cartll;
    ArrayList<String> Questions = new ArrayList<>();
    ArrayList<String> Answers = new ArrayList<>();
    FaqAdapter fap ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_);
        lv= (ListView)findViewById(R.id.listView);
        back= (ImageView)findViewById(R.id.imageView2);
        cartll = (FrameLayout) findViewById(R.id.cartll);
        Questions.add("I see different prices for same item. Why?");
        Questions.add("What is 'Surprisem Advantage'?");
        Answers.add("A product could be listed under different prices. There could be sellers offering you the same product but at a different price. That is the nature of the Surprisem marketplace, where different sellers compete for your order.");
       Answers.add("When you see the 'Surprisem Advantage' badge, you know you can buy the product from a Seller with complete confidence.\n" +
               "Products marked with 'Surprisem Advantage' are sold by/sourced from a Seller but are quality checked, stocked, packed and shipped by Surprisem.\n" +
               "Products sold by sellers with Surprisem Advantage are also backed by Surprisem's 30 Day Replacement Guarantee and you may also avail the Same Day/In-a-Day Guarantee expedited delivery options. All while you are backed by Surprisem's 24x7 customer service.");
        lv.setAdapter(new FaqAdapter(Faq_Activity.this, Questions, Answers));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        cartll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Faq_Activity.this, CartActivity.class);
                startActivity(in);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Faq_Activity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Faq_Activity.this.getResources().getColor(R.color.statusbarcolor));
        } else {
            Window window = Faq_Activity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
