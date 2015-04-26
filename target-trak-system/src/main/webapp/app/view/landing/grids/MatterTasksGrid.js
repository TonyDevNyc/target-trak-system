Ext.define('TGT.view.landing.grids.MatterTasksGrid', {
	
	extend : 'Ext.grid.Panel',
	
	alias : 'widget.landing.grids.mattertasksgrid',
	
	store : 'MatterTasks',
		
	loadMask : true,
	
	autoScroll : true,
	
	width : 1098,
	
	height : 350,
	
	maxHeight : 250,
	
	features: [
        {
        	ftype : 'grouping'
        	//,hideGroupedHeader : true
        }
    ],
	
    columns : [ 
        {
        	xtype : 'gridcolumn',
			dataIndex : 'taskType',
			text : 'Task Type',
			width : 150
        },
        {
        	xtype : 'gridcolumn',
			dataIndex : 'companyName',
			text : 'Company',
			width : 150
        },
        {
        	xtype : 'gridcolumn',
			dataIndex : 'primaryContact',
			text : 'Contact',
			width : 150
        },
        {
        	xtype : 'gridcolumn',
			dataIndex : 'createDate',
			text : 'Task Created On',
			width : 125
        },
        {
        	xtype : 'gridcolumn',
			dataIndex : 'completionDate',
			text : 'Completion Date',
			width : 150
        },
        {
        	xtype : 'gridcolumn',
			dataIndex : 'assignedTo',
			text : 'Task Assigned To',
			width : 150
        },
        {
        	xtype : 'gridcolumn',
			dataIndex : 'completedDate',
			text : 'Task Completed On',
			width : 200
        }
    ]
});