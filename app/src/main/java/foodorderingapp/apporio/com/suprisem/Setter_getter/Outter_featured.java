package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 6/10/2016.
 */
public class Outter_featured {

    @SerializedName("status")
    public String status;

    @SerializedName("product")
    public List<Inner_all_featured> products = new ArrayList<Inner_all_featured>();

}
