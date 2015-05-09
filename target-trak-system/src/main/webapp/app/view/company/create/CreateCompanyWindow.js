Ext.define('TGT.view.company.create.CreateCompanyWindow', {
	
	extend : 'Ext.window.Window',
	
	alias : 'widget.company.create.createcompanywindow',
	
	requires : [
       'Ext.window.Window',
       'TGT.view.company.create.CreateCompanyForm'
    ],
    
    itemId : 'createCompanyWindow',
    
    modal : true,
    
    width: 600,
    
    items : [
         {
        	 xtype: 'company.create.createcompanyform'
         }
    ]
});