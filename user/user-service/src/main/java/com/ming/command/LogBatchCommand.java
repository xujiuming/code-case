package com.ming.command;

import com.ming.entity.Log;
import com.ming.server.ILogController;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

import java.util.List;
/**批量查询的 命令
 * @see com.netflix.hystrix.HystrixCommand
*@author ming
*@date 2017-10-11 18:14
*/
public class LogBatchCommand extends HystrixCommand<List<Log>>{

    private RestTemplate restTemplate;
    private List<Long> ids;
    public LogBatchCommand(RestTemplate restTemplate,List<Long> ids) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("logBatch")));
        this.restTemplate = restTemplate;
        this.ids= ids;
    }


    @Override
    protected List<Log> run() throws Exception {
        return  restTemplate.getForObject("http://COMMON-SERVICE/log/list?ids=1&ids=2",List.class);
    }
}
