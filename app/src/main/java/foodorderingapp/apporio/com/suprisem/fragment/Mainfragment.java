package foodorderingapp.apporio.com.suprisem.fragment;


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

import foodorderingapp.apporio.com.suprisem.Parsing.parsingforMain_featuredlist;
import foodorderingapp.apporio.com.suprisem.R;
import foodorderingapp.apporio.com.suprisem.SampleData;
import foodorderingapp.apporio.com.suprisem.adapter.SampleAdapter;
import views.ProgressWheel;

public class Mainfragment extends Fragment implements
        AbsListView.OnScrollListener, AbsListView.OnItemClickListener {


    public static GridView mGridView;
    private boolean mHasRequestedMore;
    public static SampleAdapter mAdapter;
    public static ProgressWheel pb;
    public static ArrayList<String> mData;

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
        Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
    }
}
