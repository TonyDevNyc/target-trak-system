Ext.define('TGT.view.refdata.search.ReferenceDataMaintenance', {
	extend : 'Ext.container.Container',
	alias : 'widget.refdata.search.maintenance',
	requires : [
	    'TGT.view.refdata.search.ReferenceDataSearchForm',
	    'TGT.view.refdata.search.ReferenceDataGrid'
	],
	items: [
		{
			xtype: 'container',
			layout: 'vbox',    
	        items: [
				{
				    xtype: 'refdata.search.referencedatasearchform',
				    flex: 1
				},
				{
				    xtype: 'refdata.search.referencedatagrid',
				    flex: 1
				}
            ]
		}    
    ]
});