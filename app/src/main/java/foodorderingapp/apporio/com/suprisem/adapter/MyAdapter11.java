package foodorderingapp.apporio.com.suprisem.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.fragment.Basefragmentforphotoopen;
import foodorderingapp.apporio.com.suprisem.fragment.Basefragmentforphotoopeninner;

/**
 * Created by saifi45 on 4/30/2016.
 */
public class MyAdapter11 extends FragmentPagerAdapter {
    ArrayList<String> a;
    ArrayList<String> f;
    int b;


    public MyAdapter11(FragmentManager fm, int position1, ArrayList<String> arrayB) {
        super(fm);
        this.b =position1;
        f=arrayB;


    }



    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;

        frag= Basefragmentforphotoopeninner.newInstance(position, f.get(position));


        return frag;
    }

    @Override
    public int getCount() {
        return f.size();
    }
}