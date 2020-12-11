package by.yavid.DTO.Unf1C;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate implements Serializable{
    Date Date;
    float Cur_OfficialRate;
    float Cur_Scale;

    public Rate() {
    }

    @JsonProperty("Date")
    public Date getDate() {
        return Date;
    }

    @JsonProperty("Date")
    public void setDate(Date date) {
        Date = date;
    }

    @JsonProperty("Cur_OfficialRate")
    public float getCur_OfficialRate() {
        return Cur_OfficialRate;
    }

    @JsonProperty("Cur_OfficialRate")
    public void setCur_OfficialRate(float cur_OfficialRate) {
        Cur_OfficialRate = cur_OfficialRate;
    }

    @JsonProperty("Cur_Scale")
    public float getCur_Scale() {
        return Cur_Scale;
    }

    @JsonProperty("Cur_Scale")
    public void setCur_Scale(float cur_Scale) {
        Cur_Scale = cur_Scale;
    }
}
