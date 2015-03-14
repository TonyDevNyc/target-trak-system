Ext.define('TGT.view.refdata.common.ReferenceDataTypeCombo', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.refdata.common.referencedatatypecombo',
	requires: [ 
        'Ext.form.field.ComboBox'
    ],
    store: 'ReferenceDataTypes',
    fieldLabel: 'Reference Data Type',
    displayField: 'label',
    valueField: 'value',
    typeAhead: true,
    emptyText: '',
    queryMode: 'local',
    width: 350,
    name: 'referenceDataType'
});