<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.name" placeholder="姓名"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getJobList" plain>查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleAdd" plain>新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="data" highlight-current-row v-loading="listLoading" @selection-change="selsChange"
                  style="width: 100%;">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="jobname" label="任务名称" width="200" sortable></el-table-column>
            <el-table-column prop="triggertype" label="表达式类型" width="200" sortable></el-table-column>
            <el-table-column prop="triggerexpression" label="表达式" width="250" sortable></el-table-column>
            <el-table-column prop="jobdesc" label="任务描述" width="460" sortable></el-table-column>
            <el-table-column label="操作" width="400">
                <template slot-scope="scope">
                    <!-- <el-button size="small" @click="handleEdit(scope.$index, scope.row)">查看</el-button>
                     <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>-->
                    <el-button size="small" @click="handlePause(scope.$index, scope.row)">暂停</el-button>
                    <el-button size="small" @click="handleResume(scope.$index, scope.row)">重启</el-button>
                    <el-button size="small" @click="handleRun(scope.$index, scope.row)">立即运行</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                </template>
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

        <!--编辑界面-->
        <el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
                <el-form-item label="job名称" prop="jobName">
                    <el-input v-model="addForm.jobName" auto-complete="off"></el-input>
                </el-form-item>

                <el-form-item label="定时器表达式类型">
                    <el-radio-group v-model="addForm.triggerType">
                        <el-radio class="radio" label="SIMPLE">简单模式</el-radio>
                        <el-radio class="radio" label="CRON">CRON模式</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="定时器表达式">
                    <el-input v-model="addForm.triggerExpression"></el-input>
                </el-form-item>
                <el-form-item label="job描述" prop="jobDesc">
                    <el-input type="textarea" v-model="addForm.jobDesc" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
            <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
                <el-form-item label="job名称" prop="jobName">
                    <el-input v-model="addForm.jobName" auto-complete="off"></el-input>
                </el-form-item>

                <el-form-item label="表达式类型">
                    <el-radio-group v-model="addForm.triggerType">
                        <el-radio-button label="SIMPLE"></el-radio-button>
                        <el-radio-button label="CRON"></el-radio-button>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="定时器表达式">
                    <el-input v-model="addForm.triggerExpression"></el-input>
                </el-form-item>
                <el-form-item label="job描述" prop="jobDesc">
                    <el-input type="textarea" v-model="addForm.jobDesc" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>


    </section>
</template>

<script>
    import {createJob, deleteJob, detailJob, getJobPage,runJob,pauseJob,resumeJob} from '../../api/api';

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
                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                editFormRules: {
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ]
                },
                //编辑界面数据
                editForm: {
                    id: 0,
                    jobName: '',
                    jobDesc: '',
                    triggerType: 'SIMPLE',
                    triggerExpression: '',
                },

                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                addFormRules: {
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ]
                },
                //新增界面数据
                addForm: {
                    jobName: '',
                    jobDesc: '',
                    triggerType: 'SIMPLE',
                    triggerExpression: '',
                }
            }
        },
        methods: {


            //获取用户列表
            getJobList() {
                let para = {
                    pageNo: this.currentPage,
                    pageSize: this.pageSize,
                    name: this.filters.name
                };
                this.listLoading = true;
                getJobPage(para).then((res) => {
                    this.data = res.data;
                    this.listLoading = false;
                });
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.currentPage = 1;
                this.getJobList();
            },
            handlePageCurrentChange(val) {
                this.currentPage = val;
                this.getJobList();
            },
            selsChange: function (sels) {
                this.sels = sels;
            },

            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = {jobName: row.jobname};
                    console.log(para);
                    deleteJob(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.reload();
                    });
                }).catch(() => {

                });
            },

            handleRun: function (index, row) {
                let para = {jobName: row.jobname};
                console.log(para);
                runJob(para).then((res) => {
                    this.listLoading = false;
                    //NProgress.done();
                    this.$message({
                        message: '点火成功',
                        type: 'success'
                    });
                    this.reload();
                });

            },
            handlePause:function (index, row) {
                let para = {jobName: row.jobname};
                console.log(para);
                pauseJob(para).then((res) => {
                    this.listLoading = false;
                    //NProgress.done();
                    this.$message({
                        message: '暂停成功',
                        type: 'success'
                    });
                    this.reload();
                });

            },
            handleResume:function (index, row) {
                let para = {jobName: row.jobname};
                console.log(para);
                resumeJob(para).then((res) => {
                    this.listLoading = false;
                    //NProgress.done();
                    this.$message({
                        message: '重启成功',
                        type: 'success'
                    });
                    this.reload();
                });

            },
            //显示编辑界面
            handleEdit: function (index, row) {
                let para = {jobName: row.jobname};
                detailJob(para).then((res) => {
                    this.editForm.jobName = res.data.jobname;
                });
                console.log(this.editForm);
                this.editFormVisible = true;
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
            },
            //编辑
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.editForm);
                            editUser(para).then((res) => {
                                this.editLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.reload();
                            });
                        });
                    }
                });
            },
            //新增
            addSubmit: function () {
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.addLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.addForm);
                            console.log(para)
                            createJob(para).then((res) => {
                                this.addLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.reload();
                            });
                        });
                    }
                });
            },
        },
        mounted() {
            this.getJobList();
        }
    }

</script>

<style scoped>

</style>