Ext.define('TGT.model.ReferenceData', {
	extend : 'Ext.data.Model',
	fields : [ {
			name : 'id',
			type : 'number'
		}, {
			name : 'type',
			type : 'string'
		}, {
			name : 'label',
			type : 'string'
		},{
			name : 'value',
			type : 'string'
		}, {
			name : 'status',
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