Quartz将Job保存在数据库中所需表的说明 http://www.cnblogs.com/Rozdy/p/4269762.html



    public static String rtp(String query, String tablePrefix, String schedNameLiteral) {  
        return MessageFormat.format(query, new Object[]{tablePrefix, schedNameLiteral});  
    }  

StdSchedulerFactory.java   
instanceId 这么生成的。  

 if (schedInstId.equals(AUTO_GENERATE_INSTANCE_ID)) {  
            autoId = true;  
            instanceIdGeneratorClass = cfg.getStringProperty(  
                    PROP_SCHED_INSTANCE_ID_GENERATOR_CLASS,  
                    "org.quartz.simpl.SimpleInstanceIdGenerator");  
        }  
        
public class SimpleInstanceIdGenerator implements InstanceIdGenerator {    
    public String generateInstanceId() throws SchedulerException {  
        try {   
            //最好部署在不同服务器上   
            return InetAddress.getLocalHost().getHostName() + System.currentTimeMillis();  
        } catch (Exception e) {  
            throw new SchedulerException("Couldn't get host name!", e);  
        }  
    }  
}  


实例化在 StdJDBCDelegate.java initialize() 方法里  

# spring-quartz-cluster-sample

Spring整合Quartz基于数据库的分布式定时任务，可动态添加、删除、修改定时任务。

创建quartz数据库

执行src/main/resources/scripts下的建表语句

执行src/main/resources/create-schema.sql语句

修改数据库连接信息

# Screenshots

![](Screenshots/2016-08-04_161337.png)
![](Screenshots/2016-08-04_161310.png)
