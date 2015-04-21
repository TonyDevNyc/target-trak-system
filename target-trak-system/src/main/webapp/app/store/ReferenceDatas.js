Ext.define('TGT.store.ReferenceDatas', {
	extend : 'Ext.data.Store',
	model : 'TGT.model.ReferenceData',
	pageSize: 10,
	proxy : {
		sortParam: 'sortField',
		type : 'ajax',
		url : '/target-trak-system/sys/refdata/getReferenceData.json',
		reader : {
			type : 'json',
			root : 'data',
			idProperty : 'id',
			successProperty : 'success',
			totalProperty: 'totalSize'
		},
		listeners : {
			exception : function(proxy, response, operation) {
				Ext.MessageBox.show({
					title : 'Target-Trak System Error',
					msg : 'Error getting reference data',
					icon : Ext.MessageBox.ERROR,
					buttons : Ext.Msg.OK
				});
			}
		}
	}
});