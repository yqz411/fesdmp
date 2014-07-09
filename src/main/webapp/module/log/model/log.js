Ext.define('Bjfu.log.model.log',{
	extend : 'Ext.data.Model',
	fields : [{
    	name : 'id',   					
    	type : 'int' //日志编号
    },{
    	name : 'businessType',   					
    	type : 'int' //业务类型
    },{
    	name : 'operateType',   					
    	type : 'int' //操作类型
    },{
    	name : 'userName',   					
    	type : 'string' //用户名
    },{
    	name : 'operateContent',   					
    	type : 'string' //操作内容
    },{
    	name : 'operateTime',
    	type : 'string' //操作时间	
    },{
    	name : 'userSourceIp', 
    	type : 'string' //用户源地址
	},{
		name : 'operateTypeName', 
    	type : 'string' 
	},{
		name : 'businessTypeName', 
    	type : 'string' 
	}
	]
});
