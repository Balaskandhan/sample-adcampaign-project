package com.adcampaign.comcast.adcampaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdCampaignController {

    @Autowired
    private AdCampaignServiceImpl adCampaignServiceimpl;

    @PostMapping("/ad")
    public void saveAdCampaign(@RequestBody AdCampaign adCampaign){
        adCampaignServiceimpl.saveAdCampaign(adCampaign);
    }

    @GetMapping("/ad/{id}")
    public AdCampaign fetchById(@PathVariable("id") String partnerId){

        return adCampaignServiceimpl.getAdCampaignById(partnerId);
    }
}

