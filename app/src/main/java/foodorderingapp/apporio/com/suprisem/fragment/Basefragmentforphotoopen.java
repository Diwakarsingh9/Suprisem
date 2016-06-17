package foodorderingapp.apporio.com.suprisem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.Photoinneractivity;
import foodorderingapp.apporio.com.suprisem.R;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;


/**
 * Created by saifi45 on 8/9/2015.
 */

    public class Basefragmentforphotoopen extends Fragment {
        static String ds2;
        public  static  int y;
    TextView title, copy;
    int d;
    private ImageLoader mImageLoader;
    ArrayList<String> photos = new ArrayList<>();

public  static NetworkImageView img;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layoutforphotoopen, null);

           img = (NetworkImageView)root.findViewById(R.id.imageView);

            d= getArguments().getInt("msg");
            photos = getArguments().getStringArrayList("msg3");
            String de= getArguments().getString("msg2");
            mImageLoader = VolleySingleton.getInstance(getContext())
                    .getImageLoader();
            mImageLoader.get(""+de.replace(" ","%20"), ImageLoader.getImageListener(img,
                    R.drawable.stub, R.drawable
                            .errorimg));
            img.setImageUrl(""+de.replace(" ","%20"), mImageLoader);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getActivity(),Photoinneractivity.class);
                    in.putExtra("position", d);
                    in.putStringArrayListExtra("list",photos);
                    startActivity(in);
                    //Toast.makeText(getActivity(), ""+d, Toast.LENGTH_SHORT).show();
                }
            });

//            title.setText(de);
//            Picasso.with(getActivity())
//                    .load("http://meetsingh.com/" + d)
//                    .placeholder(R.drawable.reall2) // optional
//                    .error(R.drawable.reaal2222l)         // optional
//                    .into(img);


            return root;
        }


        public static Basefragmentforphotoopen newInstance(int position, String s, ArrayList<String> farray) {
            Basefragmentforphotoopen f = new Basefragmentforphotoopen();
            Bundle b = new Bundle();
            b.putInt("msg", position);
            b.putString("msg2", s);
            b.putStringArrayList("msg3", farray);
            // y=ber;
            f.setArguments(b);

            return f;
        }
    }

