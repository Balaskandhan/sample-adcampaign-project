package com.adcampaign.comcast.adcampaign;

import com.adcampaign.comcast.testutil.TestDataBuilder;
import com.adcampaign.comcast.util.Converter;
import com.adcampaign.comcast.validation.ValidationServiceImpl;
import org.apache.el.util.ReflectionUtil;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class AdCampaignServiceImplTest {

    private AdCampaignServiceImpl adCampaignServiceImpl;

    @Mock
    private ValidationServiceImpl validationServiceimpl;

    @Mock
    private Converter converter;

    @Mock
    private AdCampaignRepository adCampaignRepository;

    public AdCampaignServiceImplTest(){
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void init(){
        adCampaignServiceImpl = new AdCampaignServiceImpl();
        ReflectionTestUtils.setField(adCampaignServiceImpl, "validationServiceimpl", validationServiceimpl);
        ReflectionTestUtils.setField(adCampaignServiceImpl, "converter", converter);
        ReflectionTestUtils.setField(adCampaignServiceImpl, "adCampaignRepository", adCampaignRepository);
    }

    @Test
    public void testGetAdCampaignById(){
        when(adCampaignRepository.findByPartnerId(anyString())).thenReturn(TestDataBuilder.buildAdCampaignEntity());
        when(converter.convertEntityToAdCampaign(any(AdCampaignEntity.class))).thenReturn(TestDataBuilder.buildAdCampaign());
        AdCampaign adCampaign = adCampaignServiceImpl.getAdCampaignById(anyString());
        Assertions.assertThat(adCampaign).extracting("partnerId","duration","adContent").containsExactly(TestDataBuilder.buildAdCampaign().getPartnerId(),
                TestDataBuilder.buildAdCampaign().getDuration(),TestDataBuilder.buildAdCampaign().getAdContent());
        verify(converter).convertEntityToAdCampaign(any(AdCampaignEntity.class));
        verify(adCampaignRepository).findByPartnerId(anyString());
    }

    @Test
    public void testGetAdCampaignByIdExceptionWhenDataNotFound(){
        when(adCampaignRepository.findByPartnerId(anyString())).thenReturn(null);
        when(converter.convertEntityToAdCampaign(any(AdCampaignEntity.class))).thenReturn(null);
        Assertions.assertThatThrownBy(()->adCampaignServiceImpl.getAdCampaignById(anyString())).extracting("detailMessage").containsExactly("No Data found for that Id");
    }

    @Test
    public void testSaveAdCampaign(){
        adCampaignServiceImpl.saveAdCampaign(any(AdCampaign.class));
        verify(adCampaignRepository).save(any(AdCampaignEntity.class));
        verify(converter).convertAdCampaignToEntity(any(AdCampaign.class));
    }
}
