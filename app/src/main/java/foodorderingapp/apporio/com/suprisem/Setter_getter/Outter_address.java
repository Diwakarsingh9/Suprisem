package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_all_address;
import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_all_categories;

/**
 * Created by apporio6 on 17-06-2016.
 */
public class Outter_address {

    @SerializedName("status")
    public String status;

    @SerializedName("customer_address")
    public List<Inner_all_address> customer_address = new ArrayList<>();
}
