Ext.define('TGT.controller.refdata.ReferenceDataMaintenanceController', {
	extend : 'TGT.controller.BaseController',

	stores : [ 'ReferenceDatas', 'ReferenceDataTypes'],

	models : [ 'ReferenceData', 'ReferenceDataType' ],

	views : [ 'refdata.search.ReferenceDataMaintenance' ],

	refs : [ {
		ref : 'referenceDataSearchForm',
		selector : '[xtype=refdata.search.referencedatasearchform]'
	}, {
		ref : 'ReferenceDataGrid',
		selector : '[xtype=refdata.search.referencedatagrid]'
	} ],
	
	onLaunch : function() {
		var referenceDatasStore = this.getReferenceDatasStore();
		referenceDatasStore.load({
			params : {
				start : 0,
				limit : 15
			}
		});
		
		this.getReferenceDataTypesStore().referenceDatasStore.load();
	},

	init : function() {

	}, 
	
	
	
	
});