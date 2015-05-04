Ext.define('TGT.view.common.CitiesCombo', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.common.citiescombo',
	requires: [ 
        'Ext.form.field.ComboBox'
    ],
    store: 'Cities',
    fieldLabel: 'City',
    displayField: 'label',
    valueField: 'value',
    typeAhead: true,
    emptyText: '',
    queryMode: 'local',
    width: 250,
    name: 'city'    
});