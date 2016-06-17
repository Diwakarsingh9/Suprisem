package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 17-06-2016.
 */
public class Login_outter {


    @SerializedName("status")
    public String status;


    @SerializedName("customer")
    public Inner_login customer = new Inner_login();
}
