package com.adcampaign.comcast.adcampaign;

public interface AdCampaignService {

    void saveAdCampaign(AdCampaign adCampaign);

    AdCampaign getAdCampaignById(String id);
}
