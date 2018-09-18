package net.aimeizi.quartz.quartz;

import com.kowalski.demo.IPAddressKowalski;
import net.aimeizi.quartz.model.ScheduleJob;
import net.aimeizi.quartz.vo.ScheduleJobVo;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.Environment;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.management.MalformedObjectNameException;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * author : fengjing
 * createTime : 2016-08-04
 * description : 同步任务工厂
 * version : 1.0
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SyncJobFactory extends QuartzJobBean {

//    @Autowired
//    Environment environment;

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(SyncJobFactory.class);

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        try {
            LOG.info("SyncJobFactory execute" + IPAddressKowalski.getIpAddressAndPort() + " port:"+IPAddressKowalski.getTomcatPort());
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        ScheduleJob scheduleJob = (ScheduleJob) mergedJobDataMap.get(ScheduleJobVo.JOB_PARAM_KEY);
        System.out.println("jobName:" + scheduleJob.getJobName() + "  " + scheduleJob);
        String url = scheduleJob.getUrl();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response;
        try {
            response = httpclient.execute(httpGet);
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
