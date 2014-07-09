Ext.define('Bjfu.log.view.queryLog',{
	extend:'Ext.form.Panel',
	bodyPadding: 5,
	border:false,
	initComponent: function() {
    	var _this = this;
    	
    	var businessTypeStore = Ext.create('Ext.data.Store', {
    		autoLoad:true,
    	    fields: ['value', 'description'],
		  	 proxy : {
		        type : 'ajax',
		        url : Global_Path+'/smp/log!getBusinessTypeList',
		        reader : {
					type : 'json',
					root : 'result'
		        }
			}
    	});
    	var operateTypeStore = Ext.create('Ext.data.Store', {
    		autoLoad:true,
    	    fields: ['value', 'description'],
		  	 proxy : {
		        type : 'ajax',
		        url : Global_Path+'/smp/log!getOperateTypeList',
		        reader : {
					type : 'json',
					root : 'result'
		        }
			}
    	});
    	Ext.apply(_this, {
    		layout: {
		        type: 'table',
		        columns: 2
		    },
    	    defaults: {                  
    	    	margin:'15,0,5,5',
		        labelAlign : 'right'
    	    },
    	    items: [{
    	    	id : 'userName',
    	    	xtype : 'textfield',
    	        fieldLabel: '用户名',
    	        name: 'userName'
    	    },{
    	        id : 'userSourceIp',
    	    	xtype : 'textfield',
    	        fieldLabel: '用户源地址',
    	        name: 'userSourceIp'
    	    },{
    	    	id : 'businessType',
    	    	xtype : 'combo',
    	        fieldLabel : '业务类型',
    	        name : 'businessType',
    	        store : businessTypeStore,
    	        editable : false,
    	        mode : 'remote',
    	        displayField : 'description',
    	        valueField : 'value',
    	        emptyText : '请选择...'
    	    },{
    	    	id : 'operateType',
    	    	xtype : 'combo',
    	        fieldLabel: '操作类型',
    	        name: 'operateType',
    	        store : operateTypeStore,
    	        editable : false,
    	        mode : 'remote',
    	        displayField : 'description',
    	        valueField : 'value',
    	         emptyText : '请选择...'
    	    },{
    	    	id : 'startTime',
    	    	xtype : 'datefield',
				name :'startTime',
				fieldLabel : '操作时间开始',
				editable:false,
				format : 'Y-m-d H:i:s'
    	    },{
    	    	id : 'endTime',
    	    	xtype : 'datefield',
				name : 'endTime',
				fieldLabel : '操作时间结束',
				editable : false,
				format : 'Y-m-d H:i:s'
    	    },{
    	    	id : 'operateContent',
    	    	xtype : 'textfield',
    	        fieldLabel: '操作内容',
    	        name: 'operateContent'
    	    }]
    	});
    	_this.callParent(arguments);
	},
	buttonAlign:'center', 
    buttons: [{
        text: '重置',
        handler: function() {
            this.up('form').getForm().reset();
        }
    },{
        text: '查询',
        formBind: true,
        disabled: true,
        handler: function() {
            var form = this.up('form').getForm();
            var window = this.up('window');
            if (form.isValid()) {
            	var logValues = form.getValues();
                Ext.apply(Ext.getCmp('logListViewId').store.proxy.extraParams, {
                	userName : Ext.getCmp('userName').getValue(),
                	userSourceIp : Ext.getCmp('userSourceIp').getValue(),
                	businessType : Ext.getCmp('businessType').getValue(),
                	operateContent : Ext.getCmp('operateContent').getValue(),
                	startTime : Ext.getCmp('startTime').getValue(),
                	endTime : Ext.getCmp('endTime').getValue(),
                	operateType : Ext.getCmp('operateType').getValue()
                });
				Ext.getCmp('logListViewId').store.loadPage(1);
				window.close(); 
            }
        }
    }]
});

