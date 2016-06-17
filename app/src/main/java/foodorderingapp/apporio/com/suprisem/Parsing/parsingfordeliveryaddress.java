package foodorderingapp.apporio.com.suprisem.Parsing;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import foodorderingapp.apporio.com.suprisem.Api_s.Apis_url;
import foodorderingapp.apporio.com.suprisem.CartActivity;
import foodorderingapp.apporio.com.suprisem.Payment_and_deliveryActivity;
import foodorderingapp.apporio.com.suprisem.Product_list_Activity;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_all_address;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_all_products;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_pro_images;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_pro_options;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Outer_all_products;
import foodorderingapp.apporio.com.suprisem.adapter.Productlistadapter;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;

/**
 * Created by apporio6 on 17-06-2016.
 */
public class parsingfordeliveryaddress {

    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static SharedPreferences prefs2;
    public static List<Inner_all_address> address_names = new ArrayList<>();
    public static ArrayList<String> address = new ArrayList<String>();
    public static ArrayList<String> city = new ArrayList<String>();
    public static ArrayList<String> postcode = new ArrayList<String>();
    public static ArrayList<String> address_id = new ArrayList<String>();
    public static ArrayList<String> pro_price = new ArrayList<String>();
    public static ArrayList<String> pro_status = new ArrayList<String>();
    public static ArrayList<ArrayList<Innermost_all_pro_images>> pro_imagess = new ArrayList<>();
    public static ArrayList<ArrayList<Innermost_all_pro_options>> pro_options = new ArrayList<>();

    public static void parsing(final Context c, final String cust_id) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.GetAddress+"&customer_id="+cust_id;
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.GET, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                Payment_and_deliveryActivity.pb111.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    city.clear();
                    address.clear();
                    postcode.clear();
                    address_id.clear();


                    Outter_address received2 = new Outter_address();
                    received2 = gson.fromJson(response, Outter_address.class);

                    if (received2.status.equals("success")) {
                        address_names=received2.customer_address;

                        for (int i = 0; i < address_names.size(); i++)
                        {
                            address.add(address_names.get(i).address_1);
                            city.add(address_names.get(i).city);
                            postcode.add(address_names.get(i).postcode);
                            address_id.add(address_names.get(i).address_id);

                        }
                        if(address_names.size()>0){
                            Payment_and_deliveryActivity.addresssssss.setText(""+address.get(0)+" , "+""+
                            city.get(0)+" , "+postcode.get(0)+" \nMobile No - " +
                                    prefs2.getString("telephone","xxxxxxxxx"));
                            Payment_and_deliveryActivity.Address_id = address_id.get(0);
                        }

                    } else {
                        Payment_and_deliveryActivity.pb111.setVisibility(View.GONE);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Menufragment.pb.setVisibility(View.GONE);
                Log.e("Sucess", "" + error.toString());
                // Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(sr);
        Payment_and_deliveryActivity.pb111.setVisibility(View.VISIBLE);
    }
}
