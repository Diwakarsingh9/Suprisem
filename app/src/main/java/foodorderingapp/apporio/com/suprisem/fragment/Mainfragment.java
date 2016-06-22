package foodorderingapp.apporio.com.suprisem.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.InnerPrductActivity;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforMain_featuredlist;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforproductlist;
import foodorderingapp.apporio.com.suprisem.R;
import foodorderingapp.apporio.com.suprisem.SampleData;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_pro_options;
import foodorderingapp.apporio.com.suprisem.StoreCommonValues;
import foodorderingapp.apporio.com.suprisem.adapter.SampleAdapter;
import views.ProgressWheel;

public class Mainfragment extends Fragment implements
        AbsListView.OnScrollListener, AbsListView.OnItemClickListener {


    public static GridView mGridView;
    private boolean mHasRequestedMore;
    public static SampleAdapter mAdapter;
    public static ProgressWheel pb;
    public static ArrayList<String> mData;
    public static ArrayList<String> prod_img = new ArrayList<String>();
    public static ArrayList<Innermost_all_pro_options> pro_options = new ArrayList<>();

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mainfragment, container, false);
        mGridView = (GridView) v.findViewById(R.id.gridview);
        pb = (ProgressWheel) v.findViewById(R.id.pb155);
        parsingforMain_featuredlist.parsing(getContext());
        mGridView.setOnScrollListener(this);
        mGridView.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        if (savedInstanceState == null) {
//
        }

        if (mAdapter == null) {


        }


    }

    @Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {
        Log.e("", "onScrollStateChanged:" + scrollState);
    }

    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
        Log.e("", "onScroll firstVisibleItem:" + firstVisibleItem +
                " visibleItemCount:" + visibleItemCount +
                " totalItemCount:" + totalItemCount);
        // our handling
//        if (!mHasRequestedMore) {
//            int lastInScreen = firstVisibleItem + visibleItemCount;
//            if (lastInScreen >= totalItemCount) {
//                Log.e("", "onScroll lastInScreen - so load more");
//                mHasRequestedMore = true;
//               // onLoadMoreItems();
//            }
//        }
    }

    private void onLoadMoreItems() {
//        final ArrayList<String> sampleData = SampleData.generateSampleData();
//        for (String data : sampleData) {
//            mAdapter.add(data);
//        }
//        // stash all the data in our backing store
//        mData.addAll(sampleData);
//        // notify the adapter that we can update now
//        mAdapter.notifyDataSetChanged();
//        mHasRequestedMore = false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        prod_img.clear();
        pro_options.clear();
        StoreCommonValues.optionpro.clear();
        for(int j=0;j< parsingforMain_featuredlist.pro_imagess.get(position).size();j++){
            prod_img.add(parsingforMain_featuredlist.pro_imagess.get(position).get(j).image);
        }
        for(int j=0;j< parsingforMain_featuredlist.pro_options.get(position).size();j++){
            pro_options.add(parsingforMain_featuredlist.pro_options.get(position).get(j));
        }
        Intent i = new Intent(getActivity(), InnerPrductActivity.class);
        i.putExtra("act","main");
        i.putExtra("product_id", parsingforMain_featuredlist.pro_id.get(position));
        i.putExtra("product_price", parsingforMain_featuredlist.pro_price.get(position));
        i.putExtra("product_descp", parsingforMain_featuredlist.pro_desc.get(position));
        i.putExtra("product_name", parsingforMain_featuredlist.pro_name.get(position));
        i.putStringArrayListExtra("pro_imagess", prod_img);
        StoreCommonValues.optionpro = pro_options;
        startActivity(i);
        //Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
    }
}
