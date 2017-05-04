package com.adcampaign.comcast.util;

import com.adcampaign.comcast.adcampaign.AdCampaign;
import com.adcampaign.comcast.adcampaign.AdCampaignEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Converter {

    public AdCampaignEntity convertAdCampaignToEntity(AdCampaign adCampaign){

        AdCampaignEntity adCampaignEntity = new AdCampaignEntity();
        adCampaignEntity.setPartnerId(adCampaign.getPartnerId());
        adCampaignEntity.setDuration(Timestamp.valueOf(adCampaign.getDuration()));
        adCampaignEntity.setAdContent(adCampaign.getAdContent());

        return adCampaignEntity;
    }

    public AdCampaign convertEntityToAdCampaign(AdCampaignEntity adCampaignEntity){

        AdCampaign adCampaign = new AdCampaign();
        adCampaign.setPartnerId(adCampaignEntity.getPartnerId());
        adCampaign.setDuration(adCampaignEntity.getDuration().toString());
        adCampaign.setAdContent(adCampaignEntity.getAdContent());
        return adCampaign;
    }
}
