package com.dhy.provider.filter;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobAPIFactory;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobOperateAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobStatisticsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.domain.JobBriefInfo;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.dangdang.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 * job健康自检任务
 * @author lvaolin
 */
@Component
@Slf4j
public class JobSelfCheckingServiceImpl  implements SimpleJob {

    @Autowired
    ZookeeperRegistryCenter zookeeperRegistryCenter;
    JobOperateAPI jobOperateAPI;
    JobStatisticsAPI jobStatisticsAPI;
    String[] jobNames= new String[]{
    };
    @Override
    public void execute(ShardingContext shardingContext) {
        //ping 依赖的服务
        try {
            if (jobOperateAPI == null) {
                jobOperateAPI = JobAPIFactory.createJobOperateAPI(zookeeperRegistryCenter.getClient().getZookeeperClient().getCurrentConnectionString(),zookeeperRegistryCenter.getClient().getNamespace(), Optional.absent());
            }
            if (jobStatisticsAPI == null) {
                jobStatisticsAPI = JobAPIFactory.createJobStatisticsAPI(zookeeperRegistryCenter.getClient().getZookeeperClient().getCurrentConnectionString(),zookeeperRegistryCenter.getClient().getNamespace(), Optional.absent());
            }

            //如果成功 将当前服务中失效的job 置于生效状态
            enableJob(jobNames);
            log.error("依赖服务成功，job正常运行中");
        }catch (Exception e){
            //如果失败 将当前服务中生效的job 置于失效状态
            disableJob(jobNames);
            log.error("依赖服务失败，job已暂停运行"+e.getMessage());
        }
    }

    void disableJob(String... jobNames){
        for (String jobName:jobNames) {
            JobBriefInfo jobBriefInfo = jobStatisticsAPI.getJobBriefInfo(jobName);
            if(!jobBriefInfo.getStatus().equals(JobBriefInfo.JobStatus.DISABLED)){
                jobOperateAPI.disable(Optional.of(jobName), Optional.<String>absent());
            }
        }
    }

    void enableJob(String... jobNames){
        for (String jobName:jobNames) {
            JobBriefInfo jobBriefInfo = jobStatisticsAPI.getJobBriefInfo(jobName);
            if(!jobBriefInfo.getStatus().equals(JobBriefInfo.JobStatus.OK)){
                jobOperateAPI.enable(Optional.of(jobName), Optional.<String>absent());
            }
        }

    }

}
