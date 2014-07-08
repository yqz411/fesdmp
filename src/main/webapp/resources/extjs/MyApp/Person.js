Ext.define("MyApp.Person", {
			    config: {
			        Name: '',
			        Age: 0,
			    },
			    alias : 'Person',
			    Say: function (msg) {
			        Ext.Msg.alert(this.Name + " Says:", msg);
			    },
			    constructor: function (config) {
			        this.initConfig(config);
			    }
			});