package com.ming.timer.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class QrtzCronTriggers {

  private String schedName;
  @Id
  private String triggerName;
  private String triggerGroup;
  private String cronExpression;
  private String timeZoneId;


}
