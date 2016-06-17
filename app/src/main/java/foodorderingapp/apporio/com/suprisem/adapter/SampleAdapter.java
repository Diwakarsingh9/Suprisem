package foodorderingapp.apporio.com.suprisem.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.etsy.android.grid.util.DynamicHeightTextView;

import java.util.ArrayList;
import java.util.Random;

import foodorderingapp.apporio.com.suprisem.R;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;

/**
 * Created by saifi45 on 4/29/2016.
 */
public class SampleAdapter extends BaseAdapter {

    private static final String TAG = "SampleAdapter";

    static class ViewHolder {
       TextView txtLineOne;
        NetworkImageView img;

    }
    private ImageLoader mImageLoader;
    private final LayoutInflater mLayoutInflater;

    ArrayList<String> mBackgroundColors = new ArrayList<String>();;
     ArrayList<String>  titlenames = new ArrayList<String>();

    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();

    public SampleAdapter(final Context context, ArrayList<String> pro_name, ArrayList<String> pro_img) {

        mLayoutInflater = LayoutInflater.from(context);


        mImageLoader = VolleySingleton.getInstance(context)
                .getImageLoader();
        this.titlenames = pro_name;
       this.mBackgroundColors = pro_img;
        Log.e("fffffff",""+titlenames);
    }

    @Override
    public int getCount() {
        return titlenames.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.itemlayoutformainlist, parent, false);
            vh = new ViewHolder();
            vh.txtLineOne = (TextView) convertView.findViewById(R.id.textline_1);
            vh.img = (NetworkImageView) convertView.findViewById(R.id.photoss);


            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }



        mImageLoader.get(mBackgroundColors.get(position).replace(" ", "%20"), ImageLoader.getImageListener(vh.img,
                R.drawable.stub, R.drawable
                        .errorimg));
        vh.img.setImageUrl(mBackgroundColors.get(position).replace(" ","%20"), mImageLoader);

        vh.txtLineOne.setText(titlenames.get(position));



        return convertView;
    }


}