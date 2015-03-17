Ext.define('TGT.view.refdata.edit.EditReferenceDataWindow', {
	
	extend : 'Ext.window.Window',
	
	alias : 'widget.refdata.edit.editreferencedatawindow',
	
	requires : [
       'Ext.window.Window'
    ],
    
    modal : true,
    
    width: 800,
    
    items : [
         {
        	 xtype: 'refdata.edit.editreferencedataform'
         }
    ]
});