Ext.define('TGT.view.company.search.CompanySearchForm', {
	extend : 'Ext.form.Panel',
	alias : 'widget.company.search.companysearchform',
	requires : [ 
		'Ext.form.Panel',
		'Ext.layout.container.Anchor',
		'TGT.view.common.StatesCombo',
		'TGT.view.common.CountriesCombo',
		'TGT.view.common.CitiesCombo'
    ],
	bodyStyle : 'padding:10px 10px 0',
	width : 1100,
	height: 195,
	fieldDefaults : {
		msgTarget : 'side'
	},
	defaults : {
		border : false,
		xtype : 'panel',
		flex : 1,
		layout : 'anchor'
	},
	layout : 'hbox',
	listeners: {
       
    },
    items: [
        {
        	layout : 'column',
        	border : false,
        	items:[
	            {
	            	columnWidth: .5,
	            	border: false,
	            	layout: 'anchor',
	            	defaultType: 'textfield',
		            items: [
	                    {
			                fieldLabel: 'Company Name',
			                name: 'name',
			                anchor: '55%'
			            }, {
			            	xtype : 'common.statescombo',
			                fieldLabel: 'State',
			                name: 'state',
			                anchor: '55%'
			            }
		            ]
	            },{
		            columnWidth: .5,
		            border: false,
		            layout: 'anchor',
		            defaultType: 'textfield',
		            items: [
	                    {
	                    	xtype : 'common.citiescombo',
			                fieldLabel: 'City',
			                name: 'city',
			                anchor: '55%'
			            },{
			            	xtype : 'common.countriescombo',
			                fieldLabel: 'Country',
			                name: 'country',
			                anchor: '55%'
			            }
		            ]
	            }
            ]
        }
    ],
	buttons: [
        '->', 
        {
        	text: 'Search',
        	name: 'searchCompanyBtn',
        	itemId : 'searchCompanyBtn'
        }, 
        {
        	text: 'Clear',
        	action: 'reset',
            handler: function() {
            	var form = this.up('form').getForm();
            	form.reset();
            }
        }
    ]
});