Ext.define('TGT.store.ReferenceDataStatuses', {
	extend : 'Ext.data.Store',
	model : 'TGT.model.ReferenceDataStatus',
	proxy : {
		type : 'ajax',
		url : '/target-trak-system/sys/refdata/getReferenceDataStatuses.json',
		reader : {
			type : 'json',
			root : 'data'
		},
		fields: 
	    [ 
	          {name: 'value'}, 
	          {name: 'label'}
	    ],
		listeners : {
			exception : function(proxy, response, operation) {
				Ext.MessageBox.show({
					title : 'Target-Trak System Error',
					msg : 'Error getting Reference Data Statuses',
					icon : Ext.MessageBox.ERROR,
					buttons : Ext.Msg.OK
				});
			}
		}
	}
});