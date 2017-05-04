package com.adcampaign.comcast.testutil;

import com.adcampaign.comcast.adcampaign.AdCampaign;
import com.adcampaign.comcast.adcampaign.AdCampaignEntity;
import com.adcampaign.comcast.adcampaign.AdCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TestDataBuilder {

    @Autowired
    private AdCampaignRepository adCampaignRepository;

    public static AdCampaign buildAdCampaign(){
        AdCampaign adCampaign = new AdCampaign();
        adCampaign.setAdContent("content");
        adCampaign.setDuration("2016-05-05 00:00:00.0");
        adCampaign.setPartnerId("Test-Partner-Id");
        return adCampaign;
    }

    public static AdCampaignEntity buildAdCampaignEntity(){
        AdCampaignEntity adCampaignEntity = new AdCampaignEntity();
        adCampaignEntity.setAdContent("content");
        adCampaignEntity.setDuration(Timestamp.valueOf("2016-05-05 00:00:00.0"));
        adCampaignEntity.setPartnerId("Test-Partner-Id");
        return adCampaignEntity;
    }

    public AdCampaignEntity InsertAdCampaigntoTable(){
        AdCampaignEntity adCampaignEntity = buildAdCampaignEntity();
        adCampaignRepository.save(adCampaignEntity);
        return adCampaignEntity;
    }

}