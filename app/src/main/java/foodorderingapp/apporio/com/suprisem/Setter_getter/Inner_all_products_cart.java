package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by saifi45 on 6/15/2016.
 */
public class Inner_all_products_cart {

    @SerializedName("product_id")
    public String product_id;

    @SerializedName("name")
    public String namess;

    @SerializedName("model")
    public String model;

    @SerializedName("shipping")
    public String shipping;

    @SerializedName("image")
    public String image;


    @SerializedName("option")
    public ArrayList<Innermost_pro_options_cart> optionsss = new ArrayList<>();


    @SerializedName("minimum")
    public String minimum;

    @SerializedName("quantity")
    public String quantity;

    @SerializedName("total")
    public String total;



    @SerializedName("reward")
    public String reward;



    @SerializedName("price")
    public String price;

    @SerializedName("points")
    public String points;

    @SerializedName("tax_class_id")
    public String tax_class_id;

    @SerializedName("weight")
    public String weight;

    @SerializedName("length")
    public String length;

    @SerializedName("width")
    public String width;

    @SerializedName("height")
    public String height;

    @SerializedName("recurring")
    public String recurring;

}
