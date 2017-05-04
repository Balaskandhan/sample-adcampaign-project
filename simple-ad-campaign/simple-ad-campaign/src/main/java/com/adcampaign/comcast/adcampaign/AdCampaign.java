package com.adcampaign.comcast.adcampaign;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdCampaign implements Serializable{

    private static final long serialVersionUID = -7699576033831094965L;

    private String partnerId;
    private String duration;
    private String adContent;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }
}
