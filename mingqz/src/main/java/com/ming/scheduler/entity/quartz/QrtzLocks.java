package com.ming.scheduler.entity.quartz;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 锁信息
 *
 * @author ming
 * @date 2017-11-09 14:50
 */
@Entity
@Table(name = "QRTZ_LOCKS")
@Data
public class QrtzLocks {
    @Id
    private String schedName;
    private String lockName;
}
