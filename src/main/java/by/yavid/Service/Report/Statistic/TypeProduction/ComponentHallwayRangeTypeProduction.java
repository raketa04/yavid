package by.yavid.Service.Report.Statistic.TypeProduction;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ComponentHallwayRangeTypeProduction implements RangeTypeProduction {

    @Value("${ecad.report.startRangeHallwayModules}")
    private int startRangeHallwayModules;

    @Value("${ecad.report.finishRangeHallwayModules}")
    private int finishRangeHallwayModules;

    @Value("${ecad.report.startRangeModelsHallwayModules}")
    private int startRangeModelsHallwayModules;

    @Value("${ecad.report.finishRangeModelsHallwayModules}")
    private int finishRangeModelsHallwayModules;

    @Value("${ecad.report.individualModule}")
    private int individualModule;


    @Override
    public Boolean isRangeTypeProduction(int codModel, int typeProduction) {
        if ((codModel >= startRangeModelsHallwayModules && codModel <= finishRangeModelsHallwayModules) &&
            ((typeProduction < startRangeHallwayModules && typeProduction > finishRangeHallwayModules) || typeProduction != individualModule)) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public TypeProduction getTypeProdction() {
        return TypeProduction.ComponentHallway;
    }
}
