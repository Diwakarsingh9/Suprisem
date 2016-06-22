package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 20-06-2016.
 */
public class Country_list {
    @SerializedName("country_id")
    public String country_id;

    @SerializedName("name")
    public String country_name;

    @SerializedName("state")
    public List<Inner_all_statelist> statelists = new ArrayList<>();
}
