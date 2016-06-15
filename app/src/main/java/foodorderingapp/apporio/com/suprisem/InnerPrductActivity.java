package foodorderingapp.apporio.com.suprisem;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.viewpagerindicator.CirclePageIndicator;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost2_pro_options;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_pro_options;
import foodorderingapp.apporio.com.suprisem.adapter.MyAdapter1;
import foodorderingapp.apporio.com.suprisem.directory.AlbumStorageDirFactory;
import foodorderingapp.apporio.com.suprisem.directory.BaseAlbumDirFactory;
import foodorderingapp.apporio.com.suprisem.directory.FroyoAlbumDirFactory;

public class InnerPrductActivity extends FragmentActivity {

    ViewPager pager;
    TextView textView1,buynow,selectimage,p_name, p_price,p_desc,resultquantity;
    ImageView back,camera,addtocart;
    private static int RESULT_LOAD_IMG = 1;
    public  static Bitmap bitmap1;
    private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    public static String imgDecodableString;
//    TextView Oldprice,sizetxt,colortxt,quantitytxt;
    ArrayList<String> Option_id;
    ArrayList<String> Option_values;
//    ArrayList<String> quantityarr = new ArrayList<>();
    LinearLayout sizell,colorll,quantityll;
    CirclePageIndicator titleIndicator;
    public  static FragmentManager fragmentManager;
    public static ArrayList<Innermost_all_pro_options> pro_options = new ArrayList<>();
    public static ImageView minus, plus;
    FrameLayout cartll;
    LinearLayout llforcust,llforoptions;
    String pro_id, pro_desc,pro_name,pro_price;
    ArrayList<String> imagess = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_prduct);
        fragmentManager=getSupportFragmentManager();
        pager = (ViewPager) findViewById(R.id.pager);
        back = (ImageView) findViewById(R.id.imageView2);
       // sizetxt = (TextView) findViewById(R.id.size);
        addtocart = (ImageView) findViewById(R.id.addtocart);
        buynow = (TextView) findViewById(R.id.buynow);
        plus = (ImageView) findViewById(R.id.plus);
        minus = (ImageView) findViewById(R.id.minus);
        selectimage= (TextView) findViewById(R.id.selectimggg);
        p_name = (TextView) findViewById(R.id.p_name);
        resultquantity = (TextView) findViewById(R.id.result);
        p_desc = (TextView) findViewById(R.id.p_desc);
        p_price= (TextView) findViewById(R.id.p_price);
//        quantitytxt = (TextView) findViewById(R.id.quan);
//        shipped2 = (TextView) findViewById(R.id.shipped);
        llforcust = (LinearLayout) findViewById(R.id.llforcustom);
        llforoptions = (LinearLayout) findViewById(R.id.llforoptions);
        cartll = (FrameLayout) findViewById(R.id.cartll);
        camera=(ImageView)findViewById(R.id.camera);
        //Oldprice = (TextView) findViewById(R.id.oldprice);
        sizell = (LinearLayout) findViewById(R.id.sizell);
        Option_id = new ArrayList<>();
        Option_values = new ArrayList<>();
        //colorll = (LinearLayout) findViewById(R.id.colorll);
        String intentword = getIntent().getStringExtra("act");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
        } else {
            mAlbumStorageDirFactory = new BaseAlbumDirFactory();
        }
       // if(intentword.equals("product")){
            llforcust.setVisibility(View.GONE);
        pro_id = getIntent().getStringExtra("product_id");
        pro_desc = getIntent().getStringExtra("product_descp");
        pro_name = getIntent().getStringExtra("product_name");
        pro_price = getIntent().getStringExtra("product_price");
        imagess = getIntent().getStringArrayListExtra("pro_imagess");
        pro_options = StoreCommonValues.optionpro;
        p_name.setText(""+pro_name);
        p_desc.setText(""+pro_desc);
        p_price.setText("" + pro_price);
        for (int y=0;y<pro_options.size();y++){
            Option_id.add(y,pro_options.get(y).product_option_id);
            Option_values.add(y,"null");
        }
        for (int i=0;i<pro_options.size();i++){
            llforoptions.addView(optionsadd(R.layout.itemlayoutforoptions,pro_options.get(i).name,pro_options.get(i).options,i));
        }
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultquantity.setText("" + (Integer.parseInt(resultquantity.getText().toString()) + 1));

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(resultquantity.getText().toString()) == 1) {
                } else {
                    resultquantity.setText("" + (Integer.parseInt(resultquantity.getText().toString()) - 1));

                }
            }
        });


//        }
//        else{
//            llforcust.setVisibility(View.VISIBLE);
//
//        }

        // quantityll = (LinearLayout) findViewById(R.id.quantityll);

       // shipped2.setText(Html.fromHtml("&nbsp;&nbsp;Shipped to 122001  &nbsp;"+"<font color='#0370B4'><u>Change</u></font>"));

        cartll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(InnerPrductActivity.this, CartActivity.class);
                startActivity(in);
            }
        });



//        for(int j=0;j<51;j++){
//            quantityarr.add(""+j);
//        }
//
//        sizearr.add("Size EU 35");
//        sizearr.add("Size EU 36");
//        sizearr.add("Size EU 38");
//        sizearr.add("Size EU 40");
//        sizearr.add("Size EU 42");
//
//        colorarr.add("Brown");
//        colorarr.add("Yellow");
//        colorarr.add("Green");
//        colorarr.add("Blue");
//        colorarr.add("Red");

       // Oldprice.setPaintFlags(Oldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        pager.setAdapter(new MyAdapter1(getSupportFragmentManager(), 0, imagess));
        titleIndicator= (CirclePageIndicator)findViewById(R.id.titles);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        sizell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showdialog("Size", sizearr, sizetxt);
//            }
//        });
//        colorll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showdialog("Color",colorarr,colortxt);
//            }
//        });
        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showcamerdialog();
            }
        });
        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent in = new Intent (InnerPrductActivity.this,CartActivity.class);
                startActivity(in);
            }
        });
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<Option_id.size();i++){
                    Log.e("optionvalues",""+Option_values.get(i)+" "+Option_id.get(i));
                }
                finish();
                Sub_categoryActivity.sdc.finish();
                Product_list_Activity.pdc.finish();
                Toast.makeText(InnerPrductActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
        titleIndicator.setViewPager(pager);

        titleIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private View optionsadd(int layout_name, final String title, List<Innermost2_pro_options> options, final int i) {

        LayoutInflater layoutInflater =
                (LayoutInflater) InnerPrductActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);
        final TextView tv = (TextView) addView.findViewById(R.id.titleoption);
        tv.setText("Select "+title);
        addView.setTag(i);

        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                ArrayList<String> pro_options_names = new ArrayList<>();
                ArrayList<String> pro_options_id = new ArrayList<>();
                for (int y = 0; y < pro_options.get(pos).options.size(); y++) {
                    pro_options_names.add(pro_options.get(pos).options.get(y).namess);
                    pro_options_id.add(pro_options.get(pos).options.get(y).product_option_value_id);
                }
                showdialog("Select "+title, pro_options_names, tv,pos,pro_options_id);
            }
        });





        return addView;
    }

        private void showdialog(final String title, ArrayList<String> itemss, TextView textView, final int pos, ArrayList<String> pro_options_id) {

            this.textView1 = textView;


            String[] myArray = itemss.toArray(new String[itemss.size()]);

        new MaterialDialog.Builder(this)
                .title(title)
                .items(myArray)



                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                        if (text != (null)) {
                            Option_values.add(pos,"null");
                            textView1.setText("" + text);
                        } else {

                            Option_values.add(pos,Option_id.get(which));

                            textView1.setText(""+title);
                        }
                        // textsize.setText("" + text);
                        // SellOnCMTCOnstants.brandids =  brand_id_list.get(size.indexOf(text));

                        return true;
                    }
                })
                .widgetColor(Color.parseColor("#000000"))

                .widgetColorRes(R.color.black)
                .positiveText("Ok")
                .show();
    }

    public  void showcamerdialog() {

        final Dialog dialog = new Dialog(InnerPrductActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.camerdialog);
        Button cancel = (Button) dialog.findViewById(R.id.button1);
        LinearLayout button = (LinearLayout) dialog.findViewById(R.id.layout12);
        LinearLayout button1 = (LinearLayout) dialog.findViewById(R.id.layout13);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dispatchTakePictureIntent(11);


            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void dispatchTakePictureIntent(int actionCode) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        switch(actionCode) {
            case 11:
                File f = null;

                try {
                    f = setUpPhotoFile();
                    imgDecodableString = f.getAbsolutePath();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                } catch (IOException e) {
                    e.printStackTrace();
                    f = null;
                    imgDecodableString = null;
                }
                break;

            default:
                break;
        } // switch

        startActivityForResult(takePictureIntent, 11);
    }
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(imgDecodableString);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        InnerPrductActivity.this.sendBroadcast(mediaScanIntent);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //Toast.makeText(getActivity(),""+requestCode+" "+resultCode,Toast.LENGTH_SHORT).show();
            switch(requestCode){
                case 11:
                    if(resultCode!=0){

                        // Profileactivity.intentproceed=11;
                        handleBigCameraPhoto();
                    }
                case 1:
                    if (requestCode == RESULT_LOAD_IMG && resultCode != 0
                            && null != data) {
                        // Get the Image from data
                        // MainActivity.intentproceed=1;
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        // Get the cursor
                        Cursor cursor = InnerPrductActivity.this.getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imgDecodableString = cursor.getString(columnIndex);
                        cursor.close();
                        //img.setImageResource(android.R.color.transparent);
                        // Set the Image in ImageView after decoding the String
                        camera.setImageBitmap(BitmapFactory
                                .decodeFile(imgDecodableString));
                        camera.setScaleType(ImageView.ScaleType.FIT_XY);

                    }
                default:{
                    // Toast.makeText(getActivity(), "You haven't picked Image",Toast.LENGTH_LONG).show();
                }
            }

            // When an Image is picked
            // Toast.makeText(getActivity(),""+requestCode+" "+resultCode,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(InnerPrductActivity.this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    private String getAlbumName() {
        return getString(R.string.album_name);
    }
    private void handleBigCameraPhoto() {

        if (imgDecodableString != null) {
            galleryAddPic();
            setPic();

            imgDecodableString = null;
        }

    }

    private void setPic() {

		/* There isn't enough memory to open up more than a couple camera photos */
		/* So pre-scale the target bitmap into which the file is decoded */

		/* Get the size of the ImageView */
        int targetW =200;
        int targetH = 300;


		/* Get the size of the image */
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imgDecodableString, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

		/* Figure out which way needs to be reduced less */
        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        }

		/* Set bitmap options to scale the image decode target */
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

		/* Decode the JPEG file into a Bitmap */
        bitmap1 = BitmapFactory.decodeFile(imgDecodableString, bmOptions);
        Log.e("bitmap", "" + bitmap1);
        camera.setImageBitmap(bitmap1);

       		/* Associate the Bitmap to the ImageView */

    }


    private File getAlbumDir() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());

            if (storageDir != null) {
                if (! storageDir.mkdirs()) {
                    if (! storageDir.exists()){
                        Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }

        } else {
            Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
        }

        return storageDir;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
        File albumF = getAlbumDir();
        File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
        return imageF;
    }

    private File setUpPhotoFile() throws IOException {

        File f = createImageFile();
        imgDecodableString = f.getAbsolutePath();

        return f;
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = InnerPrductActivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(InnerPrductActivity.this.getResources().getColor(R.color.statusbarcolor));
        } else {
            Window window = InnerPrductActivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
