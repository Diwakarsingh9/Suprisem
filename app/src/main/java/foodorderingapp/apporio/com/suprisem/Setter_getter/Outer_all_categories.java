package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 6/9/2016.
 */
public class Outer_all_categories {
    @SerializedName("status")
    public String status;

    @SerializedName("categories")
    public List<Inner_all_categories> categories = new ArrayList<Inner_all_categories>();

}
