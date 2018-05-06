import axios from 'axios';

//let base = 'http://localhost:11111';
let base = '';

export const requestLogin = params => { return axios.post(`${base}/login`, params).then(res => res.data); };

export const getUserList = params => { return axios.get(`${base}/user/list`, { params: params }); };

export const getUserListPage = params => { return axios.get(`${base}/user/listpage`, { params: params }); };

export const removeUser = params => { return axios.get(`${base}/user/remove`, { params: params }); };

export const batchRemoveUser = params => { return axios.get(`${base}/user/batchremove`, { params: params }); };

export const editUser = params => { return axios.get(`${base}/user/edit`, { params: params }); };

export const addUser = params => { return axios.get(`${base}/user/add`, { params: params }); };

// 获取执行日志
export const getJobExecuteLogPage = params => { return axios.get(`${base}/api/timer/log/page`, { params: params }); };
export const getJobPage = params => { return axios.get(`${base}/api/timer/job/page`, { params: params }); };
