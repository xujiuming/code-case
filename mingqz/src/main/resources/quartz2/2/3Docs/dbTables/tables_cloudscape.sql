# 
# Thanks to Srinivas Venkatarangaiah for submitting this file's contents
#
# In your Quartz properties file, you'll need to set 
# org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.CloudscapeDelegate
#
# Known to work with Cloudscape 3.6.4 (should work with others)
#


CREATE TABLE qrtz_job_details (
  sched_name        VARCHAR(120) NOT NULL,
  job_name          VARCHAR(200) NOT NULL,
  job_group         VARCHAR(200) NOT NULL,
  description       VARCHAR(250),
  job_class_name    VARCHAR(250) NOT NULL,
  is_durable        VARCHAR(5)   NOT NULL,
  is_nonconcurrent  VARCHAR(5)   NOT NULL,
  is_update_data    VARCHAR(5)   NOT NULL,
  requests_recovery VARCHAR(5)   NOT NULL,
  job_data          LONG VARBINARY,
  PRIMARY KEY (sched_name, job_name, job_group)
);

CREATE TABLE qrtz_triggers (
  sched_name    VARCHAR(120) NOT NULL,
  trigger_name  VARCHAR(200) NOT NULL,
  trigger_group VARCHAR(200) NOT NULL,
  job_name      VARCHAR(200) NOT NULL,
  job_group     VARCHAR(200) NOT NULL,
  description   VARCHAR(250),
  next_fire_time longint,
  prev_fire_time longint,
  priority      INTEGER,
  trigger_state VARCHAR(16)  NOT NULL,
  trigger_type  VARCHAR(8)   NOT NULL,
  start_time longint NOT NULL,
  end_time longint,
  calendar_name VARCHAR(200),
  misfire_instr SMALLINT,
  job_data      LONG VARBINARY,
  PRIMARY KEY (sched_name, trigger_name, trigger_group),
  FOREIGN KEY (sched_name, job_name, job_group) REFERENCES qrtz_job_details (sched_name, job_name, job_group)
);

CREATE TABLE qrtz_simple_triggers (
  sched_name    VARCHAR(120) NOT NULL,
  trigger_name  VARCHAR(200) NOT NULL,
  trigger_group VARCHAR(200) NOT NULL,
  repeat_count longint NOT NULL,
  repeat_interval longint NOT NULL,
  times_triggered longint NOT NULL,
  PRIMARY KEY (sched_name, trigger_name, trigger_group),
  FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES qrtz_triggers (sched_name, trigger_name, trigger_group)
);

CREATE TABLE qrtz_cron_triggers (
  sched_name      VARCHAR(120) NOT NULL,
  trigger_name    VARCHAR(200) NOT NULL,
  trigger_group   VARCHAR(200) NOT NULL,
  cron_expression VARCHAR(120) NOT NULL,
  time_zone_id    VARCHAR(80),
  PRIMARY KEY (sched_name, trigger_name, trigger_group),
  FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES qrtz_triggers (sched_name, trigger_name, trigger_group)
);

CREATE TABLE qrtz_simprop_triggers
(
  sched_name    VARCHAR(120)   NOT NULL,
  TRIGGER_NAME  VARCHAR(200)   NOT NULL,
  TRIGGER_GROUP VARCHAR(200)   NOT NULL,
  STR_PROP_1    VARCHAR(512)   NULL,
  STR_PROP_2    VARCHAR(512)   NULL,
  STR_PROP_3    VARCHAR(512)   NULL,
  INT_PROP_1    INT            NULL,
  INT_PROP_2    INT            NULL,
  LONG_PROP_1 longint NULL,
  LONG_PROP_2 longint NULL,
  DEC_PROP_1    NUMERIC(13, 4) NULL,
  DEC_PROP_2    NUMERIC(13, 4) NULL,
  BOOL_PROP_1   VARCHAR(5)     NULL,
  BOOL_PROP_2   VARCHAR(5)     NULL,
  PRIMARY KEY (sched_name, TRIGGER_NAME, TRIGGER_GROUP),
  FOREIGN KEY (sched_name, TRIGGER_NAME, TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS (sched_name, TRIGGER_NAME, TRIGGER_GROUP)
);

CREATE TABLE qrtz_blob_triggers (
  sched_name    VARCHAR(120) NOT NULL,
  trigger_name  VARCHAR(200) NOT NULL,
  trigger_group VARCHAR(200) NOT NULL,
  blob_data     LONG VARBINARY,
  PRIMARY KEY (sched_name, trigger_name, trigger_group),
  FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES qrtz_triggers (sched_name, trigger_name, trigger_group)
);

CREATE TABLE qrtz_calendars (
  sched_name    VARCHAR(120) NOT NULL,
  calendar_name VARCHAR(200) NOT NULL,
  calendar      LONG VARBINARY NOT NULL,
  PRIMARY KEY (sched_name, calendar_name)
);

CREATE TABLE qrtz_paused_trigger_grps
(
  sched_name    VARCHAR(120) NOT NULL,
  trigger_group VARCHAR(200) NOT NULL,
  PRIMARY KEY (sched_name, trigger_group)
);

CREATE TABLE qrtz_fired_triggers (
  sched_name        VARCHAR(120) NOT NULL,
  entry_id          VARCHAR(95)  NOT NULL,
  trigger_name      VARCHAR(200) NOT NULL,
  trigger_group     VARCHAR(200) NOT NULL,
  instance_name     VARCHAR(200) NOT NULL,
  fired_time longint NOT NULL,
  sched_time longint NOT NULL,
  priority          INTEGER      NOT NULL,
  state             VARCHAR(16)  NOT NULL,
  job_name          VARCHAR(200) NULL,
  job_group         VARCHAR(200) NULL,
  is_nonconcurrent  VARCHAR(5)   NULL,
  requests_recovery VARCHAR(5)   NULL,
  PRIMARY KEY (sched_name, entry_id)
);

CREATE TABLE qrtz_scheduler_state
(
  sched_name    VARCHAR(120) NOT NULL,
  instance_name VARCHAR(200) NOT NULL,
  last_checkin_time longint NOT NULL,
  checkin_interval longint NOT NULL,
  PRIMARY KEY (sched_name, instance_name)
);

CREATE TABLE qrtz_locks
(
  sched_name VARCHAR(120) NOT NULL,
  lock_name  VARCHAR(40)  NOT NULL,
  PRIMARY KEY (sched_name, lock_name)
);
