-- 项目需要基础 表格

-- job执行日志
-- auto-generated definition
create table qrtz_job_execute_log
(
  id                  serial                not null
    constraint boss_qrtz_job_execute_log_pkey
    primary key,
  job_name            varchar(100)          not null,
  job_class           varchar(100)          not null,
  content             varchar(1000),
  execute_time_millis bigint                not null,
  gmt_create          timestamp             not null,
  gmt_modified        timestamp             not null,
  is_deleted          boolean default false not null
);

create unique index boss_qrtz_job_execute_log_id_uindex
  on qrtz_job_execute_log (id);

