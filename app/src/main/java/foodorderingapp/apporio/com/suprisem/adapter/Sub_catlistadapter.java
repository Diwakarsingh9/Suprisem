package foodorderingapp.apporio.com.suprisem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.R;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;

/**
 * Created by saifi45 on 6/9/2016.
 */
public class Sub_catlistadapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;
    //    private final ArrayList<Integer> mBackgroundColors;
    int pos=0;
    ArrayList<String> s_catid = new ArrayList<String>();
    ArrayList<String> s_catname = new ArrayList<String>();
    ArrayList<String> s_catimg = new ArrayList<String>();
    ArrayList<String> s_catstatus = new ArrayList<String>();
    ArrayList<String> names12 = new ArrayList<String>();
    ArrayList<String> prices21 = new ArrayList<String>();
    ArrayList<String> images11 = new ArrayList<String>();
    private ImageLoader mImageLoader;
    ArrayList<String> deliver_time = new ArrayList<String>();


    public Sub_catlistadapter(Context c, ArrayList<String> s_catid, ArrayList<String> s_catname, ArrayList<String> s_catimg, ArrayList<String> s_catstatus) {

        ctc = c;
//
        mImageLoader = VolleySingleton.getInstance(ctc)
                .getImageLoader();
        inflater = LayoutInflater.from(this.ctc);
        this.s_catid=s_catid;
        this.s_catname=s_catname;
        this.s_catimg=s_catimg;
        this.s_catstatus=s_catstatus;
//

    }

    @Override
    public int getCount() {
//        return product_id.size();
        return s_catname.size();
    }

    @Override
    public Object getItem(int position) {
        //Log.e("position", "Dish" + position);
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class Holder {
        public LinearLayout size, toppings, sausage, sizeno, toppingsno, sausageno;
        public NetworkImageView mNetworkImageView,plus,minus;
        public TextView product_name, resultquantity, product_price, noofunit_product, cuisines;
        TextView tv1, tv2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.itemlayoutforcategories, null);
            holder = new Holder();
            convertView.setTag(holder);


        }
        else {
            holder = (Holder)convertView.getTag();
        }

        holder.product_name = (TextView) convertView.findViewById(R.id.txt);


        holder.mNetworkImageView = (NetworkImageView) convertView.findViewById(R.id.img);

        mImageLoader.get(s_catimg.get(position), ImageLoader.getImageListener(holder.mNetworkImageView,
                R.drawable.stub, R.drawable
                        .errorimg));
        holder.mNetworkImageView.setImageUrl(s_catimg.get(position), mImageLoader);

        holder.product_name.setText(s_catname.get(position));

        return convertView;
    }
}


