Ext.define('TGT.view.company.search.CompanyGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.company.search.companygrid',
	store : 'Companies',
	itemId : 'companiesGrid',
	loadMask : true,
	autoScroll : true,
	width : 1098,
	viewConfig : {
		enableTextSelection : true
	},
	layout : 'fit',
	listeners: {
		
    },
	columns : [ 
        {
			xtype : 'actioncolumn',
			width : 75,
			text : 'Actions',
			align : 'center',
			items: [
		        {
		        	icon: '/target-trak-system/resources/images/edit.gif',  
                    tooltip: 'Edit',
                    itemId: 'editCompany',
                    align: 'center',
                    getClass: 'x-action-col-icon',
                    handler: function(grid, rowIndex, colIndex) {
                    	var record = grid.getStore().getAt(rowIndex);
                    	//TGT.app.fireEvent('actionclick', 'editReferenceData', record);
                    }
	            }
            ]
		}, 
		{
			xtype : 'gridcolumn',
			dataIndex : 'name',
			text : 'Name',
			width : 185
		},
		{
			xtype : 'gridcolumn',
			dataIndex : 'addressLine1',
			text : 'Address Line 1',
			width : 125
		},
		{
			xtype : 'gridcolumn',
			dataIndex : 'addressLine2',
			text : 'Address Line 2',
			width : 100
		},
		{
			xtype : 'gridcolumn',
			dataIndex : 'city',
			text : 'City',
			width : 100
		},
		{
			xtype : 'gridcolumn',
			dataIndex : 'state',
			text : 'State',
			width : 70
		}, 
		{
			xtype : 'gridcolumn',
			dataIndex : 'zipcode',
			text : 'Zip Code',
			width : 90
		}, 
		{
			xtype : 'gridcolumn',
			dataIndex : 'country',
			text : 'Country',
			width : 85
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
		store : 'Companies',
		displayInfo : true,
		displayMsg : 'Displaying {0} - {1} of {2}',
		width : 1275,
		items : [
	        '-', 
	        {
                text : 'Create Company',
                itemId : 'showCreateCompanyBtn', 
                enableToggle : true,
                icon : '/target-trak-system/resources/images/add-refdata.png'
	        }
        ]
	} ]
});