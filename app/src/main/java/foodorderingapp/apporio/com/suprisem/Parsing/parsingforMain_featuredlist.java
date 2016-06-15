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
import foodorderingapp.apporio.com.suprisem.Product_list_Activity;
import foodorderingapp.apporio.com.suprisem.R;
import foodorderingapp.apporio.com.suprisem.SampleData;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_all_categories;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_all_featured;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_all_products;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_categories;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_pro_images;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_pro_options;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Outer_all_categories;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Outer_all_products;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Outter_featured;
import foodorderingapp.apporio.com.suprisem.adapter.Categoriesadapter;
import foodorderingapp.apporio.com.suprisem.adapter.Productlistadapter;
import foodorderingapp.apporio.com.suprisem.adapter.SampleAdapter;
import foodorderingapp.apporio.com.suprisem.fragment.CATEGORIESfragment;
import foodorderingapp.apporio.com.suprisem.fragment.Mainfragment;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;

/**
 * Created by saifi45 on 6/10/2016.
 */
public class parsingforMain_featuredlist {
    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static SharedPreferences prefs2;
    public static List<Inner_all_featured> product_names = new ArrayList<>();
    public static ArrayList<String> pro_name = new ArrayList<String>();
    public static ArrayList<String> pro_id = new ArrayList<String>();
    public static ArrayList<String> pro_img = new ArrayList<String>();
    public static ArrayList<String> pro_desc = new ArrayList<String>();
    public static ArrayList<String> pro_price = new ArrayList<String>();
    public static ArrayList<String> pro_status = new ArrayList<String>();
    public static ArrayList<ArrayList<Innermost_all_pro_images>> pro_imagess = new ArrayList<>();
    public static ArrayList<ArrayList<Innermost_all_pro_options>> pro_options = new ArrayList<>();

    public static void parsing(final Context c) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.featured_list ;
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.GET, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                Mainfragment.pb.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    pro_name.clear();
                    pro_id.clear();
                    pro_img.clear();
                    pro_status.clear();
                    pro_imagess.clear();
                    pro_desc.clear();
                    pro_price.clear();
                    pro_options.clear();

                    Outter_featured received2 = new Outter_featured();
                    received2 = gson.fromJson(response, Outter_featured.class);

                    if (received2.status.equals("success")) {
                        product_names=received2.products;

                        for (int i = 0; i < product_names.size(); i++)
                        {
                            pro_name.add(product_names.get(i).name);
                            pro_id.add(product_names.get(i).product_id);
                            pro_img.add(product_names.get(i).image);
                            pro_status.add(product_names.get(i).status);
                            pro_desc.add(product_names.get(i).description);
                            pro_price.add(product_names.get(i).price);
                            pro_imagess.add(product_names.get(i).images);
                            pro_options.add(product_names.get(i).options);
                        }
//                        Mainfragment. mAdapter = new SampleAdapter(c, R.id.txt_line1);
//                        if (Mainfragment.mData == null) {
//                            Mainfragment.mData = pro_name;
//                        }
//
//                        for (String data : Mainfragment.mData) {
//                            Mainfragment.mAdapter.add(data);
//                        }

                        Mainfragment.mGridView.setAdapter(new SampleAdapter(c, R.id.txt_line1,pro_name,pro_img));

                    } else {



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
        Mainfragment.pb.setVisibility(View.VISIBLE);
    }
}
