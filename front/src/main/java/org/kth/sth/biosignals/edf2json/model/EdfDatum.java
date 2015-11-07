
package org.kth.sth.biosignals.edf2json.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "dataRecordId",
    "edfDataRecord"
})
public class EdfDatum {

    @JsonProperty("dataRecordId")
    private String dataRecordId;
    @JsonProperty("edfDataRecord")
    private List<EdfDataRecord> edfDataRecord;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The dataRecordId
     */
    @JsonProperty("dataRecordId")
    public String getDataRecordId() {
        return dataRecordId;
    }

    /**
     * 
     * @param dataRecordId
     *     The dataRecordId
     */
    @JsonProperty("dataRecordId")
    public void setDataRecordId(String dataRecordId) {
        this.dataRecordId = dataRecordId;
    }

    /**
     * 
     * @return
     *     The edfDataRecord
     */
    @JsonProperty("edfDataRecord")
    public List<EdfDataRecord> getEdfDataRecord() {
        return edfDataRecord;
    }

    /**
     * 
     * @param edfDataRecord
     *     The edfDataRecord
     */
    @JsonProperty("edfDataRecord")
    public void setEdfDataRecord(List<EdfDataRecord> edfDataRecord) {
        this.edfDataRecord = edfDataRecord;
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
