import axios from 'axios';

let base = '';

export const requestLogin = params => {
    return axios.post(`${base}/login`, params).then(res => res.data);
};

export const getUserList = params => {
    return axios.get(`${base}/user/list`, {params: params});
};

export const getUserListPage = params => {
    return axios.get(`${base}/user/listpage`, {params: params});
};

export const removeUser = params => {
    return axios.get(`${base}/user/remove`, {params: params});
};

export const batchRemoveUser = params => {
    return axios.get(`${base}/user/batchremove`, {params: params});
};

export const editUser = params => {
    return axios.get(`${base}/user/edit`, {params: params});
};

export const addUser = params => {
    return axios.get(`${base}/user/add`, {params: params});
};

// 获取执行日志
export const getJobExecuteLogPage = params => {
    return axios.get(`${base}/api/timer/log/page`, {params: params});
};
//job操作
export const getJobPage = params => {
    return axios.get(`${base}/api/timer/job/page`, {params: params});
};
//删除job
export const deleteJob = params => {
    return axios.delete(`${base}/api/timer/job`, {params: params});
};
//创建job
export const createJob = params => {
    return axios.post(`${base}/api/timer/job`, params);
};
//查看job详情
export const detailJob = params => {
    return axios.get(`${base}/api/timer/job`, {params: params});
};
//更新job
export const updateJob = params => {
    return axios.put(`${base}/api/timer/job`, {params: params});
};
//暂停job
export const pauseJob = params =>{
    return axios.put(`${base}/api/timer/job/pause`),{params:params};
};
//重启job
export const resumeJob = params =>{
    return axios.put(`${base}/api/timer/job/resume`),{params:params};
};
//立即执行job
export const runJob = params =>{
    return axios.put(`${base}/api/timer/job/run`),{params:params};
};