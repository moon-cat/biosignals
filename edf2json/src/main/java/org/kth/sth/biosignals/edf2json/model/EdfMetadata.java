
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
    "version",
    "localPatientIdentification",
    "localRecordingIdentification",
    "startDate",
    "startTime",
    "headerLengthInBytes",
    "noOfDataRecords",
    "dataRecordDurationInSeconds",
    "noOfSignalsInDataRecord",
    "signalMetadata"
})
public class EdfMetadata {

    @JsonProperty("version")
    private String version;
    @JsonProperty("localPatientIdentification")
    private String localPatientIdentification;
    @JsonProperty("localRecordingIdentification")
    private String localRecordingIdentification;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("headerLengthInBytes")
    private Integer headerLengthInBytes;
    @JsonProperty("noOfDataRecords")
    private Integer noOfDataRecords;
    @JsonProperty("dataRecordDurationInSeconds")
    private Integer dataRecordDurationInSeconds;
    @JsonProperty("noOfSignalsInDataRecord")
    private Integer noOfSignalsInDataRecord;
    @JsonProperty("signalMetadata")
    private List<SignalMetadata> signalMetadata = new ArrayList<SignalMetadata>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The version
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The localPatientIdentification
     */
    @JsonProperty("localPatientIdentification")
    public String getLocalPatientIdentification() {
        return localPatientIdentification;
    }

    /**
     * 
     * @param localPatientIdentification
     *     The localPatientIdentification
     */
    @JsonProperty("localPatientIdentification")
    public void setLocalPatientIdentification(String localPatientIdentification) {
        this.localPatientIdentification = localPatientIdentification;
    }

    /**
     * 
     * @return
     *     The localRecordingIdentification
     */
    @JsonProperty("localRecordingIdentification")
    public String getLocalRecordingIdentification() {
        return localRecordingIdentification;
    }

    /**
     * 
     * @param localRecordingIdentification
     *     The localRecordingIdentification
     */
    @JsonProperty("localRecordingIdentification")
    public void setLocalRecordingIdentification(String localRecordingIdentification) {
        this.localRecordingIdentification = localRecordingIdentification;
    }

    /**
     * 
     * @return
     *     The startDate
     */
    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    /**
     * 
     * @param startDate
     *     The startDate
     */
    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * @return
     *     The startTime
     */
    @JsonProperty("startTime")
    public String getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime
     *     The startTime
     */
    @JsonProperty("startTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 
     * @return
     *     The headerLengthInBytes
     */
    @JsonProperty("headerLengthInBytes")
    public Integer getHeaderLengthInBytes() {
        return headerLengthInBytes;
    }

    /**
     * 
     * @param headerLengthInBytes
     *     The headerLengthInBytes
     */
    @JsonProperty("headerLengthInBytes")
    public void setHeaderLengthInBytes(Integer headerLengthInBytes) {
        this.headerLengthInBytes = headerLengthInBytes;
    }

    /**
     * 
     * @return
     *     The noOfDataRecords
     */
    @JsonProperty("noOfDataRecords")
    public Integer getNoOfDataRecords() {
        return noOfDataRecords;
    }

    /**
     * 
     * @param noOfDataRecords
     *     The noOfDataRecords
     */
    @JsonProperty("noOfDataRecords")
    public void setNoOfDataRecords(Integer noOfDataRecords) {
        this.noOfDataRecords = noOfDataRecords;
    }

    /**
     * 
     * @return
     *     The dataRecordDurationInSeconds
     */
    @JsonProperty("dataRecordDurationInSeconds")
    public Integer getDataRecordDurationInSeconds() {
        return dataRecordDurationInSeconds;
    }

    /**
     * 
     * @param dataRecordDurationInSeconds
     *     The dataRecordDurationInSeconds
     */
    @JsonProperty("dataRecordDurationInSeconds")
    public void setDataRecordDurationInSeconds(Integer dataRecordDurationInSeconds) {
        this.dataRecordDurationInSeconds = dataRecordDurationInSeconds;
    }

    /**
     * 
     * @return
     *     The noOfSignalsInDataRecord
     */
    @JsonProperty("noOfSignalsInDataRecord")
    public Integer getNoOfSignalsInDataRecord() {
        return noOfSignalsInDataRecord;
    }

    /**
     * 
     * @param noOfSignalsInDataRecord
     *     The noOfSignalsInDataRecord
     */
    @JsonProperty("noOfSignalsInDataRecord")
    public void setNoOfSignalsInDataRecord(Integer noOfSignalsInDataRecord) {
        this.noOfSignalsInDataRecord = noOfSignalsInDataRecord;
    }

    /**
     * 
     * @return
     *     The signalMetadata
     */
    @JsonProperty("signalMetadata")
    public List<SignalMetadata> getSignalMetadata() {
        return signalMetadata;
    }

    /**
     * 
     * @param signalMetadata
     *     The signalMetadata
     */
    @JsonProperty("signalMetadata")
    public void setSignalMetadata(List<SignalMetadata> signalMetadata) {
        this.signalMetadata = signalMetadata;
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
