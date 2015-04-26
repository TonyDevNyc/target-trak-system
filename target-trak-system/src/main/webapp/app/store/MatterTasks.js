Ext.define('TGT.store.MatterTasks', {
	extend: 'Ext.data.Store',
	model: 'TGT.model.MatterTask',
	groupField : 'matterId',
	proxy: {
        type: 'ajax',
        url: '/target-trak-system/sys/matters/getMatterTasks.json',
        reader: {
            type: 'json',
            root: 'data'
        },
        listeners: {
            exception: function(proxy, response, operation){
                Ext.MessageBox.show({
                    title: 'Target Trak Error',
                    msg: 'Error getting chart data',
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
            }
        }
    }
});