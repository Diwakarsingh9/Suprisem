package foodorderingapp.apporio.com.suprisem.Parsing;

import android.content.Context;
import android.content.Intent;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import foodorderingapp.apporio.com.suprisem.Api_s.Apis_url;
import foodorderingapp.apporio.com.suprisem.CartActivity;
import foodorderingapp.apporio.com.suprisem.Payment_and_deliveryActivity;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Login_outter;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Signup_outter;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;

/**
 * Created by apporio6 on 17-06-2016.
 */
public class parsingforsignup {
    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static SharedPreferences prefs2;
    public static void parsing(final CartActivity c, final String firstname, final String lastname,
                               final String mobile, final String username, String password,
                               String confirmpass, String address, String city,
                               String state, String country, String pincode) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
//        String b= "{\"" +
//                "  \"username\": \"diw@o.com\",  \"password\": \"123456\"\n" +
//                "    \"" +
//                "}";
        String body = "{\"firstname"+"\":\""+firstname+"\","+"\"lastname"+"\":\""+lastname+"\","+
                "\"phone"+"\":\""+mobile+"\","+"\"address"+"\":\""+address+"\","+
                "\"city"+"\":\""+city+"\","+"\"pincode"+"\":\""+pincode+"\","+
                "\"country"+"\":\""+country+"\","+"\"state"+"\":\""+state+"\","+
                "\"password"+"\":\""+password+"\","+"\"email"+"\":\""+username+"\","+
                "\"company"+"\":\""+"surprisem"+"\","+"\"fax"+"\":\""+"null"+"\","+
                "\"newsletter"+"\":\""+"null"+"\"}";
        Log.e("bodydd", "" + body);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.signup;
        final String mRequestBody = body.toString();
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, urlforRest_food,mRequestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                CartActivity.pb23.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();



                    Signup_outter received2 = new Signup_outter();
                    received2 = gson.fromJson(""+response, Signup_outter.class);

                    if (received2.status.equals("success")) {
//                        product_names=received2.customer;
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
                        SharedPreferences.Editor edit2 = prefs.edit();

                        edit2.putBoolean("pref_previously_started", Boolean.TRUE);
                        edit2.putString("Customerid", "" +received2.customer_id);
                        edit2.putString("firstname", "" + firstname);
                        edit2.putString("lastname", "" + lastname);
                        edit2.putString("email", "" +username);
                        edit2.putString("telephone", "" + mobile);
                        edit2.putString("fax", "" + "null");
                        edit2.putString("newsletter", "" + "null");
                        edit2.putString("wishlist", "" + "null");
                        edit2.putString("total", "" + "null");
                        edit2.commit();
                        Intent in = new Intent(c,Payment_and_deliveryActivity.class);
                        in.putExtra("Customer_id",received2.customer_id);
                        c.startActivity(in);

                       CartActivity.dialog2.dismiss();

                    } else {

                        CartActivity.pb23.setVisibility(View.GONE);

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
        CartActivity.pb23.setVisibility(View.VISIBLE);
    }
}
