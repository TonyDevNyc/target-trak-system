Ext.define('TGT.view.refdata.search.ReferenceDataSearchForm', {
	extend : 'Ext.form.Panel',
	alias : 'widget.refdata.search.referencedatasearchform',
	requires : [ 
		'Ext.form.Panel',
		'Ext.layout.container.Anchor',
		'TGT.store.ReferenceDataTypes',
		'TGT.view.refdata.common.ReferenceDataTypeCombo'
    ],
	bodyStyle : 'padding:10px 10px 0',
	width : 1275,
	height: 225,
	fieldDefaults : {
		msgTarget : 'side'
	},
	defaults : {
		border : false,
		xtype : 'panel',
		flex : 1,
		layout : 'anchor'
	},
	layout : 'hbox',
	items: [
        {
        	items: [
    	        {
	                xtype: 'refdata.common.referencedatatypecombo'
	            }
            ]
		}
	],
	buttons: [
        '->', 
        {
        	text: 'Search',
        	name: 'searchReferenceData',
        	action: 'searchReferenceDataAction'
        }, 
        {
        	text: 'Clear',
        	action: 'reset',
            handler: function() {
            	var form = this.up('form').getForm();
            	form.reset();
            }
        }
    ]
});