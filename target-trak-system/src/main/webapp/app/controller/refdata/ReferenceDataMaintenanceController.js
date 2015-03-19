Ext.define('TGT.controller.refdata.ReferenceDataMaintenanceController', {
	extend : 'TGT.controller.BaseController',
	
	requires : [
       'TGT.store.ReferenceDataTypes',
       'TGT.store.ReferenceDatas',
       'TGT.view.refdata.edit.EditReferenceDataWindow',
       'TGT.view.refdata.edit.EditReferenceDataForm'
    ],

	stores : [ 'ReferenceDatas', 'ReferenceDataTypes'],

	models : [ 'ReferenceData', 'ReferenceDataType' ],

	views : [ 
        'refdata.search.ReferenceDataMaintenance', 
        'refdata.edit.EditReferenceDataWindow'
    ],

	refs : [ 
        {
        	ref : 'referenceDataSearchForm',
        	selector : '[xtype=refdata.search.referencedatasearchform]'
        }, {
        	ref : 'referenceDataGrid',
        	selector : '[xtype=refdata.search.referencedatagrid]'
        }, 
        {
    		ref : 'searchReferenceDataButton',
    		selector : 'button[name="searchReferenceData"]'
    	}, 
        {
    		ref : 'editReferenceDataWindow',
    		selector : '[xtype=refdata.edit.editreferencedatawindow]'
    	}, 
        {
    		ref : 'editReferenceDataForm',
    		selector : '[xtype=refdata.edit.editreferencedataform]'
    	},
    	{
    		ref : 'editReferenceDataButton',
    		selector : 'button[name="updateReferenceData"]'
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
		this.application.on('actionclick', this.handleActionColumn);
		this.application.on('handleDeleteReferenceData', this.deleteReferenceData);
		
		this.control({
            'button[name="searchReferenceData"]': {
                click: this.searchReferenceData
            },
            'button[name="cancelEditReferenceData"]': {
            	click: this.closeEditReferenceDataWindow
            },
            'button[name="updateReferenceData"]' : {
            	click: this.updateReferenceData
            }
        });
	}, 
	
	deleteReferenceData : function(id) {
		Ext.Ajax.request({
		    url : '/target-trak-system/sys/refdata/deleteReferenceData.json',
			params : {
				referenceDataId : id
			},
			success : function(response) {
				Ext.example.msg('Delete Reference Data Success', id + ' has been deleted');
			},
			failure : function() {
				Ext.example.msg('Delete Reference Data Error',  'An error occurred');
			}
		});
		
		
	},
	
	updateReferenceData : function() {
		var me = this;
		var form = me.getEditReferenceDataButton().up('form').getForm();
		if (form.isValid()) {
			form.submit({
    			submitEmptyText: true,
    			url : '/target-trak-system/sys/refdata/updateReferenceData.json',
				method : 'POST',
                waitMsg : 'Updating Reference Data Please Wait...',
                success : function(form, action) {
                	Ext.example.msg('Update Reference Data Success', 'Data was updated successfully');
                	me.getEditReferenceDataWindow().close();
                	me.getReferenceDataGrid().getStore().reload();
                },
                failure : function(form, action) {
                	var errorsArray = action.result.errors;
                	if (errorsArray != null || errorsArray.length > 0) {
                		Ext.Array.each(errorsArray, function(errorObject) {
                			var fieldName = errorObject.fieldName;
                			var errorMsg = errorObject.errorMessage;
                			form.findField(fieldName).markInvalid(errorMsg);
                		});
                	}
                	Ext.example.msg('Update Reference Data Error', action.result.message);
                }
    		});
		}
	},
	
	handleActionColumn : function(action, record) {
		var me = this;
		var id = record.get('id');
		switch (action) {
			
			case 'editReferenceData' :
				var editReferenceDataWindow = Ext.create('TGT.view.refdata.edit.EditReferenceDataWindow',{
					title: 'Edit Reference Data'
				});
				editReferenceDataWindow.down('form').loadRecord(record);
				editReferenceDataWindow.show();
				break;
			
			case 'deleteReferenceData':
				Ext.MessageBox.show({
			           title: 'Delete Reference Data',
			           msg: 'Are you sure you want to delete this reference data?',
			           width : 300,
			           buttons : Ext.MessageBox.OKCANCEL,    
			           icon : Ext.MessageBox.QUESTION,
			           fn : function(buttonId) {
			               if (buttonId === "ok") {
			            	   TGT.app.fireEvent('handleDeleteReferenceData', id);
			               }
			           }
				});
	            break;
		}
	},
	
	
	searchReferenceData : function() {
		var me = this;
		var form = me.getSearchReferenceDataButton().up('form').getForm();

		if (form.isValid()) {
			form.submit({
    			submitEmptyText: true,
    			url : '/target-trak-system/sys/refdata/searchReferenceData.json',
    			params : {
    				page: 1,
    				start : 0,
    				limit : 15
    			},
				method : 'POST',
                waitMsg : 'Searching Please Wait...',
                success : function(form, action) {
                	var referenceDataStore = me.getReferenceDatasStore();
                	referenceDataStore.loadData(action.result.data);
                	referenceDataStore.totalCount = action.result.totalSize;
                	Ext.ComponentQuery.query('pagingtoolbar')[0].onLoad();
                },
                failure : function(form, action) {
                	Ext.Msg.alert('Reference Data Search Error', 'Please contact your system administrator');
                }
    		});
		}
	},
	closeEditReferenceDataWindow : function() {
		this.getEditReferenceDataWindow().close();
	}
});