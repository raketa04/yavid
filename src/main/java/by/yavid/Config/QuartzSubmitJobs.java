package by.yavid.Config;

import by.yavid.Service.Job.SyncBaseMaterialJob;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzSubmitJobs {

    @Bean(name = "SyncBaseMaterialStats")
    public JobDetailFactoryBean jobMemberStats() {
        return QuartzConfig.createJobDetail(SyncBaseMaterialJob.class, "Member Statistics Job");
    }

    @Bean(name = "SyncBaseMaterialStatsTrigger")
    public SimpleTriggerFactoryBean triggerMemberStats(@Qualifier("SyncBaseMaterialStats") JobDetail jobDetail) {
        return QuartzConfig.createTrigger(jobDetail, 120000, "Member Statistics Trigger");
    }
}


