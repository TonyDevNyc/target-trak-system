Ext.define('TGT.controller.refdata.ReferenceDataMaintenanceController', {
	extend : 'TGT.controller.BaseController',
	
	requires: [
       'TGT.store.ReferenceDataTypes',
       'TGT.store.ReferenceDatas'
    ],

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
		
		var typesStore = this.getReferenceDataTypesStore();
		typesStore.load();
	},

	init : function() {

	}, 
	
	
	
	
});