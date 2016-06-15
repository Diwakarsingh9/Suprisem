package foodorderingapp.apporio.com.suprisem;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.ErrorListener;

public class SplashActivity extends Activity {
        ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo= (ImageView)findViewById(R.id.imageView);



        YoYo.with(Techniques.FadeInRight)
                .duration(2000)
                .playOn(findViewById(R.id.imageView));

        Handler handler1 = new Handler();

        handler1.postDelayed(new Runnable() {

            @Override
            public void run()

            {


                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();


            }
        }, 3000);
    }


}
