package by.yavid.Job;

import by.yavid.Service.Bazis.SyncWith3CadService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
public class SyncBaseMaterialJob implements Job {

    @Autowired
    SyncWith3CadService syncWith3CadService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //syncWith3CadService.SyncMaterial();
        //syncWith3CadService.SyncFasad();
        //syncWith3CadService.SyncHandles();
        //syncWith3CadService.SyncFurniture();
    }
}
