package com.ming.timer.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class QrtzSimpleTriggers {

  private String schedName;
  @Id
  private String triggerName;
  private String triggerGroup;
  private Long repeatCount;
  private Long repeatInterval;
  private Long timesTriggered;


}
