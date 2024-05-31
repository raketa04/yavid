package by.yavid.Service.Report.Statistic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.withPrecision;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;


@RunWith(SpringRunner.class)
@RestClientTest({RateService.class})
@AutoConfigureWebClient(registerRestTemplate = true)
public class RateTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private RateService rateService;


    @Test
    public void getRate() throws ParseException {
        this.server.expect(requestTo("https://www.nbrb.by/api/exrates/rates/643?ondate=2020-1-1&parammode=1"))
                .andRespond(withSuccess(new ClassPathResource("rate.json",getClass()), MediaType.APPLICATION_JSON));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");

        String dateInString = "2020-1-1";
        Date date = dateFormat.parse(dateInString);
        double rate = rateService.getCusrRateForBYN(643,date);
        assertThat(rate).isEqualTo(3.3982/100,withPrecision(0.00001));
    }
}
