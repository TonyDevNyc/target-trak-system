Ext.define('TGT.store.ReferenceDataTypes', {
	extend : 'Ext.data.Store',
	model : 'TGT.model.ReferenceDataType',
	proxy : {
		type : 'ajax',
		url : '/target-trak-system/sys/refdata/getReferenceDataTypes.json',
		reader : {
			type : 'json',
			root : 'referenceDataTypes'
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
					msg : 'Error getting Reference Data Types',
					icon : Ext.MessageBox.ERROR,
					buttons : Ext.Msg.OK
				});
			}
		}
	}
});