package foodorderingapp.apporio.com.suprisem;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import foodorderingapp.apporio.com.suprisem.Database.CartTable;
import foodorderingapp.apporio.com.suprisem.Database.DBManager;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforchangeaddress;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforconfirmorder;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforcountry;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingfordeliveryaddress;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforpaymentmethod;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforshippingmethod;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_quotes;
import io.realm.RealmResults;
import views.ProgressWheel;

public class Payment_and_deliveryActivity extends Activity {

    //String []cb={"Net Banking","Debit Card","Credit Card","Cash On Delivery"};
    RadioGroup rdg;
    public static String titleshipping ="",codeshipping="",costshipping="",sortordershipping="",taxclassidshipping="";

    public static String titlepayment ="",codepayment="",sortorderpayment="",termspayment="";
   public static Boolean []checkedss = null;
    public static Boolean []checkedss2 = null;
    ImageView closed;
    public static  Context c;
    TextView saved,placeorder,price;
    public static Dialog dialog2;
    public static Payment_and_deliveryActivity pdp;
    EditText name, address1, lastname, cityss, state, pincode, country;
    public  static LinearLayout llforradiobuttonadding;
    public  static LinearLayout llforradiobuttonadding2;
    public  static String radiobutton="";
    public  static String radiobutton2="";
    public  static Dialog dialog;
    public  static ProgressWheel pb111,pb232;
    public  static TextView changeaddress,addresssssss;
    public  static String Customer_id = "";
    public  static String Address_id = "";
    public static DBManager dbm;
    LinearLayout backll;
    Spinner statesp;
    static Spinner countrysp;
    static String countryidstring = "";
    static String stateidstring="";
    public static ArrayList<String> stateid = new ArrayList<String>();
    public static ArrayList<String> statenames = new ArrayList<String>();
    public static ArrayList<String> countryid = new ArrayList<String>();
    public static ArrayList<String> countryname = new ArrayList<String>();
    public static ArrayList<JSONArray> statenamesarray = new ArrayList<>();
    public static SharedPreferences prefs22;
    public static RealmResults<CartTable> ct4;
    public static RealmResults<CartTable> ct42;
    static ArrayList<String> title_name2 = new ArrayList<>();
    static ArrayList<String> title_name22 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_and_delivery);
        llforradiobuttonadding = (LinearLayout)findViewById(R.id.llforrb);
        llforradiobuttonadding2 = (LinearLayout)findViewById(R.id.llforshp);
        backll = (LinearLayout)findViewById(R.id.backll);
        changeaddress = (TextView)findViewById(R.id.chng);
        addresssssss = (TextView)findViewById(R.id.addresssss);
        price = (TextView)findViewById(R.id.total);
        placeorder = (TextView)findViewById(R.id.porder);
        pb111 = (ProgressWheel)findViewById(R.id.pb111);
        dbm = new DBManager(Payment_and_deliveryActivity.this);
        Customer_id = getIntent().getStringExtra("Customer_id");
        c=Payment_and_deliveryActivity.this;
        pdp = Payment_and_deliveryActivity.this;
        prefs22 = PreferenceManager.getDefaultSharedPreferences(c);
        parsingfordeliveryaddress.parsing(Payment_and_deliveryActivity.this, Customer_id);
        price.setText(""+getIntent().getStringExtra("Totalprice"));
        backll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        changeaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showchangeaddressdialog();
            }
        });
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // dbm.clearCartTable();
                if(!titlepayment.equals("")&&!titleshipping.equals("")) {
                    parsingforconfirmorder.parsing(Payment_and_deliveryActivity.this,
                            "" + getCartdetails22(parsingfordeliveryaddress.address_id.get(0),
                                    prefs22.getString("firstname", ""),
                                    prefs22.getString("lastname", ""),
                                    "null", parsingfordeliveryaddress.address.get(0),
                                    "", parsingfordeliveryaddress.city.get(0),
                                    parsingfordeliveryaddress.postcode.get(0),
                                    parsingfordeliveryaddress.countrys.get(0),
                                    parsingfordeliveryaddress.country_id.get(0),
                                    parsingfordeliveryaddress.zone.get(0),
                                    parsingfordeliveryaddress.zone_id.get(0),
                                    prefs22.getString("telephone", ""),
                                    prefs22.getString("email", "")));
                }
                else {
                    Toast.makeText(Payment_and_deliveryActivity.this, "Please fill full details !!!", Toast.LENGTH_SHORT).show();
                }
//                CartActivity.cartact.finish();
//                finish();
//                Toast.makeText(Payment_and_deliveryActivity.this, "Your order has been placed !!!", Toast.LENGTH_SHORT).show();
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
        countrysp.setAdapter(new ArrayAdapter(pdp,R.layout.itemlayoutforspinner,
                R.id.itemspinner,countryname));
    }
    public static View addviewradiobutton2(int layout_name, String s1, int i,
                                           final boolean checkedsssd,
                                           final  ArrayList<String> code_name2,
                                           final   ArrayList<String> sort_order2,
                                           final   ArrayList<List<Innermost_quotes>> quote2) {

        LayoutInflater layoutInflater =
                (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);
        final RadioButton cb2 = (RadioButton) addView.findViewById(R.id.rdbtn);


        cb2.setText("" + s1);

        cb2.setChecked(checkedsssd);
        cb2.setTag(i);
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int pos = (int) buttonView.getTag();
                radiobutton = cb2.getText().toString();
                codeshipping= code_name2.get(pos);
                titleshipping = cb2.getText().toString();
                sortordershipping = sort_order2.get(pos);
                if(quote2.get(pos).size()>0) {
                    costshipping = quote2.get(pos).get(0).cost;
                    taxclassidshipping = quote2.get(pos).get(0).tax_class_id;
                }
                for (int i = 0; i < checkedss2.length; i++) {
                    checkedss2[i] = false;
                }
                checkedss2[pos] = isChecked;
                //Toast.makeText(Add_to_CartActivity.this, ""+isChecked, Toast.LENGTH_SHORT).show();
                llforradiobuttonadding2.removeAllViews();
                for (int i = 0; i < checkedss2.length; i++) {

                    llforradiobuttonadding2.addView(addviewradiobutton2(R.layout.layoutforcb, title_name22.get(i), i, checkedss2[i], code_name2, sort_order2, quote2));
                }

            }
        });


        return addView ;
    }

    public static View addviewradiobutton(int layout_name, String s1, int i, final boolean checkedss2,
                                          final ArrayList<String> code_name22,
                                          final ArrayList<String> sort_order22,
                                          final ArrayList<String> terms22) {

        LayoutInflater layoutInflater =
                (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
                codepayment = code_name22.get(pos);
                titlepayment = cb2.getText().toString();
                sortorderpayment = sort_order22.get(pos);
                termspayment = terms22.get(pos);
                for (int i = 0; i < checkedss.length; i++) {
                    checkedss[i] = false;
                }
                checkedss[pos] = isChecked;
                //Toast.makeText(Add_to_CartActivity.this, ""+isChecked, Toast.LENGTH_SHORT).show();
                llforradiobuttonadding.removeAllViews();
                for (int i = 0; i < checkedss.length; i++) {

                    llforradiobuttonadding.addView(addviewradiobutton(R.layout.layoutforcb, title_name2.get(i), i, checkedss[i], code_name22, sort_order22, terms22));
                }

            }
        });


        return addView ;
    }

    public  static void showpaymentmethod(String address_id,String first_name,String last_name,
                                          String company,String addresss1,String address2,
                                          String city,String postcode,String countrya,String country_id,
                                          String zone,String zone_id,String telephone,String email){

        parsingforpaymentmethod.parsing(c, "" + getCartdetails(address_id, first_name, last_name, company, addresss1, address2,
                city, postcode, countrya, country_id, zone, zone_id, telephone, email));
    }


    public  static void showshippingmethod(String address_id,String first_name,String last_name,
                                          String company,String addresss1,String address2,
                                          String city,String postcode,String countrya,String country_id,
                                          String zone,String zone_id,String telephone,String email){

        parsingforshippingmethod.parsing(c, "" + getCartdetails(address_id, first_name, last_name, company, addresss1, address2,
                city, postcode, countrya, country_id, zone, zone_id, telephone, email));
    }


    public static JSONObject getCartdetails(String address_id,String first_name,String last_name,
                                            String company,String addresss1,String address2,
                                            String city,String postcode,String countrya,String country_id,
                                            String zone,String zone_id,String telephone,String email) {
        JSONObject obj = new JSONObject();
        JSONObject payment_address = new JSONObject();
        JSONObject shipping_address = new JSONObject();
        ct4 = dbm.getFullTable();
        JSONArray jsonArray2 = new JSONArray();
        try {
            obj.put("products",jsonArray2);
            obj.put("language_id","1");
            obj.put("payment_address",payment_address);
            obj.put("shipping_address",shipping_address);


            for (int i = 0; i < ct4.size(); i++) {
                JSONObject jinnerobject2 = new JSONObject();
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
            payment_address.put("address_id",address_id);
            payment_address.put("payment_firstname",first_name);
            payment_address.put("payment_lastname",last_name);
            payment_address.put("payment_company",company);
            payment_address.put("payment_address_1",addresss1);
            payment_address.put("payment_address_2",address2);
            payment_address.put("payment_city",city);
            payment_address.put("payment_postcode",postcode);
            payment_address.put("payment_country",countrya);
            payment_address.put("payment_country_id",country_id);
            payment_address.put("payment_zone",zone);
            payment_address.put("payment_zone_id",zone_id);
            payment_address.put("payment_telephone",telephone);
            payment_address.put("payment_email",email);

            shipping_address.put("address_id",address_id);
            shipping_address.put("payment_firstname",first_name);
            shipping_address.put("payment_lastname",last_name);
            shipping_address.put("payment_company",company);
            shipping_address.put("payment_address_1",addresss1);
            shipping_address.put("payment_address_2",address2);
            shipping_address.put("payment_city",city);
            shipping_address.put("payment_postcode",postcode);
            shipping_address.put("payment_country",countrya);
            shipping_address.put("payment_country_id",country_id);
            shipping_address.put("payment_zone",zone);
            shipping_address.put("payment_zone_id",zone_id);
            shipping_address.put("payment_telephone",telephone);
            shipping_address.put("payment_email",email);

            //     Log.e("JSON ", jinnerobject.toString());
            Log.e("JSONARRAYrest ", jsonArray2.toString());


        } catch (JSONException e) {
            Log.e("errrr",""+e);
            e.printStackTrace();
        }
        return obj;
    }
    public  void showchangeaddressdialog() {
        countryid.clear();
        countryname.clear();
        statenamesarray.clear();
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
         statesp = (Spinner)dialog.findViewById(R.id.State);
         countrysp = (Spinner)dialog.findViewById(R.id.country11);
         saved = (TextView)dialog.findViewById(R.id.savedd);
        pb232 = (ProgressWheel)dialog.findViewById(R.id.pb111);
        parsingforcountry.parsing(Payment_and_deliveryActivity.this, 1);

        saved.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (v != null) {
                    InputMethodManager imm = (InputMethodManager)Payment_and_deliveryActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                if(address1.getText().toString().trim().equals("")||lastname.getText().toString().trim().equals("")||
                        cityss.getText().toString().trim().equals("")||statesp.getSelectedItem().toString().trim().equals("")||
                        pincode.getText().toString().trim().equals("") ||countrysp.getSelectedItem().toString().trim().equals("") )
                {
                    Toast.makeText(Payment_and_deliveryActivity.this, "Required fields missing !!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    parsingforchangeaddress.parsing(Payment_and_deliveryActivity.this,Address_id,
                            Customer_id,name.getText().toString().trim(),lastname.getText().toString(),"null",address1.getText().toString().trim(),
                            cityss.getText().toString().trim(),pincode.getText().toString().trim(),countryidstring,
                            stateidstring);
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
                statesp.setAdapter(new ArrayAdapter(pdp, R.layout.itemlayoutforspinner,
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

    public static void makepaymentbox(ArrayList<String> code_name, ArrayList<String> title_name, ArrayList<String> sort_order, ArrayList<String> terms) {
        title_name2.clear();
        title_name2 = title_name;
        Log.e("diwpay", "" + title_name+" "+title_name2);
       // Toast.makeText(Payment_and_deliveryActivity.pdp, "payment", Toast.LENGTH_SHORT).show();
        checkedss=new Boolean[title_name2.size()];
        for(int i=0;i<title_name2.size();i++) {

                checkedss[i] = false;
                llforradiobuttonadding.addView(addviewradiobutton(R.layout.layoutforcb, title_name2.get(i), i, checkedss[i], code_name, sort_order, terms));

        }
    }

    public static void makeshipmentbox(ArrayList<String> code_name, ArrayList<String> title_name, ArrayList<String> sort_order, ArrayList<List<Innermost_quotes>> quote) {
        //Toast.makeText(Payment_and_deliveryActivity.pdp, "ship", Toast.LENGTH_SHORT).show();

        title_name22.clear();
        title_name22 = title_name;
        Log.e("diwship", "" + title_name+" "+title_name22);
        checkedss2=new Boolean[title_name22.size()];
        for(int i=0;i<title_name22.size();i++) {

                checkedss2[i] = false;
                llforradiobuttonadding2.addView(addviewradiobutton2(R.layout.layoutforcb, title_name22.get(i), i, checkedss2[i], code_name, sort_order, quote));

        }

    }

    public static JSONObject getCartdetails22(String address_id,String first_name,String last_name,
                                            String company,String addresss1,String address2,
                                            String city,String postcode,String countrya,String country_id,
                                            String zone,String zone_id,String telephone,String email) {
        JSONObject obj = new JSONObject();
        JSONObject payment_address = new JSONObject();
        JSONObject shipping_address = new JSONObject();
        JSONObject paymentmethod = new JSONObject();
        JSONObject shippingmethod = new JSONObject();
        ct42 = dbm.getFullTable();
        JSONArray jsonArray22 = new JSONArray();
        try {

            obj.put("products",jsonArray22);
            obj.put("language_id","1");
            obj.put("coupon","1");
            obj.put("voucher","1");
            obj.put("customer_id",""+Customer_id);
            obj.put("payment_address",payment_address);
            obj.put("shipping_address",shipping_address);
            obj.put("payment_method",paymentmethod);
            obj.put("shipping_method",shippingmethod);


            for (int i = 0; i < ct42.size(); i++) {
                JSONObject jinnerobject22 = new JSONObject();
                jinnerobject22.put("product_id", ct42.get(i).getproductid());
                jinnerobject22.put("quantity",ct42.get(i).getQuantity());
                if(ct4.get(i).getOption().equals("")){
                    jinnerobject22.put("option", new JSONObject());

                }
                else {
                    jinnerobject22.put("option", new JSONObject("{\"" + (ct42.get(i).getOption()) + "\"}"));
                }
                jsonArray22.put(jinnerobject22);
            }
            payment_address.put("address_id",address_id);
            payment_address.put("payment_firstname",first_name);
            payment_address.put("payment_lastname",last_name);
            payment_address.put("payment_company",company);
            payment_address.put("payment_address_1",addresss1);
            payment_address.put("payment_address_2",address2);
            payment_address.put("payment_city",city);
            payment_address.put("payment_postcode",postcode);
            payment_address.put("payment_country",countrya);
            payment_address.put("payment_country_id",country_id);
            payment_address.put("payment_zone",zone);
            payment_address.put("payment_zone_id",zone_id);
            payment_address.put("payment_telephone",telephone);
            payment_address.put("payment_email",email);

            shipping_address.put("address_id",address_id);
            shipping_address.put("payment_firstname",first_name);
            shipping_address.put("payment_lastname",last_name);
            shipping_address.put("payment_company",company);
            shipping_address.put("payment_address_1",addresss1);
            shipping_address.put("payment_address_2",address2);
            shipping_address.put("payment_city",city);
            shipping_address.put("payment_postcode",postcode);
            shipping_address.put("payment_country",countrya);
            shipping_address.put("payment_country_id",country_id);
            shipping_address.put("payment_zone",zone);
            shipping_address.put("payment_zone_id",zone_id);
            shipping_address.put("payment_telephone",telephone);
            shipping_address.put("payment_email",email);

            paymentmethod.put("title",titlepayment);
            paymentmethod.put("code",codepayment);
            paymentmethod.put("terms",termspayment);
            paymentmethod.put("sort_order",sortorderpayment);

            shippingmethod.put("title",titleshipping);
            shippingmethod.put("code",codeshipping);
            shippingmethod.put("cost",costshipping);
            shippingmethod.put("tax_class_id",taxclassidshipping);
            shippingmethod.put("sort_order",sortordershipping);

            Log.e("JSONARRAYrest ", jsonArray22.toString());


        } catch (JSONException e) {
            Log.e("errrr",""+e);
            e.printStackTrace();
        }
        return obj;
    }


}
