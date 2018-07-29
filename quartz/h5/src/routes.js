import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import TimerTaskExecuteLogList from './views/log/TimerTaskExecuteLogList'
import TimerTaskList from './views/timer-task-manager/TimerTaskListBak'

let routes = [
    /*{
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },*/
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: '定时任务管理',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            {path: '/main', component: Main, name: '主页', hidden: true},
            {path: '/TimerTaskList', component: TimerTaskList, name: '定时任务'}/*,
            { path: '/form', component: Form, name: 'Form' },
            { path: '/user', component: user, name: '列表' },*/
        ]
    },
    {
        path: '/',
        component: Home,
        name: '日志',
        iconCls: 'fa fa-id-card-o',
        children: [
            {path: '/TimerTaskExecuteLogList', component: TimerTaskExecuteLogList, name: '定时任务执行日志'}/*,
            { path: '/page5', component: Page5, name: '页面5' }*/
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: {path: '/404'}
    }
];

export default routes;