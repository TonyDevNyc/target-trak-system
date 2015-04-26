Ext.define('TGT.view.landing.MattersDashboard', {
    extend : 'Ext.panel.Panel',
    alias : 'widget.landing.mattersdashboard',
    requires : [
        'TGT.view.landing.charts.MatterStatusChart',
        'TGT.view.refdata.search.ReferenceDataGrid',
        'TGT.view.landing.grids.MatterTasksGrid'
    ],
    items: [
        {
        	 xtype : 'container',
        	 layout : {
        		    type : 'hbox',
        		    align : 'middle',
        		    pack : 'center'
        	 },
        	 items : [
    	         {
    	        	 xtype : 'panel',
    	        	 width : 549,
    	        	 title : 'Matters Status Breakdown',
    	        	 layout: 'fit',
    	        	 items : [
	        	          {
	        	        	  xtype : 'landing.charts.matterstatus'
	        	          }
    	        	 ]
    	         },
    	         {
    	        	 xtype : 'panel',
    	        	 width : 549,
    	        	 title : 'Weekly Open Matters',
    	        	 layout: 'fit',
    	        	 items : [
	        	          {
	        	        	  xtype : 'landing.charts.weeklyopenmatters'
	        	          }
    	        	 ]
    	         }
	         ]
        },
        {
        	xtype : 'container',
       	 	layout : {
	       		 type : 'hbox',
	       		 align : 'middle',
	       		 pack : 'center'
       	 	},
 			items : [
 			   {
	   	        	 xtype : 'panel',
	   	        	 width : 1098,
	   	        	 title : 'Matter Tasks Summary',
	   	        	 items : [
	        	          {
	        	        	  xtype : 'landing.grids.mattertasksgrid'
	        	          }
	   	        	 ]
	   	        }
	        ]
        }
    ]
});