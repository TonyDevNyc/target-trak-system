Ext.define('TGT.view.refdata.create.CreateReferenceDataWindow', {
	
	extend : 'Ext.window.Window',
	
	alias : 'widget.refdata.create.createreferencedatawindow',
	
	requires : [
       'Ext.window.Window',
       'TGT.view.refdata.create.CreateReferenceDataForm'
    ],
    
    modal : true,
    
    width: 800,
    
    items : [
         {
        	 xtype: 'refdata.create.createreferencedataform'
         }
    ]
});