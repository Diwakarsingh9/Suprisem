package foodorderingapp.apporio.com.suprisem.Parsing;

import android.content.Context;
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
import foodorderingapp.apporio.com.suprisem.Payment_and_deliveryActivity;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_all_shipping;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_quotes;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Shipment_outter;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;

/**
 * Created by apporio6 on 18-06-2016.
 */
public class parsingforshippingmethod {

    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static SharedPreferences prefs2;
    public static List<Inner_all_shipping> shipping_method = new ArrayList<>();
    public static ArrayList<String> code_name = new ArrayList<String>();
    public static ArrayList<String> title_name = new ArrayList<String>();
    public static ArrayList<String> sort_order = new ArrayList<String>();
    public static ArrayList<List<Innermost_quotes>> quote = new ArrayList<>();




    public static void parsing(final Context c,String body) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.shipmentmethod;
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

                    code_name.clear();
                    title_name.clear();
                    sort_order.clear();
                    quote.clear();




                    Shipment_outter received2 = new Shipment_outter();
                    received2 = gson.fromJson(""+response, Shipment_outter.class);

                    if (received2.status.equals("success")) {
                        shipping_method=received2.shipping_method;
                        for (int i = 0; i < shipping_method.size(); i++)
                        {
                            code_name.add(shipping_method.get(i).code);
                            title_name.add(shipping_method.get(i).titles);
                            sort_order.add(shipping_method.get(i).sort_order);
                            quote.add(shipping_method.get(i).quote);


                        }
                        Payment_and_deliveryActivity.makeshipmentbox(code_name,title_name,sort_order,quote);


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
                if(error.toString().equals("com.android.volley.ServerError")){
                    Toast.makeText(c, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }

                Payment_and_deliveryActivity.pb111.setVisibility(View.GONE);
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
