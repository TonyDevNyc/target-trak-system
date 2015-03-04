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
			
//		renderer: function(loader, response, active) {
//			console.log('successful ajax call to get menu items');
//		},
		failure: function() {
			console.log('failed ajax call to get menu items');
		}
	}
});