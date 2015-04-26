
package org.kth.sth.biosignals.edf2json.model;

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
    "label",
    "transducerType",
    "physicalDimension",
    "physicalMin",
    "physicalMax",
    "digitalMin",
    "digitalMax",
    "prefiltering",
    "noOfSamplesInDataRecord"
})
public class SignalMetadata {

    @JsonProperty("label")
    private String label;
    @JsonProperty("transducerType")
    private String transducerType;
    @JsonProperty("physicalDimension")
    private String physicalDimension;
    @JsonProperty("physicalMin")
    private Integer physicalMin;
    @JsonProperty("physicalMax")
    private Integer physicalMax;
    @JsonProperty("digitalMin")
    private Integer digitalMin;
    @JsonProperty("digitalMax")
    private Integer digitalMax;
    @JsonProperty("prefiltering")
    private String prefiltering;
    @JsonProperty("noOfSamplesInDataRecord")
    private Integer noOfSamplesInDataRecord;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The label
     */
    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 
     * @return
     *     The transducerType
     */
    @JsonProperty("transducerType")
    public String getTransducerType() {
        return transducerType;
    }

    /**
     * 
     * @param transducerType
     *     The transducerType
     */
    @JsonProperty("transducerType")
    public void setTransducerType(String transducerType) {
        this.transducerType = transducerType;
    }

    /**
     * 
     * @return
     *     The physicalDimension
     */
    @JsonProperty("physicalDimension")
    public String getPhysicalDimension() {
        return physicalDimension;
    }

    /**
     * 
     * @param physicalDimension
     *     The physicalDimension
     */
    @JsonProperty("physicalDimension")
    public void setPhysicalDimension(String physicalDimension) {
        this.physicalDimension = physicalDimension;
    }

    /**
     * 
     * @return
     *     The physicalMin
     */
    @JsonProperty("physicalMin")
    public Integer getPhysicalMin() {
        return physicalMin;
    }

    /**
     * 
     * @param physicalMin
     *     The physicalMin
     */
    @JsonProperty("physicalMin")
    public void setPhysicalMin(Integer physicalMin) {
        this.physicalMin = physicalMin;
    }

    /**
     * 
     * @return
     *     The physicalMax
     */
    @JsonProperty("physicalMax")
    public Integer getPhysicalMax() {
        return physicalMax;
    }

    /**
     * 
     * @param physicalMax
     *     The physicalMax
     */
    @JsonProperty("physicalMax")
    public void setPhysicalMax(Integer physicalMax) {
        this.physicalMax = physicalMax;
    }

    /**
     * 
     * @return
     *     The digitalMin
     */
    @JsonProperty("digitalMin")
    public Integer getDigitalMin() {
        return digitalMin;
    }

    /**
     * 
     * @param digitalMin
     *     The digitalMin
     */
    @JsonProperty("digitalMin")
    public void setDigitalMin(Integer digitalMin) {
        this.digitalMin = digitalMin;
    }

    /**
     * 
     * @return
     *     The digitalMax
     */
    @JsonProperty("digitalMax")
    public Integer getDigitalMax() {
        return digitalMax;
    }

    /**
     * 
     * @param digitalMax
     *     The digitalMax
     */
    @JsonProperty("digitalMax")
    public void setDigitalMax(Integer digitalMax) {
        this.digitalMax = digitalMax;
    }

    /**
     * 
     * @return
     *     The prefiltering
     */
    @JsonProperty("prefiltering")
    public String getPrefiltering() {
        return prefiltering;
    }

    /**
     * 
     * @param prefiltering
     *     The prefiltering
     */
    @JsonProperty("prefiltering")
    public void setPrefiltering(String prefiltering) {
        this.prefiltering = prefiltering;
    }

    /**
     * 
     * @return
     *     The noOfSamplesInDataRecord
     */
    @JsonProperty("noOfSamplesInDataRecord")
    public Integer getNoOfSamplesInDataRecord() {
        return noOfSamplesInDataRecord;
    }

    /**
     * 
     * @param noOfSamplesInDataRecord
     *     The noOfSamplesInDataRecord
     */
    @JsonProperty("noOfSamplesInDataRecord")
    public void setNoOfSamplesInDataRecord(Integer noOfSamplesInDataRecord) {
        this.noOfSamplesInDataRecord = noOfSamplesInDataRecord;
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
