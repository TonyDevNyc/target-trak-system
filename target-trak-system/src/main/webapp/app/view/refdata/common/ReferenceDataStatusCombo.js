Ext.define('TGT.view.refdata.common.ReferenceDataStatusCombo', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.refdata.common.referencedatastatuscombo',
	requires: [ 
        'Ext.form.field.ComboBox'
    ],
    store: 'ReferenceDataStatuses',
    fieldLabel: 'Reference Data Status',
    displayField: 'label',
    valueField: 'value',
    typeAhead: true,
    emptyText: '',
    queryMode: 'local',
    width: 350,
    name: 'status'
});