package com.adcampaign.comcast.adcampaign;

import com.adcampaign.comcast.util.Converter;
import com.adcampaign.comcast.validation.ValidationService;
import com.adcampaign.comcast.validation.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

@Component
public class AdCampaignServiceImpl implements AdCampaignService{

    @Autowired
    private ValidationServiceImpl validationServiceimpl;

    @Autowired
    private Converter converter;

    @Autowired
    private AdCampaignRepository adCampaignRepository;

    @Override
    public void saveAdCampaign(AdCampaign adCampaign) {

        validationServiceimpl.validateAdCampaign(adCampaign);
        AdCampaignEntity adCampaignEntity = converter.convertAdCampaignToEntity(adCampaign);
        adCampaignRepository.save(adCampaignEntity);
    }

    @Override
    public AdCampaign getAdCampaignById(String id) {
        AdCampaignEntity adCampaignEntity = adCampaignRepository.findByPartnerId(id);
        Assert.isTrue(adCampaignEntity != null ,"No Data found for that Id");
        return converter.convertEntityToAdCampaign(adCampaignEntity);
    }


}
