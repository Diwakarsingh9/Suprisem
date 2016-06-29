package foodorderingapp.apporio.com.suprisem.Api_s;

/**
 * Created by saifi45 on 4/26/2016.
 */
public class Apis_url {
    public static String language_id ="1";
    public  static  String all_categories = "http://design.apporio.com/ecommerce/api/getcategories?language_id="+language_id;
    public  static  String products = "http://design.apporio.com/ecommerce/api/getproducts?language_id="+language_id;
    public  static  String featured_list = "http://design.apporio.com/ecommerce/api/getfeatureproduct?language_id="+language_id;
    public  static  String get_Cart = "http://design.apporio.com/ecommerce/api/get_cart_products";
    public  static  String login = "http://design.apporio.com/ecommerce/api/login";
    public  static  String signup = "http://design.apporio.com/ecommerce/api/registration";
    public  static  String GetAddress = "http://design.apporio.com/ecommerce/api/get_customer_address?customer_id=";
    public  static  String ChangeAddress = "http://design.apporio.com/ecommerce/api/update_customer_address";
    public  static  String paymentmethod = "http://design.apporio.com/ecommerce/api/getpayment_method";
    public  static  String shipmentmethod = "http://design.apporio.com/ecommerce/api/getshipping_method";
    public  static  String countryid = "http://design.apporio.com/ecommerce/api/country";
    public  static  String ConfirmOrder = "http://design.apporio.com/ecommerce/api/confirmorder";
    public  static  String placeorder = "http://design.apporio.com/ecommerce/api/place_order";
    public  static  String filter = "http://design.apporio.com/ecommerce/api/getproducts?language_id="+language_id+"&filter_name=";
    public  static  String imageupload = "http://design.apporio.com/ecommerce/api/uploadimage";


}
