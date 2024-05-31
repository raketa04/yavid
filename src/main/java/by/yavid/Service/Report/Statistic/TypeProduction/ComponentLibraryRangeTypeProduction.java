package by.yavid.Service.Report.Statistic.TypeProduction;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ComponentLibraryRangeTypeProduction implements RangeTypeProduction {

    @Value("${ecad.report.startRangeLibraryModules}")
    private int startRangeLibraryModules;

    @Value("${ecad.report.finishRangeLibraryModules}")
    private int finishRangeLibraryModules;

    @Value("${ecad.report.startRangeModelsLibraryModules}")
    private int startRangeModelsLibraryModules;

    @Value("${ecad.report.finishRangeModelsLibraryModules}")
    private int finishRangeModelsLibraryModules;

    @Value("${ecad.report.individualModule}")
    private int individualModule;


    @Override
    public Boolean isRangeTypeProduction(int codModel, int typeProduction) {
        if ((codModel >= startRangeModelsLibraryModules && codModel <= finishRangeModelsLibraryModules) &&
            ((typeProduction < startRangeLibraryModules && typeProduction > finishRangeLibraryModules) || typeProduction != individualModule)) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public TypeProduction getTypeProdction() {
        return TypeProduction.ComponentLibrary;
    }
}
