Ext.define('TGT.view.refdata.search.ReferenceDataGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.refdata.search.referencedatagrid',
	store : 'ReferenceDatas',
	itemId : 'referenceDataGrid',
	loadMask : true,
	autoScroll : true,
	width : 1098,
	viewConfig : {
		enableTextSelection : true
	},
	layout : 'fit',
	listeners: {
		render : function(grid) {
        	grid.getStore().load();
        }
    },
	columns : [ 
        {
			xtype : 'actioncolumn',
			width : 80,
			text : 'Actions',
			align : 'center',
			items: [
		        {
		        	icon: '/target-trak-system/resources/images/edit.gif',  
                    tooltip: 'Edit Reference Data',
                    itemId: 'editReferenceData',
                    align: 'center',
                    getClass: 'x-action-col-icon',
                    handler: function(grid, rowIndex, colIndex) {
                    	var record = grid.getStore().getAt(rowIndex);
                    	TGT.app.fireEvent('actionclick', 'editReferenceData', record);
                    }
	            },
	            {
	                icon: '/target-trak-system/resources/images/delete-icon.gif',
	                tooltip: 'Delete Reference Data',
	                itemId: 'deleteReferenceData',
	                handler: function(grid, rowIndex, colIndex) {
	                    var record = grid.getStore().getAt(rowIndex);
	                    TGT.app.fireEvent('actionclick', 'deleteReferenceData', record);
	                }
	            }
            ]
		}, 
		{
			xtype : 'gridcolumn',
			dataIndex : 'type',
			text : 'Type',
			width : 110
		},
		{
			xtype : 'gridcolumn',
			dataIndex : 'label',
			text : 'Label',
			width : 170
		},
		{
			xtype : 'gridcolumn',
			dataIndex : 'value',
			text : 'Value',
			width : 115
		},
		{
			xtype : 'gridcolumn',
			dataIndex : 'status',
			text : 'Status',
			width : 100
		},
		{
			xtype : 'gridcolumn',
			dataIndex : 'createdBy',
			text : 'Created By',
			width : 100
		}, 
		{
			xtype : 'gridcolumn',
			dataIndex : 'createdDateTime',
			text : 'Created Date',
			width : 160
		}, 
		{
			xtype : 'gridcolumn',
			dataIndex : 'lastUpdatedBy',
			text : 'Last Updated By',
			width : 100
		}, 
		{
			xtype : 'gridcolumn',
			dataIndex : 'lastUpdatedDateTime',
			text : 'Last Updated Date',
			width : 160
		}  
	],
	bbar : [ {
		xtype : 'pagingtoolbar',
		store : 'ReferenceDatas',
		displayInfo : true,
		displayMsg : 'Displaying {0} - {1} of {2}',
		width : 1275,
		items : [
	        '-', 
	        {
                text : 'Create Item',
                itemId : 'createReferenceDataBtn', 
                enableToggle : true,
                icon : '/target-trak-system/resources/images/add-refdata.png'
	        }
        ]
	} ]
});