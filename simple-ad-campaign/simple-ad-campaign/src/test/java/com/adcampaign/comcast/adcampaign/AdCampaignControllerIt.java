package com.adcampaign.comcast.adcampaign;

import com.adcampaign.comcast.testutil.TestDataBuilder;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdCampaignControllerIt {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TestDataBuilder testDataBuilder;

    @Autowired
    private AdCampaignServiceImpl adCampaignService;

    @Autowired
    private AdCampaignRepository adCampaignRepository;

    private AdCampaignEntity adCampaignEntity;

    @Before
    public void testDataSetup(){

        adCampaignEntity = testDataBuilder.InsertAdCampaigntoTable();
    }

    @After
    public void cleanTestData(){
        adCampaignRepository.deleteAll();
    }

    @Test
    public void testSaveAdCampaign(){
        AdCampaign adCampaign = TestDataBuilder.buildAdCampaign();
        adCampaign.setPartnerId("NewTestPartnerId");
        testRestTemplate.exchange("/ad", HttpMethod.POST, new HttpEntity<AdCampaign>(adCampaign), void.class);
        ResponseEntity<AdCampaign> adcampaignResult = testRestTemplate.getForEntity("/ad/"+adCampaign.getPartnerId(),AdCampaign.class);
        assertThat(adcampaignResult.getStatusCodeValue()).isEqualTo(200);
        assertThat(adcampaignResult.getBody()).extracting("partnerId","duration","adContent").containsExactly(adCampaign.getPartnerId(),
                adCampaign.getDuration(),adCampaign.getAdContent());
    }

    @Test
    public void testSaveAdCampaignWithExistingPartnerIdShouldFail(){
        AdCampaign adCampaign = TestDataBuilder.buildAdCampaign();
        try{
            testRestTemplate.exchange("/ad", HttpMethod.POST, new HttpEntity<AdCampaign>(adCampaign), void.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testfetchByIdNotExistingDataWillFail(){
        ResponseEntity<AdCampaign> adcampaignResult = testRestTemplate.getForEntity("/ad/"+"12345",AdCampaign.class);
        assertThat(adcampaignResult.getStatusCodeValue()).isEqualTo(500);
    }

}
