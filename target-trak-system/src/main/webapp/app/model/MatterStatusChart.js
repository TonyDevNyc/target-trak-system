Ext.define('TGT.model.MatterStatusChart', {
    extend : 'Ext.data.Model',
    fields : [
        {name : 'status', type : 'string'},
        {name : 'count', type : Ext.data.Types.INT},
        {name : 'percentage', type : Ext.data.Types.FLOAT},
        {name : 'label', type : 'string'}
    ]
});