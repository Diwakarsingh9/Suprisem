package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 21-06-2016.
 */
public class Outter_filter {

    @SerializedName("status")
    public String status;

    @SerializedName("products")
    public List<Inner_all_filter> products = new ArrayList<Inner_all_filter>();
}
