package foodorderingapp.apporio.com.suprisem.Database;



import io.realm.RealmObject;

/**
 * Created by samir on 10/07/15.
 */
public class CartTable extends RealmObject {


    private String productid ;
    private String Quantity;
    private String Option;










    public String getproductid() {
        return productid;
    }

    public void setproductid(String productId) {
        productid = productId;
    }



    public String getOption() {
        return Option;
    }

    public void setOption(String option) {
        Option = option;
    }





    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }







}
