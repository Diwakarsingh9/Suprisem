package foodorderingapp.apporio.com.suprisem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import foodorderingapp.apporio.com.suprisem.Photoinneractivity;
import foodorderingapp.apporio.com.suprisem.R;
import foodorderingapp.apporio.com.suprisem.TouchImageView;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;

/**
 * Created by saifi45 on 4/30/2016.
 */
public class Basefragmentforphotoopeninner extends Fragment {
    static String ds2;
    public  static  int y;
    TextView title, copy;
    int d;
    private ImageLoader mImageLoader;
    public  static TouchImageView img;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.baselayout22, null);

        img = (TouchImageView)root.findViewById(R.id.imageView);

        d= getArguments().getInt("msg");
        String de= getArguments().getString("msg2");
        mImageLoader = VolleySingleton.getInstance(getContext())
                .getImageLoader();
        mImageLoader.get(""+de.replace(" ","%20"), ImageLoader.getImageListener(img,
                R.drawable.stub, R.drawable
                        .errorimg));
        img.setImageUrl(""+de.replace(" ","%20"), mImageLoader);


//            title.setText(de);
//            Picasso.with(getActivity())
//                    .load("http://meetsingh.com/" + d)
//                    .placeholder(R.drawable.reall2) // optional
//                    .error(R.drawable.reaal2222l)         // optional
//                    .into(img);


        return root;
    }


    public static Basefragmentforphotoopeninner newInstance(int position, String s) {
        Basefragmentforphotoopeninner f = new Basefragmentforphotoopeninner();
        Bundle b = new Bundle();
        b.putInt("msg", position);
        b.putString("msg2", s);
        // y=ber;
        f.setArguments(b);

        return f;
    }
}

