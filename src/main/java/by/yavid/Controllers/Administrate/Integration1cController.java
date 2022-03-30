package by.yavid.Controllers.Administrate;

import by.yavid.DTO.Unf1C.ShippedOrder;
import by.yavid.Service.Report.Statistic.ReportShippedProductsForStatistics;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("integration1c")
public class Integration1cController {

    private ReportShippedProductsForStatistics reportShippedProductsForStatistics;

    public Integration1cController(ReportShippedProductsForStatistics reportShippedProductsForStatistics) {
        this.reportShippedProductsForStatistics = reportShippedProductsForStatistics;
    }

    @RequestMapping(value = "reports/shipped_order_from_statictic", method = RequestMethod.POST)
    public ResponseEntity<Boolean> createReportShippedOrderFromStatistic(@RequestBody List<ShippedOrder> shippedOrders) {
        return new ResponseEntity<>(reportShippedProductsForStatistics.report(shippedOrders), HttpStatus.OK);
    }
}
