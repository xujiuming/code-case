package com.ming.command;

import com.google.common.collect.Lists;
import com.ming.entity.Log;
import com.ming.server.ILogController;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCommand;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**实现 请求合并 命令
 * @see com.netflix.hystrix.contrib.javanica.collapser.CommandCollapser
*@author ming
*@date 2017-10-11 18:15
*/
public class LogCollapseCommand extends HystrixCollapser<List<Log>,Log,Long> {
    /**
     * 请求接口
     * */
    private ILogController logController;
    /**
     *  单个id
     * */
    private Long id;

    public LogCollapseCommand(ILogController logController, Long id) {
        super(HystrixCollapser.Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("collapse")));
        this.logController = logController;
        this.id = id;
    }

    /** 需要合并的请求参数
    *@author ming
    *@date 2017-10-11 14:40
    */
    @Override
    public Long getRequestArgument() {
        return id;
    }

    /**
     * 创建合并请求命令 并且发起请求
    *@author ming
    *@date 2017-10-11 14:39
    */
    @Override
    protected HystrixCommand<List<Log>> createCommand(Collection<CollapsedRequest<Log, Long>> collapsedRequests) {
        List<Long> ids = Lists.newArrayList();
        ids.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toSet()));
        return new LogBatchCommand(logController,ids);
    }

    /**
     * 将返回结果拆分到 每个请求
    *@author ming
    *@date 2017-10-11 14:39
    */
    @Override
    protected void mapResponseToRequests(List<Log> batchResponse, Collection<CollapsedRequest<Log, Long>> collapsedRequests) {
        int count = 0 ;
        for (CollapsedRequest<Log, Long> request : collapsedRequests) {
            Log log = batchResponse.get(count++);
            request.setResponse(log);
        }
    }
}
