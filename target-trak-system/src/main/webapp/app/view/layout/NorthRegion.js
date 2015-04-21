Ext.define('TGT.view.layout.NorthRegion', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.view.layout.northregion',
    region: 'north',
    bodyPadding: 5,
    html: '<img src="/target-trak-system/resources/images/target-trak.jpeg" height="35" width="35" /><h1>Target-Trak</h1>',
    cls: 'header',                  
    initComponent: function(){
        var me = this;
        Ext.applyIf(me,{
            
        });
        me.callParent( arguments );
    } 
});