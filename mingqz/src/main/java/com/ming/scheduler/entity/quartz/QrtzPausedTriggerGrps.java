package com.ming.scheduler.entity.quartz;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 已经暂停的触发器组信息
 *
 * @author ming
 * @date 2017-11-09 14:49
 */
@Entity
@Table(name = "ORTZ_PAUSED_TRIGGER_GRPS")
@Data
public class QrtzPausedTriggerGrps {
    @Id
    private String schedName;
    private String triggerGroup;
}
