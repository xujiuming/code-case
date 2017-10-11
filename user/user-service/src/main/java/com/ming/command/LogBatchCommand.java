package com.ming.command;

import com.ming.entity.Log;
import com.ming.server.ILogController;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.List;
/**批量查询的 命令
 * @see com.netflix.hystrix.HystrixCommand
*@author ming
*@date 2017-10-11 18:14
*/
public class LogBatchCommand extends HystrixCommand<List<Log>>{

    private ILogController logController;
    private List<Long> ids;
    protected LogBatchCommand(ILogController logController,List<Long> ids) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("logBatch")));
        this.logController = logController;
        this.ids= ids;
    }


    @Override
    protected List<Log> run() throws Exception {
        return logController.findLogListByIds(ids);
    }
}
