
package foodorderingapp.apporio.com.suprisem.Setter_getter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class State {

    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * 
     * @return
     *     The stateId
     */
    public String getStateId() {
        return stateId;
    }

    /**
     * 
     * @param stateId
     *     The state_id
     */
    public void setStateId(String stateId) {
        this.stateId = stateId;
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

}
