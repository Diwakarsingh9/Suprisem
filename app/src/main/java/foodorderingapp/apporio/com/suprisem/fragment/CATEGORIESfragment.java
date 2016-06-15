package foodorderingapp.apporio.com.suprisem.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.Parsing.parsingforcategories;
import foodorderingapp.apporio.com.suprisem.R;
import foodorderingapp.apporio.com.suprisem.Sub_categoryActivity;
import foodorderingapp.apporio.com.suprisem.adapter.Categoriesadapter;
import views.ProgressWheel;

/**
 * Created by saifi45 on 5/17/2016.
 */
public class CATEGORIESfragment extends Fragment {
    Context ctc;
    public static ListView lv;
    Categoriesadapter cadp ;
    public static ProgressWheel pb;
    public static ArrayList<String> s_catname = new ArrayList<String>();
    public static ArrayList<String> s_catid = new ArrayList<String>();
    public static ArrayList<String> s_catimg = new ArrayList<String>();
    public static ArrayList<String> s_catstatus = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_categories, container, false);
        ctc = getActivity();
        pb= (ProgressWheel)v.findViewById(R.id.pb112);
        lv= (ListView)v.findViewById(R.id.listView);

//        cadp= new Categoriesadapter(ctc, catid, catname, catimg);
//        lv.setAdapter(cadp);
        parsingforcategories.parsing(ctc);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                s_catid.clear();
                s_catimg.clear();
                s_catname.clear();
                s_catstatus.clear();

                for(int j=0;j<parsingforcategories.sub_category.get(position).size();j++){
                    s_catid.add(parsingforcategories.sub_category.get(position).get(j).category_id);
                    s_catimg.add(parsingforcategories.sub_category.get(position).get(j).imagess);
                    s_catname.add(parsingforcategories.sub_category.get(position).get(j).namess);
                    s_catstatus.add(parsingforcategories.sub_category.get(position).get(j).status);
                }
                Intent in = new Intent(ctc, Sub_categoryActivity.class);
                in.putStringArrayListExtra("sub_category_id", s_catid);
                in.putStringArrayListExtra("sub_category_name", s_catname);
                in.putStringArrayListExtra("sub_category_img", s_catimg);
                in.putStringArrayListExtra("sub_category_status", s_catstatus);
                startActivity(in);

            }
        });


        return v;
    }



    @Override
    public void onResume() {

        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

}
