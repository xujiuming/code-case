-- 
-- Apache Derby scripts by Steve Stewart, updated by Ronald Pomeroy
-- Based on Srinivas Venkatarangaiah's file for Cloudscape
-- 
-- Known to work with Apache Derby 10.0.2.1, or 10.6.2.1
--
-- Updated by Zemian Deng <saltnlight5@gmail.com> on 08/21/2011
--   * Fixed nullable fields on qrtz_simprop_triggers table. 
--   * Added Derby QuickStart comments and drop tables statements.
--
-- DerbyDB + Quartz Quick Guide:
-- * Derby comes with Oracle JDK! For Java6, it default install into C:/Program Files/Sun/JavaDB on Windows.
-- 1. Create a derby.properties file under JavaDB directory, and have the following:
--    derby.connection.requireAuthentication = true
--    derby.authentication.provider = BUILTIN
--    derby.user.quartz2=quartz2123
-- 2. Start the DB server by running bin/startNetworkServer script.
-- 3. On a new terminal, run bin/ij tool to bring up an SQL prompt, then run:
--    connect 'jdbc:derby://localhost:1527/quartz2;user=quartz2;password=quartz2123;create=true';
--    run 'quartz/docs/dbTables/tables_derby.sql';
-- Now in quartz.properties, you may use these properties:
--    org.quartz.dataSource.quartzDataSource.driver = org.apache.derby.jdbc.ClientDriver
--    org.quartz.dataSource.quartzDataSource.URL = jdbc:derby://localhost:1527/quartz2
--    org.quartz.dataSource.quartzDataSource.user = quartz2
--    org.quartz.dataSource.quartzDataSource.password = quartz2123
--

-- Auto drop and reset tables 
-- Derby doesn't support if exists condition on table drop, so user must manually do this step if needed to.
-- drop table qrtz_fired_triggers;
-- drop table qrtz_paused_trigger_grps;
-- drop table qrtz_scheduler_state;
-- drop table qrtz_locks;
-- drop table qrtz_simple_triggers;
-- drop table qrtz_simprop_triggers;
-- drop table qrtz_cron_triggers;
-- drop table qrtz_blob_triggers;
-- drop table qrtz_triggers;
-- drop table qrtz_job_details;
-- drop table qrtz_calendars;

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
  job_data          BLOB,
  PRIMARY KEY (sched_name, job_name, job_group)
);

CREATE TABLE qrtz_triggers (
  sched_name     VARCHAR(120) NOT NULL,
  trigger_name   VARCHAR(200) NOT NULL,
  trigger_group  VARCHAR(200) NOT NULL,
  job_name       VARCHAR(200) NOT NULL,
  job_group      VARCHAR(200) NOT NULL,
  description    VARCHAR(250),
  next_fire_time BIGINT,
  prev_fire_time BIGINT,
  priority       INTEGER,
  trigger_state  VARCHAR(16)  NOT NULL,
  trigger_type   VARCHAR(8)   NOT NULL,
  start_time     BIGINT       NOT NULL,
  end_time       BIGINT,
  calendar_name  VARCHAR(200),
  misfire_instr  SMALLINT,
  job_data       BLOB,
  PRIMARY KEY (sched_name, trigger_name, trigger_group),
  FOREIGN KEY (sched_name, job_name, job_group) REFERENCES qrtz_job_details (sched_name, job_name, job_group)
);

CREATE TABLE qrtz_simple_triggers (
  sched_name      VARCHAR(120) NOT NULL,
  trigger_name    VARCHAR(200) NOT NULL,
  trigger_group   VARCHAR(200) NOT NULL,
  repeat_count    BIGINT       NOT NULL,
  repeat_interval BIGINT       NOT NULL,
  times_triggered BIGINT       NOT NULL,
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
  sched_name    VARCHAR(120) NOT NULL,
  trigger_name  VARCHAR(200) NOT NULL,
  trigger_group VARCHAR(200) NOT NULL,
  str_prop_1    VARCHAR(512),
  str_prop_2    VARCHAR(512),
  str_prop_3    VARCHAR(512),
  int_prop_1    INT,
  int_prop_2    INT,
  long_prop_1   BIGINT,
  long_prop_2   BIGINT,
  dec_prop_1    NUMERIC(13, 4),
  dec_prop_2    NUMERIC(13, 4),
  bool_prop_1   VARCHAR(5),
  bool_prop_2   VARCHAR(5),
  PRIMARY KEY (sched_name, trigger_name, trigger_group),
  FOREIGN KEY (sched_name, trigger_name, trigger_group)
  REFERENCES qrtz_triggers (sched_name, trigger_name, trigger_group)
);

CREATE TABLE qrtz_blob_triggers (
  sched_name    VARCHAR(120) NOT NULL,
  trigger_name  VARCHAR(200) NOT NULL,
  trigger_group VARCHAR(200) NOT NULL,
  blob_data     BLOB,
  PRIMARY KEY (sched_name, trigger_name, trigger_group),
  FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES qrtz_triggers (sched_name, trigger_name, trigger_group)
);

CREATE TABLE qrtz_calendars (
  sched_name    VARCHAR(120) NOT NULL,
  calendar_name VARCHAR(200) NOT NULL,
  calendar      BLOB         NOT NULL,
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
  fired_time        BIGINT       NOT NULL,
  sched_time        BIGINT       NOT NULL,
  priority          INTEGER      NOT NULL,
  state             VARCHAR(16)  NOT NULL,
  job_name          VARCHAR(200),
  job_group         VARCHAR(200),
  is_nonconcurrent  VARCHAR(5),
  requests_recovery VARCHAR(5),
  PRIMARY KEY (sched_name, entry_id)
);

CREATE TABLE qrtz_scheduler_state
(
  sched_name        VARCHAR(120) NOT NULL,
  instance_name     VARCHAR(200) NOT NULL,
  last_checkin_time BIGINT       NOT NULL,
  checkin_interval  BIGINT       NOT NULL,
  PRIMARY KEY (sched_name, instance_name)
);

CREATE TABLE qrtz_locks
(
  sched_name VARCHAR(120) NOT NULL,
  lock_name  VARCHAR(40)  NOT NULL,
  PRIMARY KEY (sched_name, lock_name)
);

