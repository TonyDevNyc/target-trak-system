Ext.define('TGT.view.Viewport', {
    extend : 'Ext.container.Viewport',
    requires : [ 
        'Ext.layout.container.Border',
        'TGT.view.menu.TargetTrakMenu'
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
          xtype: 'panel',
          title: 'Hi',
          region: 'center'
        }
    ]
});