package foodorderingapp.apporio.com.suprisem.Parsing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import foodorderingapp.apporio.com.suprisem.Api_s.Apis_url;
import foodorderingapp.apporio.com.suprisem.CartActivity;
import foodorderingapp.apporio.com.suprisem.Payment_and_deliveryActivity;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Cart_Outter;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Checksetter_getter;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_all_products_cart;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_login;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Login_outter;
import foodorderingapp.apporio.com.suprisem.adapter.Cartadapter;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;

/**
 * Created by apporio6 on 17-06-2016.
 */
public class parsingforlogin {



    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static SharedPreferences prefs2;
//    public static List<Inner_login> product_names = new ArrayList<>();
    public static ArrayList<String> pro_name = new ArrayList<String>();
    public static ArrayList<String> pro_id = new ArrayList<String>();
    public static ArrayList<String> pro_img = new ArrayList<String>();
    public static ArrayList<String> pro_quantity = new ArrayList<String>();
    public static ArrayList<String> pro_price = new ArrayList<String>();
    public static ArrayList<String> pro_status = new ArrayList<String>();

    public static void parsing(final Context c,String username,String pass) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
//        String b= "{\"" +
//                "  \"username\": \"diw@o.com\",  \"password\": \"123456\"\n" +
//                "    \"" +
//                "}";
        String body = "{\"username"+"\":\""+username+"\","+"\"password"+"\":\""+pass+"\"}";
        Log.e("bodydd", "" + body);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.login;
        final String mRequestBody = body.toString();
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, urlforRest_food,mRequestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                CartActivity.pb22.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    Checksetter_getter rec = new Checksetter_getter();
                    rec = gson.fromJson(""+response,Checksetter_getter.class);
                    if(rec.status.equals("success")) {

                    Login_outter received2 = new Login_outter();
                    received2 = gson.fromJson(""+response, Login_outter.class);

                    if (received2.status.equals("success")) {
//                        product_names=received2.customer;
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
                        SharedPreferences.Editor edit2 = prefs.edit();

                        edit2.putBoolean("pref_previously_started", Boolean.TRUE);
                        edit2.putString("Customerid", "" +received2.customer.customer_id);
                        edit2.putString("firstname", "" + received2.customer.firstname);
                        edit2.putString("lastname", "" + received2.customer.lastname);
                        edit2.putString("email", "" +received2.customer.emails);
                        edit2.putString("telephone", "" + received2.customer.telephone);
                        edit2.putString("fax", "" + received2.customer.fax);
                        edit2.putString("newsletter", "" + received2.customer.newsletter);
                        edit2.putString("wishlist", "" +received2.customer.wishlist);
                        edit2.putString("total", "" + received2.customer.total);
                        edit2.commit();
                        Intent in = new Intent(c,Payment_and_deliveryActivity.class);
                        in.putExtra("Customer_id",received2.customer.customer_id);
                        in.putExtra("Cart_details",""+prefs.getString(""+CartActivity.getCartdetails(),"null"));

                        c.startActivity(in);
                        CartActivity.dialog.dismiss();
                    } else {

                        CartActivity.pb22.setVisibility(View.GONE);


                    }
                    }
                    else {
                        CartActivity.pb23.setVisibility(View.GONE);
                        Toast.makeText(c, ""+rec.message, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                CartActivity.pb22.setVisibility(View.GONE);
//                Menufragment.pb.setVisibility(View.GONE);
                Log.e("Sucess", "" + error.toString());
                // Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();

            }

        })


        {


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
        CartActivity.pb22.setVisibility(View.VISIBLE);
    }
}
