package foodorderingapp.apporio.com.suprisem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.Partnerwithus;
import foodorderingapp.apporio.com.suprisem.R;

/**
 * Created by saifi45 on 5/11/2016.
 */
public class PartnerwithusAdapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;
    private final ArrayList<Integer> mBackgroundColors;
    int pos=0;
    ArrayList<String> product_id = new ArrayList<String>();
    ArrayList<String> quantity = new ArrayList<String>();
    ArrayList<String> dates11 = new ArrayList<String>();
    ArrayList<String> food_category = new ArrayList<String>();
    ArrayList<String> names12 = new ArrayList<String>();
    ArrayList<String> prices21 = new ArrayList<String>();
    ArrayList<String> images11 = new ArrayList<String>();

    ArrayList<String> deliver_time = new ArrayList<String>();


    public PartnerwithusAdapter(Context c) {

        ctc = c;
        mBackgroundColors = new ArrayList<Integer>();
        mBackgroundColors.add(R.drawable.aaa);
        mBackgroundColors.add(R.drawable.bbb);
        mBackgroundColors.add(R.drawable.ccc);
        mBackgroundColors.add(R.drawable.ddd);
        mBackgroundColors.add(R.drawable.eee);


        inflater = LayoutInflater.from(this.ctc);
//        this.product_id=product_id11;
//        this.quantity=quantity;
//        this.images11=image11;
//        this.dates11=datepro;
//        this.food_category=food_category;
//        this.names12=names11;
//        this.prices21=prices11;
//        this.deliver_time=deliver_time;

    }

    @Override
    public int getCount() {
//        return product_id.size();
        return mBackgroundColors.size();
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
        public ImageView imgbg,plus,minus;
        public TextView product_name, resultquantity, product_price, noofunit_product, cuisines;
        TextView tv1, tv2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.itemlayoutforpartnerwithus, null);
            holder = new Holder();
            convertView.setTag(holder);


        }
        else {
            holder = (Holder)convertView.getTag();
        }


        holder.imgbg = (ImageView) convertView.findViewById(R.id.imgpartner);

        holder.imgbg.setImageResource(mBackgroundColors.get(position));



        return convertView;
    }
}


