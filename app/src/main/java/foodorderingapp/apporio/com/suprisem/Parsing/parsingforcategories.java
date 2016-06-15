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
import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_all_categories;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_categories;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Outer_all_categories;
import foodorderingapp.apporio.com.suprisem.adapter.Categoriesadapter;
import foodorderingapp.apporio.com.suprisem.fragment.CATEGORIESfragment;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;


/**
 * Created by saifi45 on 4/26/2016.
 */
public class parsingforcategories {
    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static SharedPreferences prefs2;
    public static List<Inner_all_categories> category_names = new ArrayList<>();
    public static ArrayList<String> catname = new ArrayList<String>();
    public static ArrayList<String> catid = new ArrayList<String>();
    public static ArrayList<String> catimg = new ArrayList<String>();
    public static ArrayList<String> catstatus = new ArrayList<String>();
    public static List<List<Innermost_all_categories>> sub_category = new ArrayList<>();


    public static void parsing(final Context c) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.all_categories;
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.GET, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                CATEGORIESfragment.pb.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    catname.clear();
                    catid.clear();
                    catimg.clear();
                    catstatus.clear();
                    sub_category.clear();

                    Outer_all_categories received2 = new Outer_all_categories();
                    received2 = gson.fromJson(response, Outer_all_categories.class);

                    if (received2.status.equals("success")) {
                        category_names=received2.categories;

                        for (int i = 0; i < category_names.size(); i++)
                        {
                            catname.add(category_names.get(i).namess);
                            catid.add(category_names.get(i).category_idss);
                            catimg.add(category_names.get(i).imagess);
                            catstatus.add(category_names.get(i).status);
                            sub_category.add(category_names.get(i).child);
                        }
                        CATEGORIESfragment.lv.setAdapter(new Categoriesadapter(c, catid,catname,catimg ));


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
        CATEGORIESfragment.pb.setVisibility(View.VISIBLE);
    }
}
