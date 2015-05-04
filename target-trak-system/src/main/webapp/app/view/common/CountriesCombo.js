Ext.define('TGT.view.common.CountriesCombo', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.common.countriescombo',
	requires: [ 
        'Ext.form.field.ComboBox'
    ],
    store: 'Countries',
    fieldLabel: 'Country',
    displayField: 'label',
    valueField: 'value',
    typeAhead: true,
    emptyText: '',
    queryMode: 'local',
    width: 250,
    name: 'country'
});