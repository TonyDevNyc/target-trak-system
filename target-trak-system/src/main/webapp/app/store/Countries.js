Ext.define('TGT.store.Countries', {
	extend : 'Ext.data.Store',
	model : 'TGT.model.LabelValuePair',
	proxy : {
		type : 'ajax',
		url : '/target-trak-system/sys/common/getLabelValuesByType.json',
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
					msg : 'Error getting Countries',
					icon : Ext.MessageBox.ERROR,
					buttons : Ext.Msg.OK
				});
			}
		}
	}
});