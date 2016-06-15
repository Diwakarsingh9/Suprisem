package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 6/10/2016.
 */
public class Inner_all_featured {

    @SerializedName("product_id")
    public String product_id;

    @SerializedName("model")
    public String model;

    @SerializedName("sku")
    public String sku;

    @SerializedName("quantity")
    public String quantity;

    @SerializedName("stock_status_id")
    public String stock_status_id;

    @SerializedName("image")
    public String image;

    @SerializedName("manufacturer_id")
    public String manufacturer_id;

    @SerializedName("shipping")
    public String shipping;

    @SerializedName("price")
    public String price;

    @SerializedName("points")
    public String points;

    @SerializedName("date_available")
    public String date_available;

    @SerializedName("weight")
    public String weight;

    @SerializedName("length")
    public String length;

    @SerializedName("width")
    public String width;

    @SerializedName("height")
    public String height;

    @SerializedName("status")
    public String status;


    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("meta_title")
    public String meta_title;

    @SerializedName("manufacturer")
    public String manufacturer;

    @SerializedName("discount")
    public String discount;

    @SerializedName("special")
    public String special;

    @SerializedName("reward")
    public String reward;


    @SerializedName("stock_status")
    public String stock_status;

    @SerializedName("weight_class")
    public String weight_class;

    @SerializedName("length_class")
    public String length_class;

    @SerializedName("rating")
    public String rating;

    @SerializedName("reviews")
    public String reviews;

    @SerializedName("options")
    public ArrayList<Innermost_all_pro_options> options = new ArrayList<>();


    @SerializedName("images")
    public ArrayList<Innermost_all_pro_images> images = new ArrayList<Innermost_all_pro_images>();

}
