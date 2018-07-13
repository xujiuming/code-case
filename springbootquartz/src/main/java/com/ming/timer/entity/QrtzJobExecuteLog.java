package com.ming.timer.entity;


import com.ming.timer.base.orm.InId;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class QrtzJobExecuteLog extends InId {

  private String jobName;
  private String jobClass;
  private String content;
  private Long executeTimeMillis;


}
