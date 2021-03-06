
package org.kth.sth.biosignals.edf2json.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "signalId",
    "data"
})
public class EdfSignalDataRecord {

    @JsonProperty("signalId")
    private String signalId;
    @JsonProperty("data")
    private List<Object> data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The signalId
     */
    @JsonProperty("signalId")
    public String getSignalId() {
        return signalId;
    }

    /**
     * 
     * @param signalId
     *     The signalId
     */
    @JsonProperty("signalId")
    public void setSignalId(String signalId) {
        this.signalId = signalId;
    }

    /**
     * 
     * @return
     *     The data
     */
    @JsonProperty("data")
    public List<Object> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    @JsonProperty("data")
    public void setData(List<Object> data) {
        this.data = data;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
