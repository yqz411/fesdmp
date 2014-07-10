Ext.define('Bjfu.log.view.QueryLog',{
	extend:'Ext.form.Panel',
	bodyPadding: 5,
	border:false,
	initComponent: function() {
    	var me = this;
    	
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
    	Ext.apply(me, {
    		layout: {
		        type: 'table',
		        columns: 2
		    },
    	    defaults: {  
    	    	labelAlign:'right',
    	        margin: '3 20 3 0'
    	    },
		    defaultType: 'textfield',
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
    	    	xtype : 'datefield',
				fieldLabel : '开始时间',
				altFormats: 'Y-m-d',
				format : 'Y-m-d',
				id : 'createStartTime',
				name : 'createStartTime',
				maxValue : new Date(),
				editable : false,
				vtype : 'daterange',
				endDateField :"createEndTime"
    	    },{
    	    	xtype : 'datefield',
				fieldLabel : '结束时间',
				altFormats: 'Y-m-d',
				format : 'Y-m-d',
				id : 'createEndTime',
				name : 'createEndTime',
				maxValue : new Date(),
				vtype : 'daterange',
				editable : false,
				startDateField : "createStartTime"
    	    },{
    	    	id : 'operateContent',
    	    	xtype : 'textfield',
    	        fieldLabel: '操作内容',
    	        name: 'operateContent'
    	    }]
    	});
    	me.callParent(arguments);
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
		          	var viewId = this.up('form').listViewId;
		            var warningStr = JSON.stringify(this.up('form').getForm().getValues());
		            Ext.getCmp(viewId).getStore().load({
		               		params: {
		           				warningStr: warningStr
		           			}
		            });
		            this.up('window').close();
		        }
		    }]
});

