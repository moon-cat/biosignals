
package org.kth.sth.biosignals.edf2json.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "edfDataRecords"
})
public class EdfData {

    @JsonProperty("edfDataRecords")
    private List<EdfDataRecord> edfDataRecords = new ArrayList<EdfDataRecord>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The edfDataRecords
     */
    @JsonProperty("edfDataRecords")
    public List<EdfDataRecord> getEdfDataRecords() {
        return edfDataRecords;
    }

    /**
     *
     * @param edfDataRecords
     *     The edfDataRecords
     */
    @JsonProperty("edfDataRecords")
    public void setEdfDataRecords(List<EdfDataRecord> edfDataRecords) {
        this.edfDataRecords = edfDataRecords;
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
