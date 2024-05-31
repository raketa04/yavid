package by.yavid.Service.Report.Statistic.TypeProduction;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ComponentBedroomRangeTypeProduction implements RangeTypeProduction {

    @Value("${ecad.report.startRangeBedroomModules}")
    private int startRangeBedroomModules;

    @Value("${ecad.report.finishRangeBedroomModules}")
    private int finishRangeBedroomModules;

    @Value("${ecad.report.startRangeModelsBedroomModules}")
    private int startRangeModelsBedroomModules;

    @Value("${ecad.report.finishRangeModelsBedroomModules}")
    private int finishRangeModelsBedroomModules;

    @Value("${ecad.report.individualModule}")
    private int individualModule;


    @Override
    public Boolean isRangeTypeProduction(int codModel, int typeProduction) {
        if ((codModel >= startRangeModelsBedroomModules && codModel <= finishRangeModelsBedroomModules) &&
            ((typeProduction < startRangeBedroomModules && typeProduction > finishRangeBedroomModules) || typeProduction != individualModule)) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public TypeProduction getTypeProdction() {
        return TypeProduction.ComponentBedroom;
    }
}
