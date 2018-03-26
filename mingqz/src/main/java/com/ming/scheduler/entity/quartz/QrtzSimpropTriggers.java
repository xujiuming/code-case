package com.ming.scheduler.entity.quartz;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ming
 * @date 2017-11-09 18:09
 */
@Entity
@Table(name = "QRTZ_SIMPROP_TRIGGERS")
@Data
public class QrtzSimpropTriggers {
    @Id
    private String schedName;
    private String triggerName;
    private String triggerGroup;
    private String strProp1;
    private String strProp2;
    private String strProp3;
    private long intProp1;
    private long intProp2;
    private long longProp1;
    private long longProp2;
    private double decProp1;
    private double decProp2;
    private String boolProp1;
    private String boolProp2;
}
