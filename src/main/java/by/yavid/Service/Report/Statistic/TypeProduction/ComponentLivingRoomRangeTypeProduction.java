package by.yavid.Service.Report.Statistic.TypeProduction;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ComponentLivingRoomRangeTypeProduction implements RangeTypeProduction {


    @Value("${ecad.report.startRangeLivingRoomModules}")
    private int startRangeLivingRoomModules;

    @Value("${ecad.report.finishRangeLivingRoomModules}")
    private int finishRangeLivingRoomModules;

    @Value("${ecad.report.startRangeModelsLivingRoomModules}")
    private int startRangeModelsLivingRoomModules;

    @Value("${ecad.report.finishRangeModelsLivingRoomModules}")
    private int finishRangeModelsLivingRoomModules;

    @Value("${ecad.report.individualModule}")
    private int individualModule;


    @Override
    public Boolean isRangeTypeProduction(int codModel, int typeProduction) {
        if ((codModel >= startRangeModelsLivingRoomModules && codModel <= finishRangeModelsLivingRoomModules) &&
            ((typeProduction < startRangeLivingRoomModules && typeProduction > finishRangeLivingRoomModules) || typeProduction != individualModule)) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public TypeProduction getTypeProdction() {
        return TypeProduction.ComponentLivingRoom;
    }
}
