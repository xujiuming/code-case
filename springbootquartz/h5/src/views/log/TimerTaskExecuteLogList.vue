<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.name" placeholder="模糊搜索"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getLogs">查询</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="logs" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="id" label="id" width="60" sortable>
            </el-table-column>
            <el-table-column prop="jobName" label="任务名称" width="150" sortable>
            </el-table-column>
            <el-table-column prop="jobClass" label="任务实现类" width="250" sortable>
            </el-table-column>
            <el-table-column prop="content" label="任务执行结果" width="800" sortable>
            </el-table-column>
            <el-table-column prop="executeTimeMillis" label="任务执行耗时(ms)" min-width="150" sortable>
            </el-table-column>
        </el-table>



    </section>
</template>

<script>
    //import NProgress from 'nprogress'
    import { getJobExecuteLogPage } from '../../api/api';

    export default {
        data() {
            return {
                filters: {
                    name: ''
                },
                logs: [],
                total: 0,
                page: {
                    pageNum:1,
                    pageSize:10
                },
                listLoading: false,
                sels: [],//列表选中列

            }
        },
        methods: {


            //获取用户列表
            getLogs() {
                let para = {
                    page: this.page,
                    name: this.filters.name
                };
                this.listLoading = true;
                //NProgress.start();
                getJobExecuteLogPage(para).then((res) => {
                    console.log(res);
                    this.total = res.data.total;
                    this.logs = res.data.content;
                    this.listLoading = false;
                    //NProgress.done();
                });
            },
            //全选
            selsChange: function (sels) {
                this.sels = sels;
            },
        },

        mounted() {
            this.getLogs();
        }
    }

</script>

<style scoped>

</style>