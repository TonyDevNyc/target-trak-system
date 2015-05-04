Ext.define('TGT.controller.App', {
    extend: 'TGT.controller.BaseController',
    views: [ 
        'TGT.view.refdata.search.ReferenceDataMaintenance',    
        'TGT.view.landing.MattersDashboard',
        'TGT.view.company.search.CompanyAdministration'
    ],
    refs: [
        {
            ref: 'Menu',
            selector: '[xtype=menu.targettrakmenu]'
        },
        {
            ref: 'CenterRegion',
            selector: '[xtype=layout.centerregion]'
        }
    ],
    
    init: function() {
        this.listen({
            controller: {
                '#App': {
                    tokenchange: this.dispatch
                }
            },
            component: {
				'menuitem#administerRefDataItem' : {
					click : this.addHistory
				},
				'menuitem#logoutItem' : {
					click : this.logout
				},
				'menuitem#homePageItem' : {
					click : this.addHistory
				},
				'menuitem#companyAdminItem' : {
					click : this.addHistory
				}
            },
            global: {},
            store: {}  
        });
    },
    
    logout : function() {
    	Ext.MessageBox.show({
	           title: 'Logout',
	           msg: 'Are you sure you want to logout?',
	           width : 300,
	           buttons : Ext.MessageBox.OKCANCEL,    
	           icon : Ext.MessageBox.QUESTION,
	           fn : function(buttonId) {
	               if (buttonId === "ok") {
	            	   	Ext.Ajax.request({
		           		    url : '/target-trak-system/sys/logout.htm',
		           		    method : 'POST',
		           		    scope: this,
		           		    headers : {
		           				'X-CSRF-TOKEN': TGT.security.CsrfToken.getCsrfToken()
		           			},
		           			success : function(response) {
		           				
		           			},
		           			failure : function(response) {
		           				Ext.example.msg('Error',  'Error occurred!');
		           			}
	            	   	});
	               	}
	           }
		});
    	
    },
    
	addHistory: function(item, e, opts) {
	    var me = this,
	    token = item.itemId;
	    Ext.util.History.add(token);
	    me.fireEvent('tokenchange', token);
	},
	
	dispatch: function(token) {
        var me = this, config;
       
        switch(token) {
            
            case 'administerRefDataItem' : config = { xtype: 'refdata.search.maintenance' }; break;
            
            case 'homePageItem' :  config = { xtype: 'landing.mattersdashboard' }; break;
            
            case 'companyAdminItem' : config = { xtype: 'company.search.administration' }; break;
            	 
            default : config = { xtype: 'landing.mattersdashboard' }; break;
            	
        }
        me.updateCenterRegion(config);
    },
    
    updateCenterRegion: function(config) {
    	
        var me = this;
        center = me.getCenterRegion();

        // remove all existing content
        center.removeAll(true);
        // add new content
        center.add(config);    
    }
});