Ext.define('TGT.view.menu.TargetTrakMenu', {
	extend : 'Ext.menu.Menu',
	alias : 'widget.view.menu.targettrakmenu',
	floating : false,
	//height : 350,
	initComponent : function() {
		this.callParent(arguments);
	}, 
	loader: {
		url: '/target-trak-system/sys/buildUserMenu.json',
		autoLoad: true,
		loadMask: true,
		renderer: 'component',
		failure: function() {
			console.log('Failed ajax call to get menu items');
		}
	}
});