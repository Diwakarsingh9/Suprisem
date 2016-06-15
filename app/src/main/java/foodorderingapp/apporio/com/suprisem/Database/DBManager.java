//package foodorderingapp.apporio.com.suprisem.Database;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.Toast;
//
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import io.realm.Realm;
//import io.realm.RealmResults;
//
//
///**
// * Created by samir on 10/07/15.
// */
//public class DBManager {
//
//    public static CartTable ct ;
//
//    public  Realm myRealm;
//
//    public static Context con ;
//
//   // public static EventBus bus = EventBus.getDefault() ;
//
//    public DBManager(Context con){
//        this.con = con ;
//
//        myRealm = Realm.getInstance(con);
//    }
//
//    public   void addtocart(String productId,  String quantity
//            , String optionsValue ){
//
//        if(checkproductExsistance(productId,quantity,optionsValue)){
//            ////change already saved details in database
//            changeExsistingRowintable(productId,food_name,Foodsize,optionsValue,food_noOfUnits);
//        }else {
//            /////  create new row in database
//            createnreRowintable( productId, food_name, foodprice, FoodDescp,Foodsize,
//                    food_noOfUnits,SpecialRequest,optionsValue);
//        }
//
//    }
//
//
//    private  void createnreRowintable(String productId,  String food_name,  String foodprice,
//                                            String FoodDescp, String Foodsize,
//                                            String food_noOfUnits, String SpecialRequest
//            , String optionsValue) {
//        Log.e("hfdhg","nounits   ---"+""+food_noOfUnits);
////        new CartTable(ProductId,
////                rest_id,
////                food_name,
////                foodtype,
////                foodprice,foodimage,foodrating,food_no_of_units,toppingprice).save();
////
////        updateTotalOnActionBar();
////        showdataoncart();
//
//        myRealm.beginTransaction();
//        CartTable pd = myRealm.createObject(CartTable.class);
//
//        pd.setproductid(productId);
//
//        myRealm.commitTransaction();
//       // countNoofRowsInDatabse();
//
//    }
//
//    private static void updateTotalOnActionBar() {
//       /* if(ActivityDetector.openActivity.equals("MainActivity")){
//            MainActivity.showgrossOnCart(calculationForGrossPrice());
//        }if(ActivityDetector.openActivity.equals("CartActivity")){
//            CartActivity.showgrossOnCartCartActivity(calculationForGrossPrice());
//        }   */
//
//
//    }
//
//    public  double calculationForGrossPrice() {
//
//        Double dtemp1=0.0,dtemp2  , multiplier,multiplier2 , gross = 0.0,gross2 = 0.0;
//        int temp1,temp2;
//        RealmResults<CartTable> templist = myRealm.where(CartTable.class).findAll();
//
//        for(int i = 0 ; i<templist.size() ; i++){
//            dtemp1 = Double.parseDouble(templist.get(i).getFoodprice());
//            temp1 = Integer.parseInt(templist.get(i).getFoodNoOfUnits());
//            multiplier = temp1 * dtemp1 ;
//            gross = gross + multiplier ;
//        }
//        return gross ;
//    }
//    public  double noofquanty() {
//
//        Double dtemp1=0.0,dtemp2  , multiplier , gross = 0.0,gross2 = 0.0;
//        int temp1;
//
//        RealmResults<CartTable> templist2 = myRealm.where(CartTable.class).findAll();
//        for(int i = 0 ; i<templist2.size() ; i++){
//            dtemp2 = Double.parseDouble(templist2.get(i).getFoodNoOfUnits());
//            gross2 = gross2 + dtemp2 ;
//        }
//
//        return gross2 ;
//    }
//
//    public  void changeExsistingRowintable(String productid, String food_name, String foodsize, String optionsValue,String noOfunitreport) {
//        Log.e("hfdhg", "nounits   ---" + noOfunitreport);
//        CartTable tobechangedelement =
//                myRealm.where(CartTable.class)
//                        .equalTo("productid", ""+productid).equalTo("Foodname",food_name)
//                        .equalTo("Foodsize",foodsize).equalTo("OptionsValue",optionsValue)
//
//                        .findFirst();
//
//
//        myRealm.beginTransaction();
//        tobechangedelement.setproductid(productid);
//        tobechangedelement.setFoodNoOfUnits(noOfunitreport);
//
//        myRealm.commitTransaction();
////        updateTotalOnActionBar();
////        showdataoncart();
//    }
//
//
//
//
//    public  void removeItemfromDB(String productId, String food_name, String foodsize, String optionsValue){
//
//        CartTable tobechangedelement =
//                myRealm.where(CartTable.class).equalTo("productid", ""+productId).equalTo("Foodname",food_name)
//                .equalTo("Foodsize",foodsize).equalTo("OptionsValue",optionsValue).findFirst();
//
//
//        myRealm.beginTransaction();
//        tobechangedelement.removeFromRealm();
//        Toast.makeText(con, "Item Deleted", Toast.LENGTH_SHORT).show();
//        myRealm.commitTransaction();
//
////        updateTotalOnActionBar();
////        showdataoncart();
//    }
//
//
//    public   int countNoofRowsInDatabse(){
//        RealmResults<CartTable> results = myRealm.where(CartTable.class).findAll();
//
//        Log.e("No Rows in CART TABLE ", "" + results.size());
//        return results.size();
//
//    }
//
//
//
//    public RealmResults<CartTable> getFullTable(){
//        RealmResults<CartTable> results = myRealm.where(CartTable.class).findAll();
//        return  results ;
//    }
//
//
//
//
//
//
//
//    public  void clearCartTable(){
//        myRealm.beginTransaction();
//        myRealm.allObjects(CartTable.class).clear();
////      myRealm.where(CartTable.class).findAll().clear();
//        myRealm.commitTransaction();
////        showdataoncart();
//    }
//
//    public static void showdataoncart (){
////        CartEvent event = null ;
////        event = new CartEvent(""+totalNoofitemsincar() , ""+calculationForGrossPrice());
////        bus.post(event);
//
//    }
//
//
//    public   String getNoofunitAccordingToProductId(String productId, String food_name, String foodsize, String optionsValue ){
//
//
//        if(checkproductExsistance(productId,food_name,foodsize,optionsValue)){
//           // Log.e("fgdfdfdfdf",""+id);
//
//            CartTable tobechangedelement =
//                    myRealm.where(CartTable.class).equalTo("productid", ""+productId).equalTo("Foodname",food_name)
//                            .equalTo("Foodsize",foodsize).equalTo("OptionsValue",optionsValue).findFirst();
//
//
//            return  tobechangedelement.getFoodNoOfUnits();
//        }else {
//            return "1";
//        }
//
//
//    }
//
//    public  boolean checkproductExsistance(String productId, String quantity, String optionsValue){
//
//        boolean value  = false;
//
//
//        RealmResults<CartTable> results1 =
//                myRealm.where(CartTable.class)
//                        .equalTo("productid", ""+productId).equalTo("Quantity",quantity)
//                        .equalTo("OptionsValue",optionsValue)
//                        .findAll();
//
//        if(results1.size() >0){
//            value = true ;
//        }else {
//            value = false ;
//        }
//        return  value ;
//    }
//
//
//}
