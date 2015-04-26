package org.kth.sth.biosignals.edf2json.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "name",
        "originalFilename",
        "createdDate",
        "modifiedDate"
})
public class EdfProperties {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("originalFilename")
    private String originalFilename;

    @JsonProperty("createdDate")
    private String createdDate;

    @JsonProperty("modifiedDate")
    private String modifiedDate;

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("originalFilename")
    public String getOriginalFilename() {
        return originalFilename;
    }

    @JsonProperty("originalFilename")
    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    @JsonProperty("createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("modifiedDate")
    public String getModifiedDate() {
        return modifiedDate;
    }

    @JsonProperty("modifiedDate")
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
