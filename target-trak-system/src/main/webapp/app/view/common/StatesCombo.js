Ext.define('TGT.view.common.StatesCombo', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.common.statescombo',
	requires: [ 
        'Ext.form.field.ComboBox'
    ],
    store: 'States',
    fieldLabel: 'State',
    displayField: 'label',
    valueField: 'value',
    typeAhead: true,
    emptyText: '',
    queryMode: 'local',
    width: 250,
    name: 'state'
});