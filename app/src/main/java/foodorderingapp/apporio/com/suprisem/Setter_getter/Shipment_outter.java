package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 18-06-2016.
 */
public class Shipment_outter {

    @SerializedName("status")
    public String status;

    @SerializedName("shipping_method")
    public List<Inner_all_shipping> shipping_method = new ArrayList<>();
}
