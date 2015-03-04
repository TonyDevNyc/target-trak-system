Ext.Loader.setConfig({
	enabled: true,
	disableCaching: false
});

Ext.application({
	name: 'TGT',
	
	views: ['Viewport'],
	
	requires: ['TGT.controller.TargetTrakMenuController', 'TGT.view.menu.TargetTrakMenu'],
	
    controllers: ['TargetTrakMenuController'],
	
	autoCreateViewport: true,
	
	// create a reference in Ext.application so we can access it from multiple functions
    splashscreen: {},
 
    init: function() {
        // start the mask on the body and get a reference to the mask
         splashscreen = Ext.getBody().mask('Loading Target-Trak System, Please wait...', 'splashscreen');
    },
 
    launch: function() {
 
        Ext.tip.QuickTipManager.init();
        var task = new Ext.util.DelayedTask(function() {
 
            // fade out the body mask
            splashscreen.fadeOut({
                duration: 500,
                remove: true
            });
 
            // fade out the message
            splashscreen.next().fadeOut({
                duration: 500,
                remove: true
            });
 
       });
 
       task.delay(2000);
 
    }
});