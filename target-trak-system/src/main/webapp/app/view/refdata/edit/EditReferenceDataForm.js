Ext.define('TGT.view.refdata.edit.EditReferenceDataForm', {
	extend : 'Ext.form.Panel',
	alias : 'widget.refdata.edit.editreferencedataform',
	requires : [ 
		'Ext.form.Panel',
		'Ext.layout.container.Anchor'
    ],
	width : 800,
	defaults : {
		border : false,
		xtype : 'panel',
		flex : 1,
		layout : 'anchor'
	},
	layout : 'vbox',
	items: [
        {
		    xtype:'textfield',
		    fieldLabel: 'Reference Data Type',
		    labelWidth: 150,
		    name: 'type',
		    disabled: true,
		    width: 450
		},
		{
		    xtype:'textfield',
		    fieldLabel: 'Reference Data  Label',
		    labelWidth: 150,
		    name: 'label',
		    width: 450
		},
		{
		    xtype:'textfield',
		    fieldLabel: 'Reference Data Value',
		    labelWidth: 150,
		    name: 'value',
		    width: 450
		}
	],
	buttons: [
        '->', 
        {
        	text: 'Save',
        	name: 'saveReferenceData',
        	action: 'saveReferenceData'
        }, 
        {
        	text: 'Cancel',
        	name: 'cancelEditReferenceData',
        	action: 'cancelEditReferenceData'
        }
    ]
});