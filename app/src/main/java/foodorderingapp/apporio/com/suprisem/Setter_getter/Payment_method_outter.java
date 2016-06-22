package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import foodorderingapp.apporio.com.suprisem.Setter_getter.Inner_all_payment_method;

/**
 * Created by apporio6 on 18-06-2016.
 */
public class Payment_method_outter {

    @SerializedName("status")
    public String status;

    @SerializedName("payment_method")
    public List<Inner_all_payment_method> payment_method = new ArrayList<>();
}
