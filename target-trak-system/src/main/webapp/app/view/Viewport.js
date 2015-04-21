Ext.define('TGT.view.Viewport', {
    extend : 'Ext.container.Viewport',
    requires : [ 
        'Ext.layout.container.Border',
        'TGT.view.menu.TargetTrakMenu',
        'TGT.view.layout.CenterRegion',
        'TGT.view.layout.NorthRegion',
        'TGT.view.layout.WestRegion'
    ],
    layout : {
        type : 'border'
    },
    items : [
        {
        	xtype: 'view.layout.northregion',
        	region: 'north'
        },
        {
        	xtype: 'view.layout.westregion',
        	region: 'west'
        		
        },
        { 
          xtype: 'layout.centerregion',
          region: 'center'
        }
    ]
});