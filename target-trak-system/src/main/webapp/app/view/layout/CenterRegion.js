Ext.define('TGT.view.layout.CenterRegion', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.layout.centerregion',
	layout: 'card',
    items: [
        {
        	xtype: 'panel',
        	html: 'Welcome to the Target-Trak',
        	activeItem: 0
        }
    ]
});