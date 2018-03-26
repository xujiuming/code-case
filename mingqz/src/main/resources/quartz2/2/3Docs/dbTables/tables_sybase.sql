/*==============================================================================================*/
/* Quartz database tables creation script for Sybase ASE 12.5 */
/* Written by Pertti Laiho (email: pertti.laiho@deio.net), 9th May 2003 */
/* */
/* Compatible with Quartz version 1.1.2 */
/* */
/* Sybase ASE works ok with the SybaseDelegate delegate class. That means in your Quartz properties */
/* file, you'll need to set: */
/* org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.SybaseDelegate */
/*==============================================================================================*/

USE your_db_name_here
go

/*==============================================================================*/
/* Clear all tables: */
/*==============================================================================*/

IF OBJECT_ID('QRTZ_FIRED_TRIGGERS') IS NOT NULL
DELETE FROM QRTZ_FIRED_TRIGGERS
            go
IF OBJECT_ID('QRTZ_PAUSED_TRIGGER_GRPS') IS NOT NULL
DELETE FROM QRTZ_PAUSED_TRIGGER_GRPS
            go
IF OBJECT_ID('QRTZ_SCHEDULER_STATE') IS NOT NULL
DELETE FROM QRTZ_SCHEDULER_STATE
            go
IF OBJECT_ID('QRTZ_LOCKS') IS NOT NULL
DELETE FROM QRTZ_LOCKS
            go
IF OBJECT_ID('QRTZ_SIMPLE_TRIGGERS') IS NOT NULL
DELETE FROM QRTZ_SIMPLE_TRIGGERS
            go
IF OBJECT_ID('QRTZ_SIMPROP_TRIGGERS') IS NOT NULL
DELETE FROM QRTZ_SIMPROP_TRIGGERS
            go
IF OBJECT_ID('QRTZ_CRON_TRIGGERS') IS NOT NULL
DELETE FROM QRTZ_CRON_TRIGGERS
            go
IF OBJECT_ID('QRTZ_BLOB_TRIGGERS') IS NOT NULL
DELETE FROM QRTZ_BLOB_TRIGGERS
            go
IF OBJECT_ID('QRTZ_TRIGGERS') IS NOT NULL
DELETE FROM QRTZ_TRIGGERS
            go
IF OBJECT_ID('QRTZ_JOB_DETAILS') IS NOT NULL
DELETE FROM QRTZ_JOB_DETAILS
            go
IF OBJECT_ID('QRTZ_CALENDARS') IS NOT NULL
DELETE FROM QRTZ_CALENDARS
            go

/*==============================================================================*/
/* Drop constraints: */
/*==============================================================================*/

ALTER TABLE QRTZ_TRIGGERS
  DROP constraint FK_triggers_job_details
go

ALTER TABLE QRTZ_CRON_TRIGGERS
  DROP constraint FK_cron_triggers_triggers
go

ALTER TABLE QRTZ_SIMPLE_TRIGGERS
  DROP constraint FK_simple_triggers_triggers
go

ALTER TABLE QRTZ_SIMPROP_TRIGGERS
  DROP constraint FK_simprop_triggers_triggers
go

ALTER TABLE QRTZ_BLOB_TRIGGERS
  DROP constraint FK_blob_triggers_triggers
go

/*==============================================================================*/
/* Drop tables: */
/*==============================================================================*/

DROP TABLE QRTZ_FIRED_TRIGGERS
go
DROP TABLE QRTZ_PAUSED_TRIGGER_GRPS
go
DROP TABLE QRTZ_SCHEDULER_STATE
go
DROP TABLE QRTZ_LOCKS
go
DROP TABLE QRTZ_SIMPLE_TRIGGERS
go
DROP TABLE QRTZ_SIMPROP_TRIGGERS
go
DROP TABLE QRTZ_CRON_TRIGGERS
go
DROP TABLE QRTZ_BLOB_TRIGGERS
go
DROP TABLE QRTZ_TRIGGERS
go
DROP TABLE QRTZ_JOB_DETAILS
go
DROP TABLE QRTZ_CALENDARS
go

/*==============================================================================*/
/* Create tables: */
/*==============================================================================*/

CREATE TABLE QRTZ_CALENDARS (
  SCHED_NAME    VARCHAR(120) NOT NULL,
  CALENDAR_NAME VARCHAR(80)  NOT NULL,
  CALENDAR image NOT NULL
)
  go

CREATE TABLE QRTZ_CRON_TRIGGERS (
  SCHED_NAME      VARCHAR(120) NOT NULL,
  TRIGGER_NAME    VARCHAR(80)  NOT NULL,
  TRIGGER_GROUP   VARCHAR(80)  NOT NULL,
  CRON_EXPRESSION VARCHAR(120) NOT NULL,
  TIME_ZONE_ID    VARCHAR(80)  NULL,
)
  go

CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS (
  SCHED_NAME    VARCHAR(120) NOT NULL,
  TRIGGER_GROUP VARCHAR(80)  NOT NULL,
)
  go

CREATE TABLE QRTZ_FIRED_TRIGGERS (
  SCHED_NAME        VARCHAR(120)   NOT NULL,
  ENTRY_ID          VARCHAR(95)    NOT NULL,
  TRIGGER_NAME      VARCHAR(80)    NOT NULL,
  TRIGGER_GROUP     VARCHAR(80)    NOT NULL,
  INSTANCE_NAME     VARCHAR(80)    NOT NULL,
  FIRED_TIME        NUMERIC(13, 0) NOT NULL,
  SCHED_TIME        NUMERIC(13, 0) NOT NULL,
  PRIORITY          INT            NOT NULL,
  STATE             VARCHAR(16)    NOT NULL,
  JOB_NAME          VARCHAR(80)    NULL,
  JOB_GROUP         VARCHAR(80)    NULL,
  IS_NONCONCURRENT  BIT            NOT NULL,
  REQUESTS_RECOVERY BIT            NOT NULL,
)
  go

CREATE TABLE QRTZ_SCHEDULER_STATE (
  SCHED_NAME        VARCHAR(120)   NOT NULL,
  INSTANCE_NAME     VARCHAR(80)    NOT NULL,
  LAST_CHECKIN_TIME NUMERIC(13, 0) NOT NULL,
  CHECKIN_INTERVAL  NUMERIC(13, 0) NOT NULL,
)
  go

CREATE TABLE QRTZ_LOCKS (
  SCHED_NAME VARCHAR(120) NOT NULL,
  LOCK_NAME  VARCHAR(40)  NOT NULL,
)
  go


CREATE TABLE QRTZ_JOB_DETAILS (
  SCHED_NAME        VARCHAR(120) NOT NULL,
  JOB_NAME          VARCHAR(80)  NOT NULL,
  JOB_GROUP         VARCHAR(80)  NOT NULL,
  DESCRIPTION       VARCHAR(120) NULL,
  JOB_CLASS_NAME    VARCHAR(128) NOT NULL,
  IS_DURABLE        BIT          NOT NULL,
  IS_NONCONCURRENT  BIT          NOT NULL,
  IS_UPDATE_DATA    BIT          NOT NULL,
  REQUESTS_RECOVERY BIT          NOT NULL,
  JOB_DATA image NULL
)
  go

CREATE TABLE QRTZ_SIMPLE_TRIGGERS (
  SCHED_NAME      VARCHAR(120)   NOT NULL,
  TRIGGER_NAME    VARCHAR(80)    NOT NULL,
  TRIGGER_GROUP   VARCHAR(80)    NOT NULL,
  REPEAT_COUNT    NUMERIC(13, 0) NOT NULL,
  REPEAT_INTERVAL NUMERIC(13, 0) NOT NULL,
  TIMES_TRIGGERED NUMERIC(13, 0) NOT NULL
)
  go

CREATE TABLE QRTZ_SIMPROP_TRIGGERS
(
  SCHED_NAME    VARCHAR(120)   NOT NULL,
  TRIGGER_NAME  VARCHAR(200)   NOT NULL,
  TRIGGER_GROUP VARCHAR(200)   NOT NULL,
  STR_PROP_1    VARCHAR(512)   NULL,
  STR_PROP_2    VARCHAR(512)   NULL,
  STR_PROP_3    VARCHAR(512)   NULL,
  INT_PROP_1    INT            NULL,
  INT_PROP_2    INT            NULL,
  LONG_PROP_1   NUMERIC(13, 0) NULL,
  LONG_PROP_2   NUMERIC(13, 0) NULL,
  DEC_PROP_1    NUMERIC(13, 4) NULL,
  DEC_PROP_2    NUMERIC(13, 4) NULL,
  BOOL_PROP_1   BIT            NOT NULL,
  BOOL_PROP_2   BIT            NOT NULL
)
  go

CREATE TABLE QRTZ_BLOB_TRIGGERS (
  SCHED_NAME    VARCHAR(120) NOT NULL,
  TRIGGER_NAME  VARCHAR(80)  NOT NULL,
  TRIGGER_GROUP VARCHAR(80)  NOT NULL,
  BLOB_DATA image NULL
)
  go

CREATE TABLE QRTZ_TRIGGERS (
  SCHED_NAME     VARCHAR(120)   NOT NULL,
  TRIGGER_NAME   VARCHAR(80)    NOT NULL,
  TRIGGER_GROUP  VARCHAR(80)    NOT NULL,
  JOB_NAME       VARCHAR(80)    NOT NULL,
  JOB_GROUP      VARCHAR(80)    NOT NULL,
  DESCRIPTION    VARCHAR(120)   NULL,
  NEXT_FIRE_TIME NUMERIC(13, 0) NULL,
  PREV_FIRE_TIME NUMERIC(13, 0) NULL,
  PRIORITY       INT            NULL,
  TRIGGER_STATE  VARCHAR(16)    NOT NULL,
  TRIGGER_TYPE   VARCHAR(8)     NOT NULL,
  START_TIME     NUMERIC(13, 0) NOT NULL,
  END_TIME       NUMERIC(13, 0) NULL,
  CALENDAR_NAME  VARCHAR(80)    NULL,
  MISFIRE_INSTR  SMALLINT       NULL,
  JOB_DATA image NULL
)
  go

/*==============================================================================*/
/* Create primary key constraints: */
/*==============================================================================*/

ALTER TABLE QRTZ_CALENDARS
  ADD CONSTRAINT PK_qrtz_calendars PRIMARY KEY clustered (SCHED_NAME, CALENDAR_NAME)
go

ALTER TABLE QRTZ_CRON_TRIGGERS
  ADD CONSTRAINT PK_qrtz_cron_triggers PRIMARY KEY clustered (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
go

ALTER TABLE QRTZ_FIRED_TRIGGERS
  ADD CONSTRAINT PK_qrtz_fired_triggers PRIMARY KEY clustered (SCHED_NAME, ENTRY_ID)
go

ALTER TABLE QRTZ_PAUSED_TRIGGER_GRPS
  ADD CONSTRAINT PK_qrtz_paused_trigger_grps PRIMARY KEY clustered (SCHED_NAME, TRIGGER_GROUP)
go

ALTER TABLE QRTZ_SCHEDULER_STATE
  ADD CONSTRAINT PK_qrtz_scheduler_state PRIMARY KEY clustered (SCHED_NAME, INSTANCE_NAME)
go

ALTER TABLE QRTZ_LOCKS
  ADD CONSTRAINT PK_qrtz_locks PRIMARY KEY clustered (SCHED_NAME, LOCK_NAME)
go

ALTER TABLE QRTZ_JOB_DETAILS
  ADD CONSTRAINT PK_qrtz_job_details PRIMARY KEY clustered (SCHED_NAME, JOB_NAME, JOB_GROUP)
go

ALTER TABLE QRTZ_SIMPLE_TRIGGERS
  ADD CONSTRAINT PK_qrtz_simple_triggers PRIMARY KEY clustered (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
go

ALTER TABLE QRTZ_SIMPROP_TRIGGERS
  ADD CONSTRAINT PK_qrtz_simprop_triggers PRIMARY KEY clustered (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
go

ALTER TABLE QRTZ_TRIGGERS
  ADD CONSTRAINT PK_qrtz_triggers PRIMARY KEY clustered (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
go

ALTER TABLE QRTZ_BLOB_TRIGGERS
  ADD CONSTRAINT PK_qrtz_blob_triggers PRIMARY KEY clustered (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
go


/*==============================================================================*/
/* Create foreign key constraints: */
/*==============================================================================*/

ALTER TABLE QRTZ_CRON_TRIGGERS
  ADD CONSTRAINT FK_cron_triggers_triggers FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
go

ALTER TABLE QRTZ_SIMPLE_TRIGGERS
  ADD CONSTRAINT FK_simple_triggers_triggers FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
go

ALTER TABLE QRTZ_SIMPROP_TRIGGERS
  ADD CONSTRAINT FK_simprop_triggers_triggers FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
go

ALTER TABLE QRTZ_TRIGGERS
  ADD CONSTRAINT FK_triggers_job_details FOREIGN KEY (SCHED_NAME, JOB_NAME, JOB_GROUP)
REFERENCES QRTZ_JOB_DETAILS (SCHED_NAME, JOB_NAME, JOB_GROUP)
go

ALTER TABLE QRTZ_BLOB_TRIGGERS
  ADD CONSTRAINT FK_blob_triggers_triggers FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
go

/*==============================================================================*/
/* End of script. */
/*==============================================================================*/
