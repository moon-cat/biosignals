
package org.kth.sth.biosignals.edf2json.model.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "edfMetadata",
    "edfData"
})
public class Edf {

    /**
     * 
     */
    @JsonProperty("edfMetadata")
    private EdfMetadata edfMetadata;
    @JsonProperty("edfData")
    private EdfData edfData;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The edfMetadata
     */
    @JsonProperty("edfMetadata")
    public EdfMetadata getEdfMetadata() {
        return edfMetadata;
    }

    /**
     * 
     * @param edfMetadata
     *     The edfMetadata
     */
    @JsonProperty("edfMetadata")
    public void setEdfMetadata(EdfMetadata edfMetadata) {
        this.edfMetadata = edfMetadata;
    }

    /**
     * 
     * @return
     *     The edfData
     */
    @JsonProperty("edfData")
    public EdfData getEdfData() {
        return edfData;
    }

    /**
     * 
     * @param edfData
     *     The edfData
     */
    @JsonProperty("edfData")
    public void setEdfData(EdfData edfData) {
        this.edfData = edfData;
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
