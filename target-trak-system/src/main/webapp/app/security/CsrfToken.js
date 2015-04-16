Ext.define('TGT.security.CsrfToken', {
	
	singleton : true,
	
	config : {
		csrfToken : ''   
    },
    
    constructor : function(config) {
    	this.initConfig(config);
    }
});