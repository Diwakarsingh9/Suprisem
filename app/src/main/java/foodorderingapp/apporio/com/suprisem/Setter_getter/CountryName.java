
package foodorderingapp.apporio.com.suprisem.Setter_getter;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CountryName {

    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("state")
    @Expose
    private List<State> state = new ArrayList<State>();

    /**
     * 
     * @return
     *     The countryId
     */
    public String getCountryId() {
        return countryId;
    }

    /**
     * 
     * @param countryId
     *     The country_id
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The state
     */
    public List<State> getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(List<State> state) {
        this.state = state;
    }

}
