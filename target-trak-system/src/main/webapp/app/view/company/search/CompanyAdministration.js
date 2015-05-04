Ext.define('TGT.view.company.search.CompanyAdministration', {
	extend : 'Ext.container.Container',
	alias : 'widget.company.search.administration',
	requires : [
	    'TGT.view.company.search.CompanySearchForm',
	    'TGT.view.company.search.CompanyGrid'
	],
	items: [
		{
			xtype: 'container',
			layout: 'vbox',    
	        items: [
				{
				    xtype: 'company.search.companysearchform',
				    flex: 1
				},
				{
				    xtype: 'company.search.companygrid',
				    flex: 1
				}
            ]
		}    
    ]
});