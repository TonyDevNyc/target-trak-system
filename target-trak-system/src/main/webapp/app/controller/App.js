Ext.define('TGT.controller.App', {
    extend: 'TGT.controller.BaseController',
    views: [ 
        'TGT.view.refdata.search.ReferenceDataMaintenance',    
        'TGT.view.landing.MattersDashboard'
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
            	'menuitem#viewRefDataItem' : {
					click : this.addHistory
				},
				'menuitem#createRefDataItem' : {
					click : this.addHistory
				},
				'menuitem#administerRefDataItem' : {
					click : this.addHistory
				},
				'menuitem#editRefDataItem' : {
					click : this.addHistory
				},
				'menuitem#deleteRefDataItem' : {
					click : this.addHistory
				},
				'menuitem#logoutItem' : {
					click : this.logout
				}
            },
            global: {},
            store: {}  
        });
    },
    
    logout : function() {
    	Ext.msg.alert('Logout', 'Logout');
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
            case 'administerRefDataItem':
                config = {
                    xtype: 'refdata.search.maintenance'
                };
                break;
            case 'createMatterItem':
                config = {
                    xtype: 'panel',
                    title: 'Create Matter',
                    html: 'Create Matters'
                };
                break;
            case 'generateAffidavitItem':
                config = {
                    xtype: 'panel',
                    title: 'Generate Affidavit',
                    html: 'Generate Affidavits' 
                };
                break;
            default: 
            	config = {
                    xtype: 'landing.mattersdashboard'
                };
                break;
        }
        me.updateCenterRegion(config);
    },
    
    updateCenterRegion: function(config) {
        var me = this,
        center = me.getCenterRegion();

        // remove all existing content
        center.removeAll( true );
        // add new content
        center.add( config );
    }
});