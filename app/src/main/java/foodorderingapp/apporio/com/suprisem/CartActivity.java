package foodorderingapp.apporio.com.suprisem;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import foodorderingapp.apporio.com.suprisem.adapter.Cartadapter;

public class CartActivity extends Activity {
    ImageView back,close;
    ListView lv;
    TextView checkout;
    public static CartActivity cartact;
    public static Spinner sp22;
    private static final String[] m_Codes = { "91","376", "971",
            "93", "355", "374", "599", "244", "672", "54", "43", "61", "297",
            "994", "387", "880", "32", "226",
            "359", "973", "257", "229", "590", "673", "591", "55", "975", "267", "375", "501",
            "1", "61", "243", "236", "242", "41", "225", "682", "56", "237", "86", "57",
            "506", "53", "238", "61", "357", "420",
            "49", "253", "45", "213", "593", "372", "20", "291", "34", "251", "358", "679",
            "500", "691", "298", "33", "241", "44", "995", "233", "350", "299", "220", "224", "240", "30", "502", "245", "592", "852", "504", "385", "509", "36", "62", "353", "972", "44", "964",
            "98", "39", "962", "81", "254", "996", "855", "686", "269", "850", "82", "965", "7", "856", "961", "423", "94", "231", "266", "370", "352", "371", "218", "212", "377", "373", "382", "261", "692", "389", "223", "95", "976", "853", "222", "356",
            "230", "960", "265", "52", "60", "258", "264", "687", "227", "234", "505", "31", "47", "977", "674", "683", "64", "968", "507", "51", "689", "675", "63", "92", "48", "508", "870", "1", "351", "680", "595", "974", "40", "381", "7", "250", "966", "677", "248", "249", "46", "65", "290", "386", "421", "232", "378", "221", "252", "597", "239", "503", "963", "268", "235", "228", "66", "992", "690", "670", "993", "216", "676", "90", "688", "886", "255", "380", "256", "1", "598", "998", "39", "58", "84", "678", "681", "685", "967", "262", "27", "260", "263"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        back= (ImageView)findViewById(R.id.imageView2);
        checkout= (TextView)findViewById(R.id.cok);
        lv= (ListView)findViewById(R.id.listviewcart);
        lv.setAdapter(new Cartadapter(CartActivity.this));
        cartact = CartActivity.this;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showdialogforsignin();
            }
        });





    }
    public  void showdialogforsignin() {

        final Dialog dialog = new Dialog(CartActivity.this,android.R.style.Theme_Translucent);

        //  dialog.getWindow().setStatusBarColor(Loginscreenactivity.this.getResources().getColor(R.color.example_color));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.activity_login_signupactivity);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        close = (ImageView)dialog.findViewById(R.id.closed);
        TextView signup = (TextView)dialog.findViewById(R.id.signup);
        TextView loginn = (TextView)dialog.findViewById(R.id.Login);
        TextView forgotpw = (TextView)dialog.findViewById(R.id.frgt);
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showdialogforsignup();
                dialog.dismiss();
            }
        });
        loginn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent in = new Intent(CartActivity.this,Payment_and_deliveryActivity.class);
                startActivity(in);
                dialog.dismiss();
            }
        });
        forgotpw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Toast.makeText(CartActivity.this, "We have sent instructions to reset password.", Toast.LENGTH_SHORT).show();
            }
        });


//




        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public  void showdialogforsignup() {

        final Dialog dialog = new Dialog(CartActivity.this,android.R.style.Theme_Translucent);

        //  dialog.getWindow().setStatusBarColor(Loginscreenactivity.this.getResources().getColor(R.color.example_color));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforsignup);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        close = (ImageView)dialog.findViewById(R.id.closed);
        sp22 = (Spinner)dialog.findViewById(R.id.spinner);

        TextView login = (TextView)dialog.findViewById(R.id.login);
        TextView signup = (TextView)dialog.findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showdialogforsignin();
                dialog.dismiss();
            }
        });


        ArrayAdapter adp = new ArrayAdapter(getApplicationContext(),R.layout.itemlayoutformobile,R.id.mobcode,m_Codes);
        sp22.setAdapter(adp);


        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent in = new Intent(CartActivity.this, Payment_and_deliveryActivity.class);
                startActivity(in);
                dialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = CartActivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(CartActivity.this.getResources().getColor(R.color.statusbarcolor));
        } else {
            Window window = CartActivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
