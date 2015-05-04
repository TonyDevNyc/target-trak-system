Ext.define('TGT.controller.company.CompanyAdministrationController', {
	
	extend : 'TGT.controller.BaseController',
	
	requires : [
       'TGT.store.Cities',
       'TGT.store.Companies',
       'TGT.store.Countries',
       'TGT.store.States',
       'TGT.view.company.search.CompanyGrid',
       'TGT.view.company.search.CompanySearchForm'
    ],

	stores : [ 'Companies', 'Cities', 'Countries', 'States'],

	models : [ 'Company', 'LabelValuePair'],

	views : [ 
        'company.search.CompanyAdministration'
    ],

	refs : [ 
        {
        	ref : 'companySearchForm',
        	selector : '[xtype=company.search.companysearchform]'
        }, {
        	ref : 'companyGrid',
        	selector : '[xtype=company.search.companygrid]'
        }
    ],
	
	onLaunch : function() {
		
	},
	
	init : function() {
		this.getCitiesStore().load({
			params : {
				referenceDataType : 'cities'
			}
		});
		
		this.getStatesStore().load({
			params : {
				referenceDataType : 'states'
			}
		});
		
		this.getCountriesStore().load({
			params : {
				referenceDataType : 'countries'
			}
		});
		
		this.getCompaniesStore().load();
	}

});