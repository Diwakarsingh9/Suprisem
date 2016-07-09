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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.R;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;

/**
 * Created by saifi45 on 4/30/2016.
 */
public class Productlistadapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;

    int pos=0;
    ArrayList<String> pro_id = new ArrayList<String>();
    ArrayList<String> pro_name = new ArrayList<String>();
    ArrayList<String> pro_img = new ArrayList<String>();
    ArrayList<String> pro_status = new ArrayList<String>();
    ArrayList<String> names12 = new ArrayList<String>();
    ArrayList<String> prices21 = new ArrayList<String>();
    ArrayList<String> images11 = new ArrayList<String>();
    private ImageLoader mImageLoader;
    ArrayList<String> deliver_time = new ArrayList<String>();


    public Productlistadapter(Context c, ArrayList<String> pro_id, ArrayList<String> pro_name, ArrayList<String> pro_img, ArrayList<String> pro_status) {

        ctc = c;

        mImageLoader = VolleySingleton.getInstance(ctc)
                .getImageLoader();
        inflater = LayoutInflater.from(this.ctc);
        this.pro_id=pro_id;
        this.pro_name=pro_name;
        this.pro_img=pro_img;
        this.pro_status=pro_status;
//        this.food_category=food_category;
//        this.names12=names11;
//        this.prices21=prices11;
//        this.deliver_time=deliver_time;

    }

    @Override
    public int getCount() {
//        return product_id.size();
        return pro_name.size();
    }

    @Override
    public Object getItem(int position) {
        Log.e("position", "Dish" + position+" "+pro_name.get(position));
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class Holder {
        public LinearLayout size, toppings, sausage, sizeno, toppingsno, sausageno;
        public ImageView mNetworkImageView,plus,minus;
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
        holder.mNetworkImageView = (ImageView) convertView.findViewById(R.id.img);
        String url = "";
        if(pro_img.get(position).replace(" ", "%20").equals("")){
            url="abc";
        }
        else{
            url = pro_img.get(position).replace(" ", "%20");
        }

        Picasso.with(ctc)
                .load(url)
                .placeholder(R.drawable.stub) // optional
                .error(R.drawable.errorimg)         // optional
                .into(holder.mNetworkImageView);


        holder.product_name.setText(pro_name.get(position));



        return convertView;
    }
}


