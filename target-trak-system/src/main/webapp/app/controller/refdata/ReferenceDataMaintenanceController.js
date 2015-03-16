Ext.define('TGT.controller.refdata.ReferenceDataMaintenanceController', {
	extend : 'TGT.controller.BaseController',
	
	requires: [
       'TGT.store.ReferenceDataTypes',
       'TGT.store.ReferenceDatas'
    ],

	stores : [ 'ReferenceDatas', 'ReferenceDataTypes'],

	models : [ 'ReferenceData', 'ReferenceDataType' ],

	views : [ 'refdata.search.ReferenceDataMaintenance' ],

	refs : [ 
        {
        	ref : 'referenceDataSearchForm',
        	selector : '[xtype=refdata.search.referencedatasearchform]'
        }, {
        	ref : 'ReferenceDataGrid',
        	selector : '[xtype=refdata.search.referencedatagrid]'
        }, 
        {
    		ref : 'searchReferenceDataButton',
    		selector : 'button[name="searchReferenceData"]'
    	}
    ],
	
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
		this.control({
            'button[name="searchReferenceData"]': {
                click: this.searchReferenceData
            }
        });
	}, 
	
	searchReferenceData: function() {
		var me = this;
		var form = me.getSearchReferenceDataButton().up('form').getForm();

		if (form.isValid()) {
			form.submit({
    			submitEmptyText: true,
    			url: '/target-trak-system/sys/refdata/searchReferenceData.json',
    			params : {
    				page: 1,
    				start : 0,
    				limit : 15
    			},
				method: 'POST',
                waitMsg: 'Searching Please Wait...',
                success: function(form, action) {
                	var referenceDataStore = me.getReferenceDatasStore();
                	referenceDataStore.loadData(action.result.data);
                	referenceDataStore.totalCount = action.result.totalSize;
                	Ext.ComponentQuery.query('pagingtoolbar')[0].onLoad();
                },
                failure: function(form, action) {
                	Ext.Msg.alert('Reference Data Search Error', 'Please contact your system administrator');
                }
    		});
		}
	}
});