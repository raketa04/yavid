package by.yavid.Service.Report.Statistic.TypeProduction;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ComponentKitchenRangeTypeProduction implements RangeTypeProduction {

    @Value("${ecad.report.startRangeKithenModules}")
    private Integer startRangeKithenModules;

    @Value("${ecad.report.finishRangeKithenModules}")
    private Integer finishRangeKithenModules;

    @Value("${ecad.report.startRangeModelsKithenModules}")
    private Integer startRangeModelsKithenModules;

    @Value("${ecad.report.finishRangeModelsKithenModules}")
    private Integer finishRangeModelsKithenModules;

    @Value("${ecad.report.individualModule}")
    private int individualModule;

    @Override
    public Boolean isRangeTypeProduction(int codModel, int typeProduction) {
        if ((codModel >= startRangeModelsKithenModules && codModel <= finishRangeModelsKithenModules) &&
            ((typeProduction < startRangeKithenModules && typeProduction > finishRangeKithenModules) || typeProduction != individualModule)) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public TypeProduction getTypeProdction() {
        return TypeProduction.ComponentKitchen;
    }
}
