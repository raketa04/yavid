package by.yavid.Service.Report.Statistic;

import by.yavid.DTO.Unf1C.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RateService {

    @Autowired
    RestTemplate restTemplatet;

    public RateService(RestTemplate restTemplatet) {
        this.restTemplatet = restTemplatet;
    }
    @Cacheable("rates")
    public float getCusrRateForBYN(int codRate, Date dateRate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        String url = "https://www.nbrb.by/api/exrates/rates/" + codRate + "?ondate=" + dateFormat.format(dateRate) + "&parammode=1";
        ResponseEntity<Rate> rateResponse = restTemplatet.exchange(url,HttpMethod.GET,null, new ParameterizedTypeReference<Rate>() {});
        Rate rate = rateResponse.getBody();
        return  rate.getCur_OfficialRate()/rate.getCur_Scale();
    }
}
