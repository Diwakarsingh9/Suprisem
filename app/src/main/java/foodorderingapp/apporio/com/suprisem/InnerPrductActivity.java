package foodorderingapp.apporio.com.suprisem;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import foodorderingapp.apporio.com.suprisem.Api_s.Apis_url;
import foodorderingapp.apporio.com.suprisem.Database.DBManager;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Checksetter_getter;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Imageupload;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost2_pro_options;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_pro_options;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Login_outter;
import foodorderingapp.apporio.com.suprisem.adapter.MyAdapter1;
import foodorderingapp.apporio.com.suprisem.directory.AlbumStorageDirFactory;
import foodorderingapp.apporio.com.suprisem.directory.BaseAlbumDirFactory;
import foodorderingapp.apporio.com.suprisem.directory.FroyoAlbumDirFactory;
import foodorderingapp.apporio.com.suprisem.singleton.VolleySingleton;
import views.ProgressWheel;

public class InnerPrductActivity extends FragmentActivity {

    ViewPager pager;
    TextView textView1,buynow,p_name, p_price,p_desc,resultquantity,textcustom1,textcustomimg1;
    LinearLayout back;
    public  static StringRequest sr;
    public  static RequestQueue queue;
    ImageView addtocart;
    Dialog dialog;
    ProgressWheel pb22;
    private static int RESULT_LOAD_IMG = 1;
    public  static Bitmap bitmap1;
    private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    public static String imgDecodableString="";
//    TextView Oldprice,sizetxt,colortxt,quantitytxt;
    ArrayList<String> Option_id;
    ArrayList<String> Option_values;
    static DBManager dbm;
    TextView totlitem;
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
    ArrayList<String> pro_options_id2 ;
    ImageView camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_prduct);
        fragmentManager=getSupportFragmentManager();
        pager = (ViewPager) findViewById(R.id.pager);
        back = (LinearLayout) findViewById(R.id.llforback);
        totlitem = (TextView) findViewById(R.id.total_item);
       // sizetxt = (TextView) findViewById(R.id.size);
        addtocart = (ImageView) findViewById(R.id.addtocart);
        buynow = (TextView) findViewById(R.id.buynow);
        plus = (ImageView) findViewById(R.id.plus);
        minus = (ImageView) findViewById(R.id.minus);

        p_name = (TextView) findViewById(R.id.p_name);
        resultquantity = (TextView) findViewById(R.id.result);
        p_desc = (TextView) findViewById(R.id.p_desc);
        p_price= (TextView) findViewById(R.id.p_price);
//        quantitytxt = (TextView) findViewById(R.id.quan);
//        shipped2 = (TextView) findViewById(R.id.shipped);
       // llforcust = (LinearLayout) findViewById(R.id.llforcustom);
        llforoptions = (LinearLayout) findViewById(R.id.llforoptions);
        cartll = (FrameLayout) findViewById(R.id.cartll);

        //Oldprice = (TextView) findViewById(R.id.oldprice);
        sizell = (LinearLayout) findViewById(R.id.sizell);
        dbm = new DBManager(InnerPrductActivity.this);

        Option_id = new ArrayList<>();
        Option_values = new ArrayList<>();
        //colorll = (LinearLayout) findViewById(R.id.colorll);
        final String intentword = getIntent().getStringExtra("act");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
        } else {
            mAlbumStorageDirFactory = new BaseAlbumDirFactory();
        }
//        if(intentword.equals("product")){
//            llforcust.setVisibility(View.GONE);
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

        cartll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(InnerPrductActivity.this, CartActivity.class);
                startActivity(in);
            }
        });

        pager.setAdapter(new MyAdapter1(getSupportFragmentManager(), 0, imagess));
        titleIndicator= (CirclePageIndicator)findViewById(R.id.titles);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                String optionvaluesss = "";
                for(int i=0;i<Option_id.size();i++){
                    if(Option_values.get(i).equals("null")){

                    }
                    else {
                        if (Option_id.size() - 1 == i) {
                            optionvaluesss = optionvaluesss.concat(Option_id.get(i) + "\":\"" + Option_values.get(i));

                        } else {
                            optionvaluesss = optionvaluesss.concat(Option_id.get(i) + "\":\"" + Option_values.get(i) + "\",\"");
                        }
                    }

                    Log.e("optionvalues", "" + Option_id.get(i) + " " + Option_values.get(i));

                }
                if(optionvaluesss.endsWith("\",\"")) {

                    optionvaluesss= optionvaluesss.substring(0, optionvaluesss.length() - 3);
                }
                dbm.addtocart(pro_id , resultquantity.getText().toString() , optionvaluesss);
                finish();
                if(intentword.equals("category")) {
                    Sub_categoryActivity.sdc.finish();
                    Product_list_Activity.pdc.finish();
                }
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
    @Override
    protected void onResume() {
        super.onResume();
        totlitem.setText("" + dbm.getFullTable().size());
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
                    Log.e("gfgf", pro_options_id.get(y));

                }
                if (title.equals("File")) {
                    imgDecodableString = "";
                    showoptioncameradialog(tv,pos);
                } else if (title.equals("Text")) {
                    showoptiontextdialog(tv,pos);
                } else {
                    showdialog("Select " + title, pro_options_names,
                            tv, pos, pro_options_id);
                }
            }
        });





        return addView;
    }

        private void showdialog(final String title, ArrayList<String> itemss, TextView textView, final int pos, final ArrayList<String> pro_options_id) {

            this.textView1 = textView;

            this.pro_options_id2=pro_options_id;

            String[] myArray = itemss.toArray(new String[itemss.size()]);

        new MaterialDialog.Builder(this)
                .title(title)
                .items(myArray)



                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                        if (text != (null)) {

                            Option_values.set(pos, pro_options_id2.get(which));
                            textView1.setText("" + text);
                        } else {
                            Option_values.set(pos, "null");
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

    public  void showoptioncameradialog(TextView tv, final int pos) {
        this.textcustomimg1 = tv;
       dialog = new Dialog(InnerPrductActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforphoto);
        TextView cancel = (TextView) dialog.findViewById(R.id.cncl);
        TextView ok = (TextView) dialog.findViewById(R.id.okkkkk);
         camera = (ImageView) dialog.findViewById(R.id.camera);
        pb22 = (ProgressWheel)dialog.findViewById(R.id.pbd);
        TextView selctimg = (TextView)dialog.findViewById(R.id.selectimggg);


        selctimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showcamerdialog();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(!imgDecodableString.equals("")) {
                    parsingforimageupload(InnerPrductActivity.this,imgDecodableString,
                            textcustomimg1,pos);
                }
                else {
                    Toast.makeText(InnerPrductActivity.this, "No image selected !!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                imgDecodableString="";
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public  void showoptiontextdialog(TextView tv, final int pos) {
        this.textcustom1 = tv;
        final Dialog dialog = new Dialog(InnerPrductActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogfortext);
        TextView cancel = (TextView) dialog.findViewById(R.id.cncl);
        TextView ok = (TextView) dialog.findViewById(R.id.okkkkk);
        final EditText selctimg = (EditText)dialog.findViewById(R.id.textwewe);



        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                textcustom1.setText("Customised Text Done");
                Option_values.set(pos, "" + selctimg.getText().toString().trim());
                dialog.dismiss();
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


    private void parsingforimageupload(final Context c, String imgDecodableString, final TextView textcustomimg1, final int pos) {
        String body = "{\"image"+"\":\""+imgDecodableString+"\"}";
        Log.e("bodydd", "" + body);

        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.imageupload;
        final String mRequestBody = body.toString();
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, urlforRest_food,mRequestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                pb22.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    Checksetter_getter rec = new Checksetter_getter();
                    rec = gson.fromJson(""+response,Checksetter_getter.class);
                    if(rec.status.equals("success")) {

                        Imageupload received2 = new Imageupload();
                        received2 = gson.fromJson(""+response, Imageupload.class);

                        if (received2.status.equals("success")) {
//                        product_names=received2.customer;
                            textcustomimg1.setText("Customised Image Successfully Done");
                            Option_values.set(pos,""+received2.Code );
                            dialog.dismiss();
                        } else {

                            pb22.setVisibility(View.GONE);


                        }
                    }
                    else {
                        pb22.setVisibility(View.GONE);
                        Toast.makeText(c, ""+rec.message, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                CartActivity.pb22.setVisibility(View.GONE);
//                Menufragment.pb.setVisibility(View.GONE);
                Log.e("Sucess", "" + error.toString());
                // Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();

            }

        })


        {


            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(sr);
        pb22.setVisibility(View.VISIBLE);
    }

}

