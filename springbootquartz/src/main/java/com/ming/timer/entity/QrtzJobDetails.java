package com.ming.timer.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class QrtzJobDetails {

  private String schedName;
  @Id
  private String jobName;
  private String jobGroup;
  private String description;
  private String jobClassName;
  private String isDurable;
  private String isNonconcurrent;
  private String isUpdateData;
  private String requestsRecovery;
  private String jobData;


}
