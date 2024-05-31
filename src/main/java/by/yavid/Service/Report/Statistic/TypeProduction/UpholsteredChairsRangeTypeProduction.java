package by.yavid.Service.Report.Statistic.TypeProduction;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UpholsteredChairsRangeTypeProduction implements RangeTypeProduction {

    @Value("${ecad.report.startRangeUpholsteredChairsModules}")
    private int startRangeUpholsteredChairsModules;

    @Value("${ecad.report.finishRangeUpholsteredChairsModules}")
    private int finishRangeUpholsteredChairsModules;

    @Value("${ecad.report.startRangeModelsDiningRoomModules}")
    private int startRangeModelsDiningRoomModules;

    @Value("${ecad.report.finishRangeModelsDiningRoomModules}")
    private int finishRangeModelsDiningRoomModules;



    @Override
    public Boolean isRangeTypeProduction(int codModel, int typeProduction) {
        if ((codModel >= startRangeModelsDiningRoomModules && codModel <= finishRangeModelsDiningRoomModules) &&
            (typeProduction >= startRangeUpholsteredChairsModules && typeProduction <= finishRangeUpholsteredChairsModules)) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public TypeProduction getTypeProdction() {
        return TypeProduction.UpholsteredChairs;
    }
}
