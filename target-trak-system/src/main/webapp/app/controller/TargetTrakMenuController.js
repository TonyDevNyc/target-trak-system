Ext.define('TGT.controller.TargetTrakMenuController', {
	extend : 'TGT.controller.BaseController',

	requires : [ 'Ext.util.History' ],

	views : [ 'menu.TargetTrakMenu'],
	
	refs : [ 
        {
			ref: 'TargetTrakMenu',
			selector: '[xtype=view.menu.targettrakmenu]'
		}
	],
	
	onLaunch : function() {
		
		
	},

	init : function() {

	}, 
	
	
	
	
});