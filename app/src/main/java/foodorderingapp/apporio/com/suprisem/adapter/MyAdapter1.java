package foodorderingapp.apporio.com.suprisem.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.fragment.Basefragmentforphotoopen;


public class MyAdapter1 extends FragmentPagerAdapter {
    ArrayList<String> a;
    ArrayList<String> f;
        int b;


    public MyAdapter1(FragmentManager fm, int position1, ArrayList<String> arrayB) {
        super(fm);
        this.b =position1;
        f=arrayB;


    }



    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;

        frag= Basefragmentforphotoopen.newInstance(position, f.get(position),f);


        return frag;
    }

    @Override
    public int getCount() {
        return f.size();
    }
}