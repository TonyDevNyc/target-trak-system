Ext.define('TGT.view.Viewport', {
    extend : 'Ext.container.Viewport',
    requires : [ 
        'Ext.layout.container.Border',
        'TGT.view.menu.TargetTrakMenu',
        'TGT.view.layout.CenterRegion'
    ],
    layout : {
        type : 'border'
    },
    items : [
        {
        	xtype: 'view.menu.targettrakmenu',
        	region: 'north'
        },
        { 
          xtype: 'layout.centerregion',
          region: 'center'
        }
    ]
});