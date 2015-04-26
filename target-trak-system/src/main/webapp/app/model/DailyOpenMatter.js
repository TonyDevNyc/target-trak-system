Ext.define('TGT.model.DailyOpenMatter', {
    extend : 'Ext.data.Model',
    fields : [
        {name : 'date', type : 'string'},
        {name : 'matterOpenCount', type : Ext.data.Types.INT}
    ]
});