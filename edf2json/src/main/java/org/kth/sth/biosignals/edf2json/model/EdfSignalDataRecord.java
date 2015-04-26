
package org.kth.sth.biosignals.edf2json.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "signalId",
    "edfSignalDataRecord"
})
public class EdfSignalDataRecord {

    @JsonProperty("signalId")
    private String signalId;
    @JsonProperty("edfSignalDataRecord")
    private List<Object> edfSignalDataRecord = new ArrayList<Object>();
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
     *     The edfSignalDataRecord
     */
    @JsonProperty("edfSignalDataRecord")
    public List<Object> getEdfSignalDataRecord() {
        return edfSignalDataRecord;
    }

    /**
     * 
     * @param edfSignalDataRecord
     *     The edfSignalDataRecord
     */
    @JsonProperty("edfSignalDataRecord")
    public void setEdfSignalDataRecord(List<Object> edfSignalDataRecord) {
        this.edfSignalDataRecord = edfSignalDataRecord;
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
