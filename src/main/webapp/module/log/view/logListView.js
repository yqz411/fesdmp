Ext.define('Bjfu.log.view.logListView',{
	extend : 'Ext.grid.Panel',
	alias:'widget.logListView',
	id : 'logListViewId',
	forceFit : true,
	layout : 'fit',
    autoScroll: true,
	layoutConfig : {
		animate : true
	},
	search_cache: null,	  //用于分页时缓存高级查询条件
	split : true,
	overflowY : 'scroll', //只显示上下滚动的滚动条
	overflowX : 'hidden',
	selType : 'checkboxmodel',	// 单选，复选框
	requires : ['Bjfu.log.model.log'],
	
	initComponent : function() {
		var me = this;
		var gridStore = Ext.create('Ext.data.Store', {
			model : 'Bjfu.log.model.log',
			pageSize : '25',
			proxy : {
				type : 'ajax',
				actionMethods: {
	                create : 'POST',
	                read   : 'POST', // by default GET
	                update : 'POST',
	                destroy: 'POST'
				},
				extraParams: {  
	                searchJson : '{ismpewStatus : 1}'
	            },  
				url : Global_Path+'syslog/systemlogList',
				reader : {
					type : 'json',
					root : 'result',
					idProperty : 'id',
					totalProperty : 'pageCount'
				}
			},
			listeners : {
				'beforeload': function(store, operation, eOpts) {
					if (me.search_cache!=null) {
						Ext.apply(store.proxy.extraParams, { 
							warningStr : me.search_cache
						});
					}
				}
			},
			/*sorters : ["ProductName", "CatID"],
			filters : [{"property" : "ProductName" ,"value" : 1}],*/
			autoLoad : true
		});
		
		Ext.apply(me, {
			store : gridStore,
			forceFit:true,
			columns : [
				{
					text : '日志编号',
			        dataIndex : 'id',
			        hidden : true
				},
				Ext.create('Ext.grid.RowNumberer',{
			          		header : '序号',
			          		align: 'left',
			          		width : 60
			    }), 
			    {
			        text : '业务类型',
			        dataIndex : 'businessType'
			    },{
			        text : '业务类型',
			        dataIndex : 'businessTypeName',
			        hidden:true
			    },{
			        text : '操作类型',
			        dataIndex : 'operationType'
			    },{
			        text : '操作类型',
			        dataIndex : 'operateTypeName',
			        hidden:true
			    } ,{
			        text : '用户名',
			        dataIndex : 'userName',
			        width : '10%'
			    },{
			        text : '用户源地址',
			        dataIndex : 'userSourceIp',
			        width : '10%'
			    },{
			        text : '操作内容',
			        dataIndex : 'operateContent',
			        width : '45%'
			    },{
			        text : '操作时间',
			        dataIndex : 'operateTime',
			        width : '15%'
			    }
			],
			tbar : [{
				 	fieldLabel: '搜索关键词',
					xtype : 'textfield',
					name : 'searchWord',
					width : 340,
					emptyText : '业务类型/操作类型/用户名/操作内容'
				},{
			       	text : '查询' ,
			       	icon : Global_Path + '/resources/extjs/images/search.png',
			       	scope : this, 
			       	handler : function(btn) {
			       		var gridStore = btn.up('gridpanel').store;
			      		var queryForm = Ext.create('Bjfu.log.view.queryLog');
			  			Ext.create('Ext.window.Window', {
							title : '查询日志信息',
				       		height : 300,
				       		width : 590,
				       		closable : true,
				       		closeAction : 'destroy',
				       		border : false,
				       		modal : true,
				       		resizable : false,
				       		layout : 'fit',
				       		items : [queryForm]
				       	}).show();
			       }
			    }, "->", {
		    	text:'高级搜索',
		    	scope:this,
		    	icon : Global_Path + '/resources/extjs/images/search.png',
		    	handler : function(o) {
		    		var gird = o.ownerCt.ownerCt;
				    var check = gird.getSelectionModel().getSelection();
					var idStr = "";
					if(check.length>0){
						for(var i=0;i<check.length;i++){
							if(check[i]!=undefined){
								idStr+=check[i].get("id")+",";
							}
						}
		    	         Ext.MessageBox.confirm("提示", "确定要导出以上原始通告预警数据吗？", function(btn) {
						    if (btn == 'yes') {
						    	if (!Ext.fly('downForm', "_global")){      
	  								var downForm = document.createElement('form'); 
	 								downForm.id = 'downForm'; 
	  								downForm.name = 'downForm'; 
	  								downForm.className = 'x-hidden';
	 								downForm.action = Global_Path+"/smp/warningAnnounce!exportWarningAnnounce"; 
	 								downForm .method = 'post';  
	 
	 								var data = document.createElement('input');  
	  								data.type = 'hidden';//隐藏域
	  								data.name = 'ids';// form表单参数
	 								data.value = idStr;//form表单值
	 								downForm.appendChild(data); 
	 								document.body.appendChild(downForm ); 
	 							}        
	 							Ext.fly('downForm').dom.submit(); 
	     						if (Ext.fly('downForm')){      
	     							document.body.removeChild(downForm );      
								}
							}
						 });
					}else{
						Ext.Msg.alert("提示","请选择需要导出的原始通告预警数据！");
					}
				}
			}],
			loadMask:true,
			bbar : Ext.create('Ext.toolbar.Paging', {
					width : '100%',
					store : gridStore,
					displayInfo : true,
					displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
					emptyMsg : "没有数据"
			})
		});
		
		me.callParent(arguments);
	}
});
