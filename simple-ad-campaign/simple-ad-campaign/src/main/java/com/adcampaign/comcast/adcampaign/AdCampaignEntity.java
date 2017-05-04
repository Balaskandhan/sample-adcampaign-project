package com.adcampaign.comcast.adcampaign;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="adcampaign")
public class AdCampaignEntity implements Serializable{

    private static final long serialVersionUID = -7699576033831094958L;

    @Id
    private String partnerId;

    @Column(name="duration")
    private Timestamp duration;

    @Column(name="ad_content")
    private String adContent;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
    }

    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }
}
