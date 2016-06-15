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
import foodorderingapp.apporio.com.suprisem.adapter.Favlistadapter;
import views.ProgressWheel;

/**
 * Created by saifi45 on 5/17/2016.
 */
public class Favfragment extends Fragment {
    Context ctc;
    public static ListView lv;
    Favlistadapter fadp ;
    public static ProgressWheel pb;
    public static ArrayList<String> s_catname = new ArrayList<String>();
    public static ArrayList<String> s_catid = new ArrayList<String>();
    public static ArrayList<String> s_catimg = new ArrayList<String>();
    public static ArrayList<String> s_catstatus = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_favourites_list, container, false);
        ctc = getActivity();
        pb= (ProgressWheel)v.findViewById(R.id.pb11);
        lv= (ListView)v.findViewById(R.id.listView);

//        cadp= new Categoriesadapter(ctc, catid, catname, catimg);
//        lv.setAdapter(cadp);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });


        return v;
    }



    @Override
    public void onResume() {

        super.onResume();
        parsingforcategories.parsing(ctc);
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
