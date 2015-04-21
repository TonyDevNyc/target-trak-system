Ext.define('TGT.store.Companies', {
	extend : 'Ext.data.Store',
	model : 'TGT.model.Company',
	pageSize: 15,
	proxy : {
		type : 'ajax',
		url : '/target-trak-system/sys/company/getCompanies.json',
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
					msg : 'Error getting company data',
					icon : Ext.MessageBox.ERROR,
					buttons : Ext.Msg.OK
				});
			}
		}
	}
});