Ext.define('TGT.view.menu.TargetTrakMenu', {
	extend : 'Ext.toolbar.Toolbar',
	alias : 'widget.view.menu.targettrakmenu',
	initComponent : function() {
		this.callParent(arguments);
	}, 
	loader: {
		url: '/target-trak-system/sys/buildUserMenu.json',
		autoLoad: true,
		loadMask: true,
		renderer: 'component',
		failure: function() {
			console.log('failed ajax call to get menu items');
		}
	}
});