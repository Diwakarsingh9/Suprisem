package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 6/9/2016.
 */
public class Inner_all_categories {

    @SerializedName("category_id")
    public String category_idss;

    @SerializedName("image")
    public String imagess;

    @SerializedName("parent_id")
    public String parent_id;

    @SerializedName("top")
    public String topss;

    @SerializedName("column")
    public String column;

    @SerializedName("sort_order")
    public String sort_order;

    @SerializedName("status")
    public String status;

    @SerializedName("date_added")
    public String date_added;

    @SerializedName("date_modified")
    public String date_modified;

    @SerializedName("language_id")
    public String language_id;

    @SerializedName("name")
    public String namess;

    @SerializedName("description")
    public String description;

    @SerializedName("meta_title")
    public String meta_title;

    @SerializedName("meta_description")
    public String meta_description;

    @SerializedName("meta_keyword")
    public String meta_keyword;

    @SerializedName("store_id")
    public String store_id;

    @SerializedName("child")
    public List<Innermost_all_categories> child = new ArrayList<Innermost_all_categories>();

}
