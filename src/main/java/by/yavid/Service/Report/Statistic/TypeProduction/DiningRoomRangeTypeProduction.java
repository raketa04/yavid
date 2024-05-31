package by.yavid.Service.Report.Statistic.TypeProduction;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiningRoomRangeTypeProduction implements RangeTypeProduction {

    @Value("${ecad.report.startRangeDiningRoomModules}")
    private int startRangeDiningRoomModules;

    @Value("${ecad.report.finishRangeDiningRoomModules}")
    private int finishRangeDiningRoomModules;

    @Value("${ecad.report.startRangeModelsDiningRoomModules}")
    private int startRangeModelsDiningRoomModules;

    @Value("${ecad.report.finishRangeModelsDiningRoomModules}")
    private int finishRangeModelsDiningRoomModules;
    @Value("${ecad.report.individualModule}")
    private int individualModule;


    @Override
    public Boolean isRangeTypeProduction(int codModel, int typeProduction) {
        if ((codModel >= startRangeModelsDiningRoomModules && codModel <= finishRangeModelsDiningRoomModules) &&
            ((typeProduction >= startRangeDiningRoomModules && typeProduction <= finishRangeDiningRoomModules) || typeProduction == individualModule)) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public TypeProduction getTypeProdction() {
        return TypeProduction.DiningRoom;
    }
}
