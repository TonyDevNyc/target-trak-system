Ext.define('TGT.view.refdata.search.ReferenceDataGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.refdata.search.referencedatagrid',
	store : 'ReferenceDatas',
	itemId : 'referenceDataGrid',
	loadMask : true,
	autoScroll : true,
	width : 1275,
	viewConfig : {
		enableTextSelection : true
	},
	layout : 'fit',
	columns : [ 
        {
			xtype : 'actioncolumn',
			width : 100,
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
			width : 160
		},
		{
			xtype : 'gridcolumn',
			dataIndex : 'label',
			text : 'Label',
			width : 180
		}, 
		{
			xtype : 'gridcolumn',
			dataIndex : 'value',
			text : 'Value',
			width : 180
		}, 
		{
			xtype : 'gridcolumn',
			dataIndex : 'createdBy',
			text : 'Created By',
			width : 160
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
			width : 160
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