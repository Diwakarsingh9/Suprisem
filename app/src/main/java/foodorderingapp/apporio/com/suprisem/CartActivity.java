package foodorderingapp.apporio.com.suprisem;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import foodorderingapp.apporio.com.suprisem.Database.CartTable;
import foodorderingapp.apporio.com.suprisem.Database.DBManager;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforCart;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforcountry;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforlogin;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforsignup;
import foodorderingapp.apporio.com.suprisem.adapter.Cartadapter;
import io.realm.RealmResults;
import views.ProgressWheel;

public class CartActivity extends Activity {
    ImageView back,close;
    public static ListView lv;
    public static TextView checkout,totalprice;
    public static CartActivity cartact;
    public static Spinner sp22;
   public static DBManager dbm;

    public static List<CartTable> ct;
    boolean previouslyStarted;

    public static Dialog dialog,dialog2;
    public static ProgressWheel pb,pb22,pb23;
    public static RealmResults<CartTable> ct4;
    Spinner statesp;
    static Spinner countrysp;
    static String countryidstring = "";
    static String stateidstring="";
    public static  TextView totlitem;
    public static ArrayList<String> stateid = new ArrayList<String>();
    public static ArrayList<String> statenames = new ArrayList<String>();
    public static ArrayList<String> countryid = new ArrayList<String>();
    public static ArrayList<String> countryname = new ArrayList<String>();
    public static ArrayList<JSONArray> statenamesarray = new ArrayList<>();
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
    String spinnermob = "+91";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        back= (ImageView)findViewById(R.id.imageView2);
        checkout= (TextView)findViewById(R.id.cok);
        totalprice= (TextView)findViewById(R.id.totalprice);
        pb= (ProgressWheel)findViewById(R.id.pb123);
        lv= (ListView)findViewById(R.id.listviewcart);
        totlitem = (TextView) findViewById(R.id.total_item);
        dbm = new DBManager(CartActivity.this);
        Log.e("details",""+getCartdetails());

        parsingforCart.parsing(CartActivity.this, "" + getCartdetails());
        totlitem.setText("" + dbm.getFullTable().size());
       // lv.setAdapter(new Cartadapter(CartActivity.this));
        cartact = CartActivity.this;
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        previouslyStarted = prefs.getBoolean("pref_previously_started", false);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbm.getFullTable().size() == 0) {
                    Toast.makeText(CartActivity.this, "Please add item in Cart !!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!previouslyStarted) {
                        showdialogforsignin();

                    } else {
                        Intent in = new Intent(CartActivity.this, Payment_and_deliveryActivity.class);
                        in.putExtra("Customer_id", "" + prefs.getString("Customerid", "null"));
                        in.putExtra("Totalprice", "" + totalprice.getText().toString().trim());
                        in.putExtra("Cart_details", "" + prefs.getString("" + getCartdetails(), "null"));
                        startActivity(in);

                    }

                }
            }
        });

    }

    public  static void countryidstateid(ArrayList<String> country_id, ArrayList<String> country_name, ArrayList<JSONArray> statenames){

        countryid =country_id;
        countryname = country_name;
        statenamesarray = statenames;

        try {
            countryidstring = countryid.get(0);
           stateidstring = statenamesarray.get(0).getJSONObject(0).getString("state_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        countrysp.setAdapter(new ArrayAdapter(cartact,R.layout.itemlayoutforspinner,
                R.id.itemspinner,countryname));
    }
    public static JSONObject getCartdetails() {
        JSONObject obj = new JSONObject();
        ct4 = dbm.getFullTable();
        JSONArray jsonArray2 = new JSONArray();
        try {
            obj.put("products",jsonArray2);
            obj.put("language_id", "1");



            for (int i = 0; i < ct4.size(); i++) {
                JSONObject jinnerobject2 = new JSONObject();
                Log.e("optionsdatabase", ct4.size() + " " + ct4.get(i).getOption().toString() + "   " + ct4.get(i).getOption().toString());

                jinnerobject2.put("product_id", ct4.get(i).getproductid());
                jinnerobject2.put("quantity",ct4.get(i).getQuantity());
                if(ct4.get(i).getOption().equals("")){
                    jinnerobject2.put("option", new JSONObject());

                }
                else {
                    jinnerobject2.put("option", new JSONObject("{\"" + (ct4.get(i).getOption()) + "\"}"));
                }

                jsonArray2.put(jinnerobject2);

            }


            Log.e("JSONARRAYrest ", jsonArray2.toString());


        } catch (JSONException e) {
            Log.e("errrr",""+e);
            e.printStackTrace();
        }
        return obj;
    }

    public  void showdialogforsignin() {

      dialog = new Dialog(CartActivity.this,android.R.style.Theme_Translucent);

        //  dialog.getWindow().setStatusBarColor(Loginscreenactivity.this.getResources().getColor(R.color.example_color));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.activity_login_signupactivity);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        close = (ImageView)dialog.findViewById(R.id.closed);
        final EditText username = (EditText)dialog.findViewById(R.id.email);
        final EditText passowrd = (EditText)dialog.findViewById(R.id.pass);
        TextView signup = (TextView)dialog.findViewById(R.id.signup);
        TextView loginn = (TextView)dialog.findViewById(R.id.Login);
        TextView forgotpw = (TextView)dialog.findViewById(R.id.frgt);
        pb22= (ProgressWheel)dialog.findViewById(R.id.pbd);
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
                if(username.getText().toString().trim().equals("")||passowrd.getText().toString().trim().equals("")){
                    Toast.makeText(CartActivity.this, "Required fields missing !!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    parsingforlogin.parsing(CartActivity.this, username.getText().toString().trim(), passowrd.getText().toString().trim());
                }
            }
        });
        forgotpw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Toast.makeText(CartActivity.this, "We have sent instructions to reset password.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
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
        countryid.clear();
        countryname.clear();
        statenamesarray.clear();
      dialog2 = new Dialog(CartActivity.this,android.R.style.Theme_Translucent);

        //  dialog.getWindow().setStatusBarColor(Loginscreenactivity.this.getResources().getColor(R.color.example_color));

        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog2.getWindow();
        dialog2.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.setContentView(R.layout.dialogforsignup);

        dialog2.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        close = (ImageView)dialog2.findViewById(R.id.closed);
        sp22 = (Spinner)dialog2.findViewById(R.id.spinner);
        final EditText firstname2 = (EditText)dialog2.findViewById(R.id.firstname22);
        final EditText lastname = (EditText)dialog2.findViewById(R.id.lastname);
        final EditText mobile = (EditText)dialog2.findViewById(R.id.mob);
        final EditText passowrd = (EditText)dialog2.findViewById(R.id.password2);
        final EditText confirmpass = (EditText)dialog2.findViewById(R.id.confirmpassword2);
        final EditText username = (EditText)dialog2.findViewById(R.id.email);
        final EditText address = (EditText)dialog2.findViewById(R.id.address);
        final EditText city = (EditText)dialog2.findViewById(R.id.city);
         statesp = (Spinner)dialog2.findViewById(R.id.state);
        countrysp = (Spinner)dialog2.findViewById(R.id.country);
        final EditText pincode = (EditText)dialog2.findViewById(R.id.pincode2);
        pb23= (ProgressWheel)dialog2.findViewById(R.id.pbd);
        TextView login = (TextView)dialog2.findViewById(R.id.login);
        TextView signup = (TextView)dialog2.findViewById(R.id.signup);
        parsingforcountry.parsing(CartActivity.this,0);


        countrysp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                statenames.get(0).getJSONObject(0).getString("name");
                stateid.clear();
                statenames.clear();
                countryidstring = countryid.get(position);
                for (int j = 0; j < statenamesarray.get(position).length(); j++) {
                    try {
                        stateid.add(statenamesarray.get(position).getJSONObject(j).getString("state_id"));
                        statenames.add(statenamesarray.get(position).getJSONObject(j).getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                statesp.setAdapter(new ArrayAdapter(cartact, R.layout.itemlayoutforspinner,
                        R.id.itemspinner, statenames));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        statesp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stateidstring = stateid.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                showdialogforsignin();
                dialog2.dismiss();
            }
        });


        ArrayAdapter adp = new ArrayAdapter(getApplicationContext(),R.layout.itemlayoutformobile,R.id.mobcode,m_Codes);
        sp22.setAdapter(adp);
        sp22.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnermob = "+"+parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnermob = "+91";
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (firstname2.getText().toString().trim().equals("") ||
                        mobile.getText().toString().trim().equals("") ||
                        passowrd.getText().toString().trim().equals("") ||
                        confirmpass.getText().toString().trim().equals("") ||
                        username.getText().toString().trim().equals("") ||
                        address.getText().toString().trim().equals("") ||
                        city.getText().toString().trim().equals("") ||
                        statesp.getSelectedItem().toString().trim().equals("") ||
                        countrysp.getSelectedItem().toString().trim().equals("") ||
                        pincode.getText().toString().trim().equals("")) {
                    Toast.makeText(CartActivity.this, "Required fields missing !!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!passowrd.getText().toString().trim().
                            equals(confirmpass.getText().toString().trim())) {
                        Toast.makeText(CartActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    } else {
                        parsingforsignup.parsing(CartActivity.this, firstname2.getText().toString().trim(),
                                lastname.getText().toString().trim(), spinnermob + mobile.getText().toString().trim(),
                                username.getText().toString().trim(), passowrd.getText().toString().trim(),
                                confirmpass.getText().toString().trim(), address.getText().toString().trim(),
                                city.getText().toString().trim(), stateidstring,
                                countryidstring, pincode.getText().toString().trim());
                    }
                }

            }
        });

        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog2.dismiss();
            }
        });

        dialog2.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        totlitem.setText(""+dbm.getFullTable().size());
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
