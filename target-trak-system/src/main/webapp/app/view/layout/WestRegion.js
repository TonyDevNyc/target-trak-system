Ext.define('TGT.view.layout.WestRegion', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.view.layout.westregion',
    requires: [
        'TGT.view.menu.TargetTrakMenu'
    ],
    region: 'west',
    split: true,
    bodyPadding: 5,
    minWidth: 175,
    width: 175,
    initComponent: function(){
        var me = this;
        Ext.applyIf(me,{
            items: [
                {
                    xtype: 'view.menu.targettrakmenu'
                }
            ]
        });
        me.callParent( arguments );
    } 
});