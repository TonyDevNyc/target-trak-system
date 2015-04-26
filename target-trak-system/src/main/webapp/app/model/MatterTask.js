Ext.define('TGT.model.MatterTask', {
    extend : 'Ext.data.Model',
    fields : [
        {name : 'matterId', type : 'string'},
        {name : 'companyName', type : 'string'},
        {name : 'primaryContact', type: 'string'},
        {name : 'taskType', type : 'string'},
        {name : 'createDate', type : 'string'},
        {name : 'completionDate', type : 'string'},
        {name : 'assignedTo', type : 'string'},
        {name : 'completedDate', type : 'string'}
    ]
});