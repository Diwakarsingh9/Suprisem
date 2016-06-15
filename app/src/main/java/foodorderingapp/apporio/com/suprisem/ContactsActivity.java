package foodorderingapp.apporio.com.suprisem;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import foodorderingapp.apporio.com.suprisem.adapter.PartnerwithusAdapter;

public class ContactsActivity extends Activity {
    ImageView back, callus, mailus;
    FrameLayout cartll;


    private static final int MY_PERMISSIONS_REQUEST_Call_Contacts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        back = (ImageView) findViewById(R.id.imageView2);
        callus = (ImageView) findViewById(R.id.call);
        mailus = (ImageView) findViewById(R.id.mail);
        cartll = (FrameLayout) findViewById(R.id.cartll);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        callus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String permission = "android.permission.CALL_PHONE";

                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        try {


                            int permissionCheck = ContextCompat.checkSelfPermission(ContactsActivity.this,
                                    Manifest.permission.CALL_PHONE);

                            if (ContextCompat.checkSelfPermission(ContactsActivity.this,
                                    Manifest.permission.CALL_PHONE)
                                    != PackageManager.PERMISSION_GRANTED) {

                                // Should we show an explanation?
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactsActivity.this,
                                        Manifest.permission.CALL_PHONE)) {
                                    Toast.makeText(getApplicationContext(), "Please allow phone permission to make calls !!!", Toast.LENGTH_LONG).show();
                                    // Show an expanation to the user *asynchronously* -- don't block
                                    // this thread waiting for the user's response! After the user
                                    // sees the explanation, try again to request the permission.


                                } else {

                                    // No explanation needed, we can request the permission.

                                    ActivityCompat.requestPermissions(ContactsActivity.this,
                                            new String[]{Manifest.permission.READ_PHONE_STATE},
                                            MY_PERMISSIONS_REQUEST_Call_Contacts);

                                    String posted_by = "0000000000";
                                    String uri = "tel:" + posted_by.trim();
                                    Intent intent = new Intent(Intent.ACTION_CALL);
                                    intent.setData(Uri.parse(uri));
                                    startActivity(intent);

                                }
                            } else {
                                String posted_by = "0000000000";
                                String uri = "tel:" + posted_by.trim();
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse(uri));
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            Log.e("ghghghhg", "" + e);
                        }
                    } else {
                        String posted_by = "0000000000";
                        String uri = "tel:" + posted_by.trim();
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);
                    }

                    // parsingfornotification.parsing(MainActivity.this,regId,dd);

                }
                else {
                    String posted_by = "0000000000";
                    String uri = "tel:" + posted_by.trim();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                }


            }


        });
        mailus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
//                        "mailto", ""+prefs.getString("adminemail","amit.s@tyretimes.com"), null));
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "Surprisem_demo@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Surprisem App");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                emailIntent.setType("text/plain");


            }
        });
        cartll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ContactsActivity.this, CartActivity.class);
                startActivity(in);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_Call_Contacts: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    String posted_by = "0000000000";
                    String uri = "tel:" + posted_by.trim();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(uri));
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }


        return;
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = ContactsActivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContactsActivity.this.getResources().getColor(R.color.statusbarcolor));
        } else {
            Window window = ContactsActivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
