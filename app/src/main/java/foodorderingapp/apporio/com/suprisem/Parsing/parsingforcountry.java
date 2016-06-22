package foodorderingapp.apporio.com.suprisem.Parsing;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import foodorderingapp.apporio.com.suprisem.Api_s.Apis_url;
import foodorderingapp.apporio.com.suprisem.CartActivity;
import foodorderingapp.apporio.com.suprisem.Payment_and_deliveryActivity;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Country_list;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_pro_images;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_pro_options;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;

/**
 * Created by apporio6 on 20-06-2016.
 */
public class parsingforcountry {

    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static SharedPreferences prefs2;
    public static List<Country_list> country_names = new ArrayList<>();
    public static ArrayList<String> country_id = new ArrayList<String>();
    public static ArrayList<String> country_name = new ArrayList<String>();
    public static ArrayList<JSONArray> statenames = new ArrayList<>();
    public static ArrayList<String> pro_desc = new ArrayList<String>();
    public static ArrayList<String> pro_price = new ArrayList<String>();
    public static ArrayList<String> pro_status = new ArrayList<String>();
    public static ArrayList<ArrayList<Innermost_all_pro_images>> pro_imagess = new ArrayList<>();
    public static ArrayList<ArrayList<Innermost_all_pro_options>> pro_options = new ArrayList<>();

    public static void parsing(final Context c, final int page) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.countryid;
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.GET, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();

//                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    country_name.clear();
                    country_id.clear();
                    statenames.clear();


//                  received2 = new Country_list[];
                  //  Country_list[]  received2 = gson.fromJson(response,  Country_list[].class);
                try {
                    JSONArray json = new JSONArray(response);



                for (int i = 0; i < json.length(); i++)
                        {
                            JSONObject e = json.getJSONObject(i);
                            country_id.add(e.getString("country_id"));
                            country_name.add(e.getString("name"));
                            statenames.add( e.getJSONArray("state"));


                        }
                    statenames.get(0).getJSONObject(0).getString("name");
                    if(page==0){
                        CartActivity.countryidstateid(country_id,country_name,statenames);
                    }
                    else {
                        Payment_and_deliveryActivity.countryidstateid(country_id, country_name, statenames);

                    }
                        Log.e("country_list",""+statenames.get(0));

//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                } catch (JSONException e) {
                    Log.e("excptn",""+e);
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

    }
}
