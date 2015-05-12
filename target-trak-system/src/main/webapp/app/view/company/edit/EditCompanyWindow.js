Ext.define('TGT.view.company.edit.EditCompanyWindow', {
	
	extend : 'Ext.window.Window',
	
	alias : 'widget.company.edit.editcompanywindow',
	
	requires : [
       'Ext.window.Window',
       'TGT.view.company.edit.EditCompanyForm'
    ],
    
    itemId : 'editCompanyWindow',
    
    modal : true,
    
    width: 600,
    
    items : [
         {
        	 xtype: 'company.edit.editcompanyform'
         }
    ]
});