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
        <el-table :data="data" highlight-current-row v-loading="listLoading" @selection-change="selsChange"
                  style="width: 100%;">
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
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handlePageCurrentChange"
                :current-page="currentPage"
                :page-sizes="[10, 20, 50, 100]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="totalSize">
        </el-pagination>
    </section>


</template>

<script>
    //import NProgress from 'nprogress'
    import {getJobExecuteLogPage} from '../../api/api';

    export default {
        data() {
            return {
                filters: {
                    name: ''
                },
                data: [],
                totalSize: 0,
                pageSize: 10,
                currentPage: 1,
                listLoading: false,
                sels: [],//列表选中列

            }
        },
        methods: {


            //获取用户列表
            getLogs() {
                let para = {
                    pageNo: this.currentPage,
                    pageSize : this.pageSize,
                    name: this.filters.name
                };
                this.listLoading = true;
                //NProgress.start();
                getJobExecuteLogPage(para).then((res) => {
                    this.totalSize = res.data.totalPages;
                    this.currentPage = res.data.number;
                    this.pageSize = res.data.size;
                    this.data = res.data.content;
                    this.listLoading = false;
                    //NProgress.done();
                });
            },
            //全选
            selsChange: function (sels) {
                this.sels = sels;
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.currentPage = 1;
                this.getLogs();
            },
            handlePageCurrentChange(val) {
                this.currentPage = val;
                this.getLogs();
            }

        },

        mounted() {
            this.getLogs();
        }
    }

</script>

<style scoped>

</style>