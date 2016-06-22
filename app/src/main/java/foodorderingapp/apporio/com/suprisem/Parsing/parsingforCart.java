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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import foodorderingapp.apporio.com.suprisem.Api_s.Apis_url;
import foodorderingapp.apporio.com.suprisem.CartActivity;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Cart_Outter;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Checksetter_getter;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_all_products_cart;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_pro_options_cart;
import foodorderingapp.apporio.com.suprisem.adapter.Cartadapter;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;

/**
 * Created by saifi45 on 6/15/2016.
 */
public class parsingforCart {

    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static SharedPreferences prefs2;
    public static List<Inner_all_products_cart> product_names = new ArrayList<>();
    public static ArrayList<String> pro_name = new ArrayList<String>();
    public static ArrayList<String> pro_id = new ArrayList<String>();
    public static ArrayList<String> pro_img = new ArrayList<String>();
    public static ArrayList<String> pro_quantity = new ArrayList<String>();
    public static ArrayList<String> pro_price = new ArrayList<String>();
    public static ArrayList<String> pro_status = new ArrayList<String>();
    public static ArrayList<String> namessss = new ArrayList<>();
    public static ArrayList<List<String> >namessss22= new ArrayList<>();
    public static ArrayList<ArrayList<Innermost_pro_options_cart>> pro_imagess = new ArrayList<>();
    public static ArrayList<ArrayList<Innermost_pro_options_cart>> pro_options = new ArrayList<>();


    public static void parsing(final Context c,String body) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        Log.e("body", "" + body);
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.get_Cart;
        final String mRequestBody = body.toString();
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
       JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, urlforRest_food,mRequestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                CartActivity.pb.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    pro_name.clear();
                    pro_id.clear();
                    pro_img.clear();
                    pro_status.clear();
                    pro_imagess.clear();
                    pro_quantity.clear();
                    pro_price.clear();
                    pro_options.clear();

                    Checksetter_getter rcv = new Checksetter_getter();
                    rcv = gson.fromJson(response+"",Checksetter_getter.class);

                    if(rcv.status.equals("success")) {

                        Cart_Outter received2 = new Cart_Outter();
                        received2 = gson.fromJson("" + response, Cart_Outter.class);

                        if (received2.status.equals("success")) {
                            product_names = received2.cart;
                            CartActivity.totalprice.setText("Total : " + received2.total);
                            for (int i = 0; i < product_names.size(); i++) {
                                pro_id.add(product_names.get(i).product_id);
                                pro_name.add(product_names.get(i).namess);
                                pro_img.add(product_names.get(i).image);
                                pro_quantity.add(product_names.get(i).quantity);
                                pro_options.add(product_names.get(i).optionsss);
                                pro_price.add(product_names.get(i).price);


                            }

                            CartActivity.lv.setAdapter(new Cartadapter(c, pro_id, pro_name, pro_img,
                                    pro_quantity, pro_options, pro_price));

                        } else {

                            CartActivity.pb.setVisibility(View.GONE);

                        }
                    }
                    else {
                        CartActivity.pb.setVisibility(View.GONE);
                        CartActivity.totalprice.setText("Total : $ 00.00");
                        Toast.makeText(c, "Cart Is empty", Toast.LENGTH_SHORT).show();
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
                    CartActivity.pb.setVisibility(View.GONE);
                    CartActivity.totalprice.setText("Total : $ 00.00");
                    CartActivity.totlitem.setText("0");
                    Toast.makeText(c, "Cart Is empty", Toast.LENGTH_SHORT).show();
                }
                CartActivity.lv.setVisibility(View.GONE);
                CartActivity.pb.setVisibility(View.GONE);
                // Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();
            }

        })


        {

//            @Override
//            public String getBodyContentType() {
//                return "application/json; charset=utf-8";
//            }

//            @Override
//            public byte[] getBody() throws AuthFailureError {
//                try {
//                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
//                } catch (UnsupportedEncodingException uee) {
//                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
//                            mRequestBody, "utf-8");
//                    return null;
//                }
//            }

//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                String responseString = "";
//                if (response != null) {
//                    responseString = String.valueOf(response.statusCode);
//                    Log.e("Sucess", "" + responseString+" "+response);
//                    // can get more details such as response.headers
//                }
//                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//            }

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
        CartActivity.pb.setVisibility(View.VISIBLE);
    }
}
