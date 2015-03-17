Ext.Loader.setConfig({
	enabled: true,
	disableCaching: false
});

Ext.application({
	name: 'TGT',
	
	views: ['Viewport'],
	
	requires: [
	   'Ext.util.History',
       'TGT.controller.TargetTrakMenuController', 
       'TGT.view.menu.TargetTrakMenu',
       'TGT.store.ReferenceDataTypes',
       'TGT.store.ReferenceDatas',
       'TGT.controller.refdata.ReferenceDataMaintenanceController'
    ],
	
    stores: ['ReferenceDataTypes', 'ReferenceDatas'],
    
    controllers: ['App', 'TargetTrakMenuController', 'refdata.ReferenceDataMaintenanceController'],
	
	autoCreateViewport: true,
	
	// create a reference in Ext.application so we can access it from multiple functions
    splashscreen: {},
 
    init: function() {
        // start the mask on the body and get a reference to the mask
         splashscreen = Ext.getBody().mask('Loading Target-Trak System, Please wait...', 'splashscreen');
    },
 
    launch: function() {
    	/** Custom Application Events **/
    	this.addEvents('actionclick');
    	
    	/** Application Load Mask **/
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
 
       /** Application History mechanism **/
       var me = this;
       // init Ext.util.History on app launch; if there is a hash in the url,
       // our controller will load the appropriate content
       Ext.util.History.init(function(){
           var hash = document.location.hash;
           me.getAppController().fireEvent( 'tokenchange', hash.replace( '#', '' ) );
       });
       // add change handler for Ext.util.History; when a change in the token
       // occurs, this will fire our controller's event to load the appropriate content
       Ext.util.History.on( 'change', function( token ){
           me.getAppController().fireEvent( 'tokenchange', token );
       });
    }
});