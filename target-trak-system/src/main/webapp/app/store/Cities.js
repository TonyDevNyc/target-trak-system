Ext.define('TGT.store.Cities', {
	extend : 'Ext.data.Store',
	model : 'TGT.model.LabelValuePair',
	proxy : {
		type : 'ajax',
		url : '/target-trak-system/sys/common/getReferenceDataValuesByType.json',
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
					msg : 'Error getting Cities',
					icon : Ext.MessageBox.ERROR,
					buttons : Ext.Msg.OK
				});
			}
		}
	}
});