package com.adcampaign.comcast.validation;

import com.adcampaign.comcast.adcampaign.AdCampaign;
import com.adcampaign.comcast.adcampaign.AdCampaignEntity;
import com.adcampaign.comcast.adcampaign.AdCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ValidationServiceImpl implements ValidationService{

    @Autowired
    private AdCampaignRepository adCampaignRepository;

    @Override
    public void validateAdCampaign(AdCampaign adCampaign) {

        AdCampaignEntity adCampaignCheck = adCampaignRepository.findByPartnerId(adCampaign.getPartnerId());
        Assert.isTrue(adCampaignCheck == null,"Can not create new Campaign!, Existing Camapaign Information found for the Partner ID : "+adCampaign.getPartnerId());
    }
}
