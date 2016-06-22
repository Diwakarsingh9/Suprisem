package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apporio6 on 20-06-2016.
 */
public class ConfirmOrderOutter {

    @SerializedName("status")
    public String status;

    @SerializedName("order_info")
    public OrderId order_info = new OrderId();

}
