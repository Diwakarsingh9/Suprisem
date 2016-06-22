package foodorderingapp.apporio.com.suprisem.Database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import foodorderingapp.apporio.com.suprisem.CartActivity;
import foodorderingapp.apporio.com.suprisem.Parsing.parsingforCart;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by samir on 10/07/15.
 */
public class DBManager {

    public static CartTable ct ;

    public  Realm myRealm;

    public static Context con ;

   // public static EventBus bus = EventBus.getDefault() ;

    public DBManager(Context con){
        this.con = con ;

        myRealm = Realm.getInstance(con);
    }

    public   void addtocart(String productId,  String quantity
            , String optionsValue ){

        if(checkproductExsistance(productId,quantity,optionsValue)){
            ////change already saved details in database
            changeExsistingRowintable(productId,quantity,optionsValue);
        }else {
            /////  create new row in database
            createnreRowintable( productId,quantity,optionsValue);
        }

    }


    private  void createnreRowintable(String productId, String quantity, String optionsvalue) {
        Log.e("hfdhg","nounits   ---"+""+quantity);
//        new CartTable(ProductId,
//                rest_id,
//                food_name,
//                foodtype,
//                foodprice,foodimage,foodrating,food_no_of_units,toppingprice).save();
//
//        updateTotalOnActionBar();
//        showdataoncart();

        myRealm.beginTransaction();
        CartTable pd = myRealm.createObject(CartTable.class);

        pd.setproductid(productId);
        pd.setQuantity(quantity);
        pd.setOption(optionsvalue);
        myRealm.commitTransaction();
       // countNoofRowsInDatabse();

    }

    private static void updateTotalOnActionBar() {
       /* if(ActivityDetector.openActivity.equals("MainActivity")){
            MainActivity.showgrossOnCart(calculationForGrossPrice());
        }if(ActivityDetector.openActivity.equals("CartActivity")){
            CartActivity.showgrossOnCartCartActivity(calculationForGrossPrice());
        }   */


    }




    public  void changeExsistingRowintable(String productid, String quantity, String optionsvalue) {
        Log.e("hfdhg", "nounits   ---" + quantity);
        CartTable tobechangedelement =
                myRealm.where(CartTable.class)
                        .equalTo("productid", ""+productid)
                        .equalTo("Option", optionsvalue)
                        .findFirst();

        Log.e("change",""+productid+" "+quantity+" "+optionsvalue);
        myRealm.beginTransaction();
        tobechangedelement.setproductid(productid);
        tobechangedelement.setQuantity(quantity);
        tobechangedelement.setOption(optionsvalue);

        myRealm.commitTransaction();
//        updateTotalOnActionBar();
//        showdataoncart();
    }




    public  void removeItemfromDB(Context ctc, String productId, String optionsValue){

        CartTable tobechangedelement =
                myRealm.where(CartTable.class).equalTo("productid", "" + productId)
                .equalTo("Option", optionsValue).findFirst();


        myRealm.beginTransaction();
        tobechangedelement.removeFromRealm();
        Toast.makeText(con, "Item Deleted", Toast.LENGTH_SHORT).show();
        parsingforCart.parsing(ctc, "" + CartActivity.getCartdetails());
        myRealm.commitTransaction();

//        updateTotalOnActionBar();
//        showdataoncart();
    }


    public   int countNoofRowsInDatabse(){
        RealmResults<CartTable> results = myRealm.where(CartTable.class).findAll();

        Log.e("No Rows in CART TABLE ", "" + results.size());
        return results.size();

    }



    public RealmResults<CartTable> getFullTable(){
        RealmResults<CartTable> results = myRealm.where(CartTable.class).findAll();
        return  results ;
    }







    public  void clearCartTable(){
        myRealm.beginTransaction();
        myRealm.allObjects(CartTable.class).clear();
//      myRealm.where(CartTable.class).findAll().clear();
        myRealm.commitTransaction();
//        showdataoncart();
    }

    public static void showdataoncart (){
//        CartEvent event = null ;
//        event = new CartEvent(""+totalNoofitemsincar() , ""+calculationForGrossPrice());
//        bus.post(event);

    }


    public   String getNoofunitAccordingToProductId(String productId, String quantity, String optionsValue ){


        if(checkproductExsistance(productId,quantity,optionsValue)){
           // Log.e("fgdfdfdfdf",""+id);

            CartTable tobechangedelement =
                    myRealm.where(CartTable.class).equalTo("productid", ""+productId).equalTo("Quantity",quantity)
                            .equalTo("Option",optionsValue).findFirst();


            return  tobechangedelement.getQuantity();
        }else {
            return "1";
        }


    }

    public  boolean checkproductExsistance(String productId, String quantity, String optionsValue){

        boolean value  = false;



               if( myRealm.where(CartTable.class)
                        .equalTo("productid", ""+productId)
                        .equalTo("Option",optionsValue)
                        .count()==0)
     {
            value = false ;
        }else {
            value = true ;
        }
        Log.e("valuesscheck",""+value);
        return  value ;

    }


}
