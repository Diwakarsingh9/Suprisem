package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 18-06-2016.
 */
public class Inner_all_shipping {
    @SerializedName("code")
    public String code;

    @SerializedName("title")
    public String titles;

    @SerializedName("quote")
    public List<Innermost_quotes> quote = new ArrayList<>();

    @SerializedName("sort_order")
    public String sort_order;
}
