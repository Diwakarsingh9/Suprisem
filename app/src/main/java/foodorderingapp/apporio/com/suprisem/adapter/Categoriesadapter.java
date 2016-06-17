package foodorderingapp.apporio.com.suprisem.adapter;

import android.content.Context;
import android.util.Log;
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
 * Created by saifi45 on 4/29/2016.
 */
public class Categoriesadapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;
    private NetworkImageView mNetworkImageView;
    private ImageLoader mImageLoader;
    int pos=0;
    ArrayList<String> product_id = new ArrayList<String>();
    ArrayList<String> quantity = new ArrayList<String>();
    ArrayList<String> dates11 = new ArrayList<String>();
    ArrayList<String> food_category = new ArrayList<String>();
    ArrayList<String> catid = new ArrayList<String>();
    ArrayList<String> catname = new ArrayList<String>();
    ArrayList<String> catimg = new ArrayList<String>();

    ArrayList<String> deliver_time = new ArrayList<String>();


    public Categoriesadapter(Context c, ArrayList<String> catid, ArrayList<String> catname, ArrayList<String> catimg) {

        ctc = c;

        //Image URL - This can point to any image file supported by Android



        inflater = LayoutInflater.from(this.ctc);
        this.catid=catid;
        this.catname=catname;
        this.catimg=catimg;
        mImageLoader = VolleySingleton.getInstance(ctc)
                .getImageLoader();
//        this.dates11=datepro;
//        this.food_category=food_category;
//        this.names12=names11;
//        this.prices21=prices11;
//        this.deliver_time=deliver_time;

    }

    @Override
    public int getCount() {
//        return product_id.size();
        return catname.size();
    }

    @Override
    public Object getItem(int position) {
        Log.e("position", "Dish" +catname.get(position));
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

        mImageLoader.get(catimg.get(position).replace(" ","%20"), ImageLoader.getImageListener(holder.mNetworkImageView,
                R.drawable.stub, R.drawable
                        .errorimg));
        holder.mNetworkImageView.setImageUrl(catimg.get(position).replace(" ","%20"), mImageLoader);

            holder.product_name.setText(catname.get(position));



        return convertView;
    }
}


