package com.ming.scheduler.controller;

import com.ming.base.BaseController;
import com.ming.base.quartz.BaseProxyJob;
import com.ming.base.scheduler.SchedulerInstance;
import com.ming.core.utils.GroovyUtils;
import com.ming.core.utils.SpringBeanManager;
import com.ming.scheduler.controller.vo.JobVO;
import com.ming.scheduler.service.api.SchedulerService;
import com.ming.scheduler.service.api.quartz.QrtzJobDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 定时器管理控制器
 *
 * @author ming
 * @date 2017-11-08 15:06
 */
@RestController
@RequestMapping("scheduler-manager")
public class SchedulerManagerController extends BaseController {

    @Autowired
    private SchedulerInstance schedulerInstance;
    @Autowired
    private QrtzJobDetailsService qrtzJobDetailsService;

    @Autowired
    private SchedulerService schedulerService;

    /**
     * 使用已注册bean 创建 定时任务
     *
     * @author ming
     * @date 2017-11-08 18:06
     */
    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String create(Model model, @RequestBody JobVO jobVO) {
        schedulerService.create(jobVO);
        model.addAttribute("result", "创建定时任务成功");
        model.addAttribute("list", qrtzJobDetailsService.findAll());
        return "index";
    }

    /**
     * 获取定时任务page 分页
     *
     * @author ming
     * @date 2017-11-08 18:07
     */
    @GetMapping(value = "Page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String page(Model model) {
        model.addAttribute("list", qrtzJobDetailsService.findAll());
        return "index";
    }

    /**
     * 查看定时任务的详情
     *
     * @author ming
     * @date 2017-11-08 18:07
     */
    @GetMapping("detail")
    public String detail() {
        return "";
    }

    /**
     * 删除定时任务
     *
     * @author ming
     * @date 2017-11-08 18:07
     */
    @PostMapping(value = "delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String delete(Model model, @RequestParam String jobName, @RequestParam String jobGroup) {
        schedulerService.delete(jobName, jobGroup);
        model.addAttribute("result", "删除" + jobName + "任务成功");
        model.addAttribute("list", qrtzJobDetailsService.findAll());

        return "index";
    }

    /**
     * 修改定时任务
     *
     * @author ming
     * @date 2017-11-08 18:08
     */
    @PostMapping(value = "update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String update(Model model, @RequestBody JobVO jobVO) {
        schedulerService.update(jobVO);
        model.addAttribute("result", "修改" + jobVO.getJobName() + "成功");
        model.addAttribute("list", qrtzJobDetailsService.findAll());
        return "index";
    }

    /**
     * 暂停
     *
     * @author ming
     * @date 2017-11-09 14:27
     */
    @PostMapping(value = "pause", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String pause(Model model, @RequestBody JobVO jobVO) {
        schedulerService.pause(jobVO);
        model.addAttribute("result", jobVO + "暂停");
        model.addAttribute("list", qrtzJobDetailsService.findAll());

        return "index";
    }

    /**
     * 重启暂停的任务
     *
     * @author ming
     * @date 2017-11-09 14:27
     */
    @PostMapping(value = "resume", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String resume(Model model, @RequestBody JobVO jobVo) {
        schedulerService.resume(jobVo);
        model.addAttribute("result", jobVo + "重启成功");
        model.addAttribute("list", qrtzJobDetailsService.findAll());
        return "index";
    }

    /**
     * 立即运行
     *
     * @author ming
     * @date 2017-11-09 14:28
     */
    @PostMapping(value = "run", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String run(Model model, @RequestBody JobVO jobVO) {
        schedulerService.run(jobVO.getJobName(), jobVO.getJobGroup());
        model.addAttribute("result", jobVO + "立即运行");
        model.addAttribute("list", qrtzJobDetailsService.findAll());

        return "index";
    }

    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String test() {
        BaseProxyJob job = null;
        try {
            job = GroovyUtils.newInsta();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        assert job != null;
        SpringBeanManager.registerBean("com/ming", job.getClass());
        //schedulerInstance.create("ming", "ming", SchedulerInstance.TriggerType.SIMPLE, "1");
        return "index";
    }

    @GetMapping(value = "ex", produces = MediaType.APPLICATION_JSON_VALUE)
    public String ex() {
        if (true) {
            throw new RuntimeException("测试异常");
        }
        return "ex";
    }

    @GetMapping(value = "json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object json() {
        return "jsontest";
    }

    @GetMapping(value = "tt", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String tt() {
        System.out.println("ssss");
        return "index";
    }
}
