package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 6/15/2016.
 */
public class Cart_Outter {

    @SerializedName("status")
    public String status;

    @SerializedName("total")
    public String total;

    @SerializedName("cart")
    public List<Inner_all_products_cart> cart = new ArrayList<>();
}
