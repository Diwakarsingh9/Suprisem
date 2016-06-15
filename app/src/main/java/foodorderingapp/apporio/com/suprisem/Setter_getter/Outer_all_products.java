package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 6/10/2016.
 */
public class Outer_all_products {
    @SerializedName("status")
    public String status;

    @SerializedName("count")
    public String count;

    @SerializedName("products")
    public List<Inner_all_products> products = new ArrayList<Inner_all_products>();
}
