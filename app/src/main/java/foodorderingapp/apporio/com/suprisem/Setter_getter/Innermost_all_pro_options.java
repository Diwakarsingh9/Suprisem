package foodorderingapp.apporio.com.suprisem.Setter_getter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 6/13/2016.
 */
public class Innermost_all_pro_options {


    @SerializedName("product_option_id")
    public String product_option_id;

    @SerializedName("product_option_value")
    public List<Innermost2_pro_options> options = new ArrayList<>();

    @SerializedName("option_id")
    public String option_id;


    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;

    @SerializedName("value")
    public String value;

    @SerializedName("required")
    public String required;



}
