
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
    "dataRecordId",
    "edfSignalDataRecords"
})
public class EdfDataRecord {

    @JsonProperty("dataRecordId")
    private String dataRecordId;
    @JsonProperty("edfSignalDataRecords")
    private List<EdfSignalDataRecord> edfSignalDataRecords = new ArrayList<EdfSignalDataRecord>();
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
     *     The edfSignalDataRecords
     */
    @JsonProperty("edfSignalDataRecords")
    public List<EdfSignalDataRecord> getEdfSignalDataRecords() {
        return edfSignalDataRecords;
    }

    /**
     * 
     * @param edfSignalDataRecords
     *     The edfSignalDataRecords
     */
    @JsonProperty("edfSignalDataRecords")
    public void setEdfSignalDataRecords(List<EdfSignalDataRecord> edfSignalDataRecords) {
        this.edfSignalDataRecords = edfSignalDataRecords;
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
