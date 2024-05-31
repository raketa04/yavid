package by.yavid.Service.Report.Statistic.TypeProduction;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UnupholsteredChairsRangeTypeProduction implements RangeTypeProduction {

    @Value("${ecad.report.startRangeUnupholsteredChairsModules}")
    private int startRangeUnupholsteredChairsModules;

    @Value("${ecad.report.finishRangeUnupholsteredChairsModules}")
    private int finishRangeUnupholsteredChairsModules;

    @Value("${ecad.report.startRangeModelsDiningRoomModules}")
    private int startRangeModelsDiningRoomModules;

    @Value("${ecad.report.finishRangeModelsDiningRoomModules}")
    private int finishRangeModelsDiningRoomModules;



    @Override
    public Boolean isRangeTypeProduction(int codModel, int typeProduction) {
        if ((codModel >= startRangeModelsDiningRoomModules && codModel <= finishRangeModelsDiningRoomModules) &&
            (typeProduction >= startRangeUnupholsteredChairsModules && typeProduction <= finishRangeUnupholsteredChairsModules)) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public TypeProduction getTypeProdction() {
        return TypeProduction.UnupholsteredChairs;
    }
}
