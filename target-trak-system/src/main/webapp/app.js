Ext.Loader.setConfig({
	enabled: true,
	disableCaching: false
});

Ext.application({
	name: 'TGT',
	
	views: ['Viewport'],
	
	requires: [
	   'Ext.util.History',
	   'TGT.security.CsrfToken',
       'TGT.controller.TargetTrakMenuController', 
       'TGT.view.menu.TargetTrakMenu',
       'TGT.controller.BaseController',
       'TGT.controller.refdata.ReferenceDataMaintenanceController',
       'TGT.controller.matters.MattersDashboardController',
       'TGT.controller.company.CompanyAdministrationController'
    ],
	
    stores: [
         'ReferenceDataTypes', 
         'ReferenceDatas', 
         'MatterStatusCharts', 
         'ReferenceDataStatuses',
         'Companies', 'Cities', 'Countries', 'States'
    ],
    
    controllers: [
        'App', 
        'BaseController',
        'TargetTrakMenuController', 
        'refdata.ReferenceDataMaintenanceController',
        'matters.MattersDashboardController',
        'company.CompanyAdministrationController'
    ],
	
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
    	this.addEvents('handleDeleteReferenceData');
    	this.addEvents('companyactionclick');
    	
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
       Ext.util.History.on('change', function(token){
           me.getAppController().fireEvent( 'tokenchange', token);
       });
       
       //var csrfParam;
       var csrfToken = ''; 
       var metaHeaders =  Ext.dom.Query.select("meta[name='_csrf']");
       Ext.Array.each(metaHeaders, function(metaHeader) {
    	   	csrfToken = metaHeader.content;
       });
       TGT.security.CsrfToken.setCsrfToken(csrfToken);
       console.log('Token from singleton: ' + TGT.security.CsrfToken.getCsrfToken());
    }
});

Ext.Ajax.on('requestcomplete', function (conn, response, options) {
	var data = Ext.decode(response.responseText);
	var success = data.success;
	if (success == false) {
		var errorType = data.errorType;
		if ("SESSION_TIMEOUT" == errorType) {
			Ext.Msg.alert('Target-Trak Session Timeout', "Your session has timed out due to inactivity. You will be forwarded to login");
			window.location = data.redirectUrl;
		} 
		console.log("Success is false, value: " + success);
	}
});