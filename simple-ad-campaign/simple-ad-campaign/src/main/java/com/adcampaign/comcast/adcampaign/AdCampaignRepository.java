package com.adcampaign.comcast.adcampaign;

import org.springframework.data.repository.CrudRepository;
import com.adcampaign.comcast.adcampaign.AdCampaignEntity;

public interface AdCampaignRepository extends CrudRepository<AdCampaignEntity, String> {

    AdCampaignEntity findByPartnerId(String partnerId);
}
