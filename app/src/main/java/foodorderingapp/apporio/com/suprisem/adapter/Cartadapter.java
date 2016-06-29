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
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.CartActivity;
import foodorderingapp.apporio.com.suprisem.Database.CartTable;
import foodorderingapp.apporio.com.suprisem.Database.DBManager;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforCart;
import foodorderingapp.apporio.com.suprisem.R;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_pro_options_cart;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;
import io.realm.RealmResults;

/**
 * Created by saifi45 on 5/3/2016.
 */
public class Cartadapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;

    public static RealmResults<CartTable> ct4;
    private ImageLoader mImageLoader;
    int pos=0;
    static DBManager dbm;
    ArrayList<String> optionvalue = new ArrayList<>();
    ArrayList<String> pro_id = new ArrayList<String>();
    ArrayList<String> pro_name = new ArrayList<String>();
    ArrayList<String> pro_img = new ArrayList<String>();
    ArrayList<String> pro_quantity = new ArrayList<String>();
    ArrayList<ArrayList<Innermost_pro_options_cart>> pro_options = new ArrayList<>();
    ArrayList<String> pro_price = new ArrayList<String>();
    ArrayList<String> images11 = new ArrayList<String>();

    ArrayList<String> deliver_time = new ArrayList<String>();


    public Cartadapter(Context c, ArrayList<String> pro_id, ArrayList<String> pro_name,
                       ArrayList<String> pro_img, ArrayList<String> pro_quantity,
                       ArrayList<ArrayList<Innermost_pro_options_cart>> pro_options, ArrayList<String> pro_price) {
        optionvalue.clear();
        ctc = c;
        dbm = new DBManager(c);
        mImageLoader = VolleySingleton.getInstance(ctc)
                .getImageLoader();
        inflater = LayoutInflater.from(this.ctc);
        this.pro_id=pro_id;
        this.pro_img=pro_img;
        this.pro_name=pro_name;
        this.pro_quantity=pro_quantity;
        this.pro_options=pro_options;
        this.pro_price=pro_price;
        ct4 = dbm.getFullTable();
        for (int i = 0; i < ct4.size(); i++) {
            Log.e("optionsdatabasediwww", ct4.size() + " " + ct4.get(i).getOption().toString() + "   " + ct4.get(i).getOption().toString());

            JSONObject jinnerobject2 = new JSONObject();
            optionvalue.add(ct4.get(i).getOption());
            try {
                jinnerobject2.put("diw",ct4.get(i).getOption());
                jinnerobject2.put("diw22", optionvalue.get(i));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        this.prices21=prices11;
//        this.deliver_time=deliver_time;

    }

    @Override
    public int getCount() {
//        return product_id.size();
        return pro_id.size();
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
        public TextView product_name, resultquantity, product_price, descp, delete;
        TextView tv1, tv2;
        public NetworkImageView mNetworkImageView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.itemlayoutforcart, null);
            holder = new Holder();
            convertView.setTag(holder);


        }
        else {
            holder = (Holder)convertView.getTag();
        }
        holder.product_name = (TextView)convertView.findViewById(R.id.namesss);
        holder.descp = (TextView)convertView.findViewById(R.id.descp);
        holder.delete = (TextView)convertView.findViewById(R.id.delete);
        holder.resultquantity = (TextView)convertView.findViewById(R.id.result);
        holder.product_price = (TextView)convertView.findViewById(R.id.rate);
        holder.plus = (ImageView)convertView.findViewById(R.id.plus22);
        holder.minus = (ImageView)convertView.findViewById(R.id.minus22);
        holder.plus.setTag(position);
        holder.minus.setTag(position);
            holder.product_name.setText(""+pro_name.get(position));
        holder.resultquantity.setText("" + pro_quantity.get(position));
        holder.product_price.setText(""+pro_price.get(position));
        String ds="";
        if(pro_options.get(position).size()==0){
            holder.descp.setText("");
        }
        else {
            for (int i = 0; i < pro_options.get(position).size(); i++) {

                if (i == pro_options.get(position).size() - 1) {
                    ds = ds + pro_options.get(position).get(i).value;
                } else {
                    ds = ds + pro_options.get(position).get(i).value + ",";
                }
                holder.descp.setText("" + ds);
            }
        }
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                int noofunits = (Integer.parseInt(holder.resultquantity.getText().toString()) + 1);
                holder.resultquantity.setText("" + noofunits);
                Log.e("addddd",""+optionvalue.get(pos));
                dbm.addtocart(pro_id.get(pos), holder.resultquantity.getText().toString(), optionvalue.get(pos));
                parsingforCart.parsing(ctc, CartActivity.getCartdetails()+"");
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                if(Integer.parseInt(holder.resultquantity.getText().toString())==1)
                {}
                else {
                    int noofunits = (Integer.parseInt(holder.resultquantity.getText().toString()) - 1);
                    holder.resultquantity.setText("" + noofunits);
                    dbm.addtocart(pro_id.get(pos), holder.resultquantity.getText().toString(), optionvalue.get(pos));
                    parsingforCart.parsing(ctc, "" + CartActivity.getCartdetails());

                }
            }
        });
        holder.delete.setTag(position);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int pos = (int) v.getTag();
                dbm.removeItemfromDB(ctc,pro_id.get(pos),ct4.get(pos).getOption());
            }
        });
        holder.mNetworkImageView = (NetworkImageView) convertView.findViewById(R.id.img);

        mImageLoader.get(pro_img.get(position).replace(" ", "%20"), ImageLoader.getImageListener(holder.mNetworkImageView,
                R.drawable.stub, R.drawable
                        .errorimg));
        holder.mNetworkImageView.setImageUrl(pro_img.get(position).replace(" ", "%20"), mImageLoader);


        return convertView;
    }
}


