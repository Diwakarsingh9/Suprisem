package foodorderingapp.apporio.com.suprisem.Parsing;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.AlertDialogWrapper;
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
import java.util.Map;

import foodorderingapp.apporio.com.suprisem.Api_s.Apis_url;
import foodorderingapp.apporio.com.suprisem.CartActivity;
import foodorderingapp.apporio.com.suprisem.Payment_and_deliveryActivity;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Login_outter;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Placeorder_outter;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;

/**
 * Created by apporio6 on 18-06-2016.
 */
public class parsingfororderplace {

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

    public static void parsing(final Context c,String orderid,String orderstatusid) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
//        String b= "{\"" +
//                "  \"username\": \"diw@o.com\",  \"password\": \"123456\"\n" +
//                "    \"" +
//                "}";
        String body = "{\"order_id"+"\":\""+orderid+"\","+"\"order_status_id"+"\":\""+orderstatusid+"\"}";
        Log.e("bodydd", "" + body);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.placeorder;
        final String mRequestBody = body.toString();
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, urlforRest_food,mRequestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                Payment_and_deliveryActivity.pb111.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();



                    Placeorder_outter received2 = new Placeorder_outter();
                    received2 = gson.fromJson(""+response, Placeorder_outter.class);

                    if (received2.status.equals("success")) {
//                        product_names=received2.customer;
                        CartActivity.cartact.finish();
                        Payment_and_deliveryActivity.pdp.finish();
                        Payment_and_deliveryActivity.dbm.clearCartTable();
                        Toast.makeText(c, "Your order has been placed !!!", Toast.LENGTH_SHORT).show();
                       // Toast.makeText(c, ""+received2.message, Toast.LENGTH_SHORT).show();


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

                Payment_and_deliveryActivity.pb111.setVisibility(View.GONE);
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
        Payment_and_deliveryActivity.pb111.setVisibility(View.VISIBLE);
    }
}
