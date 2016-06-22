package foodorderingapp.apporio.com.suprisem;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Handler;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;

import foodorderingapp.apporio.com.suprisem.Database.DBManager;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforMain_featuredlist;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforcountry;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforfiltering;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Innermost_all_pro_options;
import foodorderingapp.apporio.com.suprisem.adapter.SampleAdapter;
import foodorderingapp.apporio.com.suprisem.fragment.CATEGORIESfragment;
import foodorderingapp.apporio.com.suprisem.fragment.Favfragment;
import foodorderingapp.apporio.com.suprisem.fragment.Mainfragment;
import views.ProgressWheel;

public class MainActivity extends AppCompatActivity {
    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;
    public static String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];

    String data[]={"Apple","Bingo","Charlie","Asus","Ample","Amplifier","Bag","Chain Lock"};
    public static ProgressWheel pb2233;
    public static ListView lv4;
    private String name;        //String Resource for header View Name
    private int profile ;        //int Resource for header view profile picture
    private String email;       //String Resource for header view email
    Fragment fragment = null;
    public static Context ctc2;
    FrameLayout cartll;
    ImageView search;
    public static Context ctc;
    private static String mCurrentPhotoPath;
    public static String NAME = "";
    public static String EMAIL = "";
    public static String PROFILE = "";
    LinearLayout llfrhome, llforcategories, llforfav;
    ImageView homeic, catic, favic;
        TextView totlitem;
    public static ArrayAdapter adp;
    private android.support.v7.widget.Toolbar toolbar;                              // Declaring the Toolbar Object
    public static String TITLES[] = {"Home","Categories","Cart"
            ,"My Favourites","Share the App","FAQ","Rate the App",
            "About","Contact Us","Partner","Log Out"};
    public static int ICONS[] = {

            R.drawable.home,
            R.drawable.category,
            R.drawable.cartblck,
            R.drawable.fav,
            R.drawable.refer,
            R.drawable.help,
            R.drawable.rate,
            R.drawable.about,
            R.drawable.contact,
            R.drawable.partner,
            R.drawable.logout};
    public static ArrayList<String> prod_img = new ArrayList<String>();
    public static ArrayList<Innermost_all_pro_options> pro_options = new ArrayList<>();
    ActionBarDrawerToggle mDrawerToggle;
    static DBManager dbm;
    public TextView head;
    public static RecyclerView mRecyclerView;                           // Declaring RecyclerView
    public static RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    public static RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    public static DrawerLayout Drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        cartll = (FrameLayout) findViewById(R.id.cartll);
        head = (TextView) findViewById(R.id.header);
        search = (ImageView) findViewById(R.id.sear);
        totlitem = (TextView) findViewById(R.id.total_item);
        homeic = (ImageView) findViewById(R.id.homeic);
        catic = (ImageView) findViewById(R.id.categoryic);
        favic = (ImageView) findViewById(R.id.favic);
        llfrhome = (LinearLayout) findViewById(R.id.homell);
        llforcategories = (LinearLayout) findViewById(R.id.categoryll);
        llforfav = (LinearLayout) findViewById(R.id.favll);
        dbm = new DBManager(MainActivity.this);
       // dbm.clearCartTable();
        toolbar.setTitle("");
        totlitem.setText(""+dbm.getFullTable().size());
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);
        homeic.setImageResource(R.drawable.homegreen);
        catic.setImageResource(R.drawable.category);
        favic.setImageResource(R.drawable.fav);
        fragment = new Mainfragment();
        if (fragment != null) {
            head.setText("SURPRISEM");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

        cartll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, CartActivity.class);
                startActivity(in);
            }
        });
        llfrhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new Mainfragment();
                homeic.setImageResource(R.drawable.homegreen);
                catic.setImageResource(R.drawable.category);
                favic.setImageResource(R.drawable.fav);
//                llfrhome.setBackgroundColor(Color.parseColor("#333333"));
//                llforfav.setBackgroundColor(Color.parseColor("#ffffff"));
//                llforcategories.setBackgroundColor(Color.parseColor("#ffffff"));
                if (fragment != null) {
                    head.setText("SURPRISEM");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
            }
        });
        llforcategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new CATEGORIESfragment();
                homeic.setImageResource(R.drawable.home);
                catic.setImageResource(R.drawable.categorygreen);
                favic.setImageResource(R.drawable.fav);
//                llfrhome.setBackgroundColor(Color.parseColor("#ffffff"));
//                llforfav.setBackgroundColor(Color.parseColor("#ffffff"));
//                llforcategories.setBackgroundColor(Color.parseColor("#333333"));
                if (fragment != null) {
                    head.setText("CATEGORIES");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
            }
        });
        llforfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new Favfragment();
                homeic.setImageResource(R.drawable.home);
                catic.setImageResource(R.drawable.category);
                favic.setImageResource(R.drawable.favouritesgreen);
//                llfrhome.setBackgroundColor(Color.parseColor("#ffffff"));
//                llforfav.setBackgroundColor(Color.parseColor("#333333"));
//                llforcategories.setBackgroundColor(Color.parseColor("#ffffff"));
                if (fragment != null) {
                    head.setText("FAVOURITES");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showsearchdialog();
            }
        });
        // do we have saved data?



        mAdapter = new MyAdapter(MainActivity.this, TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.drawer);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, Drawer, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        };

        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Drawer.openDrawer(Gravity.LEFT);
            }
        });

    }




    public  void showsearchdialog() {

        final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Translucent);

        //  dialog.getWindow().setStatusBarColor(Loginscreenactivity.this.getResources().getColor(R.color.example_color));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforsearch);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
       ImageView close = (ImageView)dialog.findViewById(R.id.closed);
        final EditText  searchedt = (EditText)dialog. findViewById(R.id.searchedt);
         lv4 = (ListView)dialog. findViewById(R.id.lvsearch);
        pb2233 = (ProgressWheel)dialog.findViewById(R.id.pb2233);
        lv4.setTextFilterEnabled(true);
        lv4.setVisibility(View.GONE);
        try {

//          adp = new ArrayAdapter(MainActivity.this, R.layout.layoutforsearchfinder, R.id.textView10, data);
//            lv4.setAdapter(adp);


            searchedt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if(searchedt.getText().toString().equals(""))
                    {
                        lv4.setVisibility(View.GONE);
                    }
                    else{
                        lv4.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    MainActivity.this.adp.getFilter().filter(s);

                    parsingforfiltering.parsing(MainActivity.this,s+"");
                    if(searchedt.getText().toString().equals(""))
                    {
                        lv4.setVisibility(View.GONE);
                    }
                    else{
                        lv4.setVisibility(View.VISIBLE);

                    }

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(searchedt.getText().toString().equals(""))
                    {
                        lv4.setVisibility(View.GONE);
                    }
                    else{
                        lv4.setVisibility(View.VISIBLE);

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        lv4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                prod_img.clear();
                pro_options.clear();
                StoreCommonValues.optionpro.clear();
                for(int j=0;j< parsingforfiltering.pro_imagess.get(position).size();j++){
                    prod_img.add(parsingforfiltering.pro_imagess.get(position).get(j).image);
                }
                for(int j=0;j< parsingforfiltering.pro_options.get(position).size();j++){
                    pro_options.add(parsingforfiltering.pro_options.get(position).get(j));
                }
                Intent i = new Intent(MainActivity.this, InnerPrductActivity.class);
                i.putExtra("act","search");
                i.putExtra("product_id", parsingforfiltering.pro_id.get(position));
                i.putExtra("product_price", parsingforfiltering.pro_price.get(position));
                i.putExtra("product_descp", parsingforfiltering.pro_desc.get(position));
                i.putExtra("product_name", parsingforfiltering.pro_name.get(position));
                i.putStringArrayListExtra("pro_imagess", prod_img);
                StoreCommonValues.optionpro = pro_options;
                startActivity(i);

            }
        });

        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        totlitem.setText(""+dbm.getFullTable().size());

        mAdapter = new MyAdapter(MainActivity.this, TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)

        mRecyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        // Creating a ViewHolder which extends the RecyclerView View Holder
        // ViewHolder are used to to store the inflated views in order to recycle them

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            int Holderid;

            TextView textView;
            ImageView imageView;
            public ImageView profile11;
            public TextView Name;
            TextView email;
            FrameLayout llforprof;
            LinearLayout itemll;

            public ViewHolder(View itemView, int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
                super(itemView);


                // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

                if (ViewType == TYPE_ITEM) {
                    textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
                    imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                    itemll = (LinearLayout) itemView.findViewById(R.id.llfornavi);
                    itemll.setOnClickListener(this);
                    // Creating ImageView object with the id of ImageView from item_row.xml
                    Holderid = 1;

                    // setting holder id as 1 as the object being populated are of type item row
                } else {

                    // Creating Text View object from header.xml for email

                    profile11 = (ImageView) itemView.findViewById(R.id.imageViewcc);
                    llforprof = (FrameLayout) itemView.findViewById(R.id.llforprofile);
                    profile11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.Drawer.closeDrawer(Gravity.LEFT);
                            //  showcamerdialog();
                        }
                    });
                    llforprof.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent in = new Intent(ctc2, Profileactivity.class);
//                            ctc2.startActivity(in);
                        }
                    });

                    // Creating Image view object from header.xml for profile pic
                    Holderid = 0;
                }
            }


            @Override
            public void onClick(View v) {
                try {

                    if (mNavTitles[getPosition() - 1].equals("Home")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                                fragment = new Mainfragment();
                                homeic.setImageResource(R.drawable.homegreen);
                                catic.setImageResource(R.drawable.category);
                                favic.setImageResource(R.drawable.fav);
                                if (fragment != null) {
                                    head.setText("SURPRISEM");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }
                            }
                            //startThread();
                        }
                                , 200);

                    }
                    else if (mNavTitles[getPosition() - 1].equals("Categories")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                                fragment = new CATEGORIESfragment();
                                homeic.setImageResource(R.drawable.home);
                                catic.setImageResource(R.drawable.categorygreen);
                                favic.setImageResource(R.drawable.fav);
                                if (fragment != null) {
                                    head.setText("CATEGORIES");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }
                            }
                            //startThread();
                        }
                                , 200);


                    }
                    else if (mNavTitles[getPosition() - 1].equals("Cart")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                                Intent i = new Intent(MainActivity.this, CartActivity.class);
                                startActivity(i);
                            }
                            //startThread();
                        }
                                , 200);
                    }


                    else if (mNavTitles[getPosition() - 1].equals("My Favourites")) {
                        Drawer.closeDrawer(Gravity.LEFT);

                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                                fragment = new Favfragment();
                                homeic.setImageResource(R.drawable.home);
                                catic.setImageResource(R.drawable.category);
                                favic.setImageResource(R.drawable.favouritesgreen);
                                if (fragment != null) {
                                    head.setText("FAVOURITES");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }
                            }
                            //startThread();
                        }
                                , 200);

                    }
                    else if (mNavTitles[getPosition() - 1].equals("Share the App")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        sendSMS();
                    }
                    else if (mNavTitles[getPosition() - 1].equals("FAQ")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                        Intent i = new Intent(MainActivity.this, Faq_Activity.class);
                        startActivity(i);
                            }
                            //startThread();
                        }
                                , 200);

                    }
                    else if (mNavTitles[getPosition() - 1].equals("Rate the App")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        rateApp("" + getApplicationContext().getPackageName());

                    }
                    else if (mNavTitles[getPosition() - 1].equals("About")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                                Intent i = new Intent(MainActivity.this, AboutActivity.class);
                                startActivity(i);
                            }
                            //startThread();
                        }
                                , 200);

                    }
                    else if (mNavTitles[getPosition() - 1].equals("Contact Us")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                                Intent i = new Intent(MainActivity.this, ContactsActivity.class);
                                startActivity(i);
                            }
                            //startThread();
                        }
                                , 200);

                    }
                    else if (mNavTitles[getPosition() - 1].equals("Partner")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                                Intent i = new Intent(MainActivity.this, Partnerwithus.class);
                                startActivity(i);
                            }
                            //startThread();
                        }
                                , 200);

                    }
                    else if (mNavTitles[getPosition() - 1].equals("Log Out")) {
                        Drawer.closeDrawer(Gravity.LEFT);

                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {

//                               new MaterialDialog.Builder(MainActivity.this)
//                                        .title("Exit")
//                                        .content("Are you sure want to exit?")
//                                        .positiveText("Ok")
//                                        .show();
//
//                               new MaterialDialog.Builder(MainActivity.this)
//                                        .callback(new MaterialDialog.ButtonCallback() {
//                                            @Override
//                                            public void onPositive(MaterialDialog dialog) {
//                                                finish();
//                                            }
//                                        });
                                new AlertDialogWrapper.Builder(MainActivity.this)
                                        .setTitle("EXIT")
                                        .setMessage("Are you sure want to exit?")
                                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        }).show();

                            }
                            //startThread();
                        }
                                , 200);
                    }
                    else{
                        //
                    }
                }catch (Exception e){
                    Log.e("ddddd", "" + e);
                }
            }

        }

        private void sendSMS() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) // At least KitKat
            {
                String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(MainActivity.this); // Need to change the build to API 19

                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out Surprisem for your smartphone. Download it today from play store");

                if (defaultSmsPackageName != null)// Can be null in case that there is no default, then the user would be able to choose
                // any app that support this intent.
                {
                    sendIntent.setPackage(defaultSmsPackageName);
                }
                startActivity(sendIntent);

            }
            else // For early versions, do what worked for you before.
            {
                Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("sms_body","Check out Surprisem for your smartphone. Download it today from play store");
                startActivity(smsIntent);
            }
        }

        public void rateApp(String packageName) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + packageName)));
            }
            catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
            }
        }

        public MyAdapter(MainActivity mainActivity, String Titles[], int Icons[], String Name, String Email, String Profile){ // MyAdapter Constructor with titles and icons parameter
            // titles, icons, name, email, profile pic are passed from the main activity as we
            mNavTitles = Titles;                //have seen earlier
            mIcons = Icons;
            name = Name;
            email = Email;
            //profile = Profile;
            ctc2= mainActivity;
            //here we assign those passed values to the values we declared here
            //in adapter


        }



        //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
        //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
        // if the viewType is TYPE_HEADER
        // and pass it to the view holder

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == TYPE_ITEM) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false); //Inflating the layout

                ViewHolder vhItem = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhItem; // Returning the created object

                //inflate your layout and pass it to view holder

            } else if (viewType == TYPE_HEADER) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false); //Inflating the layout

                ViewHolder vhHeader = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhHeader; //returning the object created


            }
            return null;

        }

        //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
        // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
        // which view type is being created 1 for item row
        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
            if(holder.Holderid ==1) {                              // as the list view is going to be called after the header view so we decrement the
                // position by 1 and pass it to the holder while setting the text and image
                holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
                holder.imageView.setImageResource(mIcons[position -1]);// Settimg the image with array of our icons
            }
            else{
//                Picasso.with(MainActivity.this)
//                        .load("http://www.wscubetechapps.in/mobileteam/OneTapTakeway_app/" + profile)
//                        .placeholder(R.drawable.download) // optional
//                        .error(R.drawable.download)         // optional
//                        .into(holder.profile11);
                //  holder.profile11.setImageBitmap(bitmap1);           // Similarly we set the resources for header view
//                holder.Name.setText(name);

            }

        }

        // This method returns the number of items present in the list
        @Override
        public int getItemCount() {
            return mNavTitles.length+1; // the number of items in the list will be +1 the titles including the header view.
        }


        // Witht the following method we check what type of view is being passed
        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;

            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }

    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = MainActivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(MainActivity.this.getResources().getColor(R.color.statusbarcolor));
        } else {
            Window window = MainActivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
