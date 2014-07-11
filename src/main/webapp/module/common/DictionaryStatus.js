Ext.define('Bjfu.fesdmp.BusinessType', {
    extend: 'Ext.data.Store',
    fields: ['val', 'name'],
    data : [
        {"val":"SYS_LOGIN", "name":"系统登录"},
        {"val":"SYS_LOGOUT", "name":"系统退出"},
        {"val":"SYS_OPERATE", "name":"系统操作"},
        {"val":"SYS_OTHERS", "name":"其他业务"}
    ]
});
Ext.define('Bjfu.fesdmp.OperatitonType', {
    extend: 'Ext.data.Store',
    fields: ['val', 'name'],
    data : [
        {"val":"ADD", "name":"增加"},
        {"val":"DELETE", "name":"删除"},
        {"val":"UPDATE", "name":"修改"},
        {"val":"QUERY", "name":"查询"},
        {"val":"OTHERS", "name":"其他"}
    ]
});