Ext.define('TGT.model.Company', {
	extend : 'Ext.data.Model',
	fields : [ {
			name : 'id',
			type : 'number'
		}, {
			name : 'name',
			type : 'string'
		}, {
			name : 'addressLine1',
			type : 'string'
		},{
			name : 'addressLine2',
			type : 'string'
		},{
			name : 'city',
			type : 'string'
		},{
			name : 'state',
			type : 'string'
		},{
			name : 'zipcode',
			type : 'string'
		},{
			name : 'country',
			type : 'string'
		}, {
			name : 'createdBy',
			type : 'string'
		},{
			name : 'createdDateTime',
			type : 'string'
		},{
			name : 'lastUpdatedBy',
			type : 'string'
		} ,{
			name : 'lastUpdatedDateTime',
			type : 'string'
		}  
	]
});