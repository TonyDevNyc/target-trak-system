Ext.define('TGT.view.company.edit.EditCompanyForm', {
	extend : 'Ext.form.Panel',
	alias : 'widget.company.edit.editcompanyform',
	requires : [ 
		'Ext.form.Panel',
		'Ext.layout.container.Anchor',
		'TGT.view.common.CitiesCombo',
		'TGT.view.common.StatesCombo',
		'TGT.view.common.CountriesCombo'
    ],
	width : 600,
	bodyPadding: 10,
    waitMsgTarget: true,
	defaults : {
		border : false,
		xtype : 'panel',
		flex : 1,
		layout : 'anchor'
	},
	fieldDefaults: {
        labelAlign: 'right',
        labelWidth: 85,
        msgTarget: 'side'
    },
    items: [
        {
        	xtype : 'hidden',
        	name : 'id'
        },
        {
        	xtype : 'textfield',
        	fieldLabel: 'Company Name',
        	labelWidth: 150,
        	emptyText: 'Company Name',
        	name: 'name',
        	width: 350
        }, 
        {
        	xtype : 'textfield',
        	fieldLabel: 'Address Line 1',
        	labelWidth: 150,
        	emptyText: 'Address Line 1',
        	name: 'addressLine1',
        	width: 350
        }, 
        {
        	xtype : 'textfield',
        	fieldLabel: 'Address Line 2',
        	labelWidth: 150,
        	emptyText: 'Address Line 2',
        	name: 'addressLine2',
        	width: 350
        }, 
        {
        	xtype : 'common.citiescombo',
        	labelWidth: 150,
        	fieldLabel: 'City',
        	emptyText: 'Please select a city..',
        	name: 'city',
        	width: 350
        }, 
        {
        	xtype : 'common.statescombo',
        	labelWidth: 150,
        	fieldLabel: 'State',
        	emptyText: 'Please select a state..',
        	name: 'state',
        	width: 350
        }, 
        {
        	xtype : 'textfield',
        	fieldLabel: 'Zip Code',
        	labelWidth: 150,
        	emptyText: 'Zip Code',
        	name: 'zipcode',
        	width: 350
        },
        {
            xtype: 'common.countriescombo',
            labelWidth: 150,
            fieldLabel: 'Country',
            emptyText: 'Please select a country..',
        	name: 'country',
        	width: 350
        }, 
        {
        	xtype : 'textfield',
        	fieldLabel: 'Created By',
        	labelWidth: 150,
        	disabled : true,
        	width: 350,
        	name : 'createdBy'
        }, 
        {
        	xtype : 'textfield',
        	fieldLabel: 'Created Date',
        	labelWidth: 150,
        	disabled : true,
        	width: 350,
        	name : 'createdDateTime'
        }
    ],
	buttons: [
          '->', 
          {
          	text : 'Save',
          	name : 'saveCompanyBtn',
          	itemId : 'saveCompanyBtn'
          }, 
          {
          	text : 'Cancel',
          	name : 'cancelEditCompanyBtn',
          	itemId : 'cancelEditCompanyBtn'
          	
          }
    ]
});