package com.adcampaign.comcast.adcampaign;

import com.adcampaign.comcast.testutil.TestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdCampaignController.class)
public class AdCampaignControllerTest {

    private AdCampaignController adCampaignController;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdCampaignServiceImpl adCampaignServiceimpl;

    @Before
    public void setup() {

        adCampaignController = new AdCampaignController();

        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .alwaysDo(print())
                .build();
        ReflectionTestUtils.setField(adCampaignController,"adCampaignServiceimpl",adCampaignServiceimpl);
    }

    @Test
    public void testFetchById() throws Exception {
        when(adCampaignServiceimpl.getAdCampaignById(anyString())).thenReturn(TestDataBuilder.buildAdCampaign());
        MvcResult returnedCampaign = this.mvc.perform(get("/ad/id")).andReturn();
        assertThat(returnedCampaign.getResponse().getStatus()).isEqualTo(200);
        verify(adCampaignServiceimpl).getAdCampaignById(anyString());
    }

    @Test
    public void testSaveAdCampaign() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.mvc.perform(post("/ad")
                .content(mapper.writeValueAsString(TestDataBuilder.buildAdCampaign()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(adCampaignServiceimpl).saveAdCampaign(any(AdCampaign.class));
    }

    @Test
    public void testSaveAdCampaignWithBadData(){

        AdCampaign adCampaign = TestDataBuilder.buildAdCampaign();
        ObjectMapper mapper = new ObjectMapper();
        try{
            this.mvc.perform(post("/ad")
                    .content(mapper.writeValueAsString(null))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError());
        }
        catch(Exception e){
            e.getMessage();
        }
    }
}
