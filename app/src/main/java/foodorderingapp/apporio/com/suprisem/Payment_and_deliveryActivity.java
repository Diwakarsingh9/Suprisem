package foodorderingapp.apporio.com.suprisem;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import foodorderingapp.apporio.com.suprisem.Parsing.parsingforchangeaddress;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingfordeliveryaddress;
import views.ProgressWheel;

public class Payment_and_deliveryActivity extends Activity {

    String []cb={"Net Banking","Debit Card","Credit Card","Cash On Delivery"};
    RadioGroup rdg;
    Boolean []checkedss;
    ImageView closed;
    TextView saved,placeorder;
    EditText name, address1, lastname, cityss, state, pincode, country;
    LinearLayout llforradiobuttonadding;
    String radiobutton="";
    public  static Dialog dialog;
    public  static ProgressWheel pb111,pb232;
    public  static TextView changeaddress,addresssssss;
    public  static String Customer_id = "";
    public  static String Address_id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_and_delivery);
        llforradiobuttonadding = (LinearLayout)findViewById(R.id.llforrb);
        changeaddress = (TextView)findViewById(R.id.chng);
        addresssssss = (TextView)findViewById(R.id.addresssss);
        placeorder = (TextView)findViewById(R.id.porder);
        pb111 = (ProgressWheel)findViewById(R.id.pb111);

        Customer_id = getIntent().getStringExtra("Customer_id");
        parsingfordeliveryaddress.parsing(Payment_and_deliveryActivity.this, Customer_id);
        checkedss=new Boolean[cb.length];
        for(int i=0;i<cb.length;i++){
              checkedss[i]=false;
            llforradiobuttonadding.addView(addviewradiobutton(R.layout.layoutforcb,cb[i],i,checkedss[i]));
        }
        changeaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showchangeaddressdialog();
            }
        });
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartActivity.cartact.finish();
                finish();
                Toast.makeText(Payment_and_deliveryActivity.this, "Your order has been placed !!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private View addviewradiobutton(int layout_name, String s1, int i, boolean checkedss2) {

        LayoutInflater layoutInflater =
                (LayoutInflater)Payment_and_deliveryActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);
        final RadioButton cb2 = (RadioButton) addView.findViewById(R.id.rdbtn);


        cb2.setText("" + s1);

        cb2.setChecked(checkedss2);
        cb2.setTag(i);
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int pos = (int) buttonView.getTag();
                radiobutton = cb2.getText().toString();
                for (int i = 0; i < checkedss.length; i++) {
                    checkedss[i] = false;
                }
                checkedss[pos] = isChecked;
                //Toast.makeText(Add_to_CartActivity.this, ""+isChecked, Toast.LENGTH_SHORT).show();
                llforradiobuttonadding.removeAllViews();
                for (int i = 0; i < cb.length; i++) {

                    llforradiobuttonadding.addView(addviewradiobutton(R.layout.layoutforcb, cb[i], i, checkedss[i]));
                }

            }
        });


        return addView ;
    }


    public  void showchangeaddressdialog() {

     dialog = new Dialog(Payment_and_deliveryActivity.this,android.R.style.Theme_Translucent);

        //  dialog.getWindow().setStatusBarColor(Loginscreenactivity.this.getResources().getColor(R.color.example_color));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforaddresschange);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        closed = (ImageView)dialog.findViewById(R.id.closed);
          name = (EditText)dialog.findViewById(R.id.name);
          address1 = (EditText)dialog.findViewById(R.id.address1);
            lastname = (EditText)dialog.findViewById(R.id.lastname);
         pincode = (EditText)dialog.findViewById(R.id.pincode);
         cityss = (EditText)dialog.findViewById(R.id.city);
         state = (EditText)dialog.findViewById(R.id.State);
         country = (EditText)dialog.findViewById(R.id.country11);
         saved = (TextView)dialog.findViewById(R.id.savedd);
        pb232 = (ProgressWheel)dialog.findViewById(R.id.pb111);


        saved.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (v != null) {
                    InputMethodManager imm = (InputMethodManager)Payment_and_deliveryActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                if(address1.getText().toString().trim().equals("")||lastname.getText().toString().trim().equals("")||
                        cityss.getText().toString().trim().equals("")||state.getText().toString().trim().equals("")||
                        pincode.getText().toString().trim().equals("") ||country.getText().toString().trim().equals("") )
                {
                    Toast.makeText(Payment_and_deliveryActivity.this, "Required fields missing !!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    parsingforchangeaddress.parsing(Payment_and_deliveryActivity.this,Address_id,
                            Customer_id,name.getText().toString().trim(),lastname.getText().toString(),"null",address1.getText().toString().trim(),
                            cityss.getText().toString().trim(),pincode.getText().toString().trim(),country.getText().toString(),
                            state.getText().toString().trim());
//                    addresssssss.setText("" + address1.getText().toString()
//                            + ", " + address2.getText().toString() +
//                            ", " + cityss.getText().toString() + ", " +
//                            state.getText().toString() + ", " +
//                            pincode.getText().toString() + "\nMobile No - " +
//                            mobile.getText().toString());
                  //  dialog.dismiss();
                }
            }
        });

        closed.setOnClickListener(new View.OnClickListener() {

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
            Window window = Payment_and_deliveryActivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Payment_and_deliveryActivity.this.getResources().getColor(R.color.statusbarcolor));
        } else {
            Window window = Payment_and_deliveryActivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
