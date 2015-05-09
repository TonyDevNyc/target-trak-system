Ext.define('TGT.controller.company.CompanyAdministrationController', {
	
	extend : 'TGT.controller.BaseController',
	
	requires : [
       'TGT.store.Cities',
       'TGT.store.Companies',
       'TGT.store.Countries',
       'TGT.store.States',
       'TGT.view.company.search.CompanyGrid',
       'TGT.view.company.search.CompanySearchForm',
       'TGT.view.company.create.CreateCompanyWindow',
       'TGT.view.company.create.CreateCompanyForm'
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
        }, 
        {
        	ref : 'companyGrid',
        	selector : '[xtype=company.search.companygrid]'
        }, 
        {
        	ref : 'createCompanyWindow',
        	select : '[xtype=company.create.createcompanywindow]'
        }
    ],
	
	onLaunch : function() {
		
	},
	
	init : function() {
		this.getCitiesStore().load({ params : { referenceDataType : 'cities' }});
		this.getStatesStore().load({ params : { referenceDataType : 'states' } });
		this.getCountriesStore().load({ params : { referenceDataType : 'countries' } });
		
		this.getCompaniesStore().load();
		
		this.control({
            'button#showCreateCompanyBtn' : {
            	click : this.showCreateCompanyWindow
            },
            'button#cancelCreateCompanyBtn' : {
            	click : this.cancelCreateCompany
            },
            'button#createCompanyBtn' : {
            	click : this.createCompany
            }
		});
	},
	
	createCompany : function() {
		var createButton = Ext.ComponentQuery.query('button#createCompanyBtn')[0];
		var form = createButton.up('form').getForm();
		var me = this;
		var createWindow = Ext.ComponentQuery.query('window#createCompanyWindow')[0];
		
		if (form.isValid()) {
			form.submit({
    			submitEmptyText: false,
    			url : '/target-trak-system/sys/company/createCompany.json',
				method : 'POST',
				headers : {
					'X-CSRF-TOKEN' : TGT.security.CsrfToken.getCsrfToken()
				},
                waitMsg : 'Creating Company Please Wait...',
                success : function(form, action) {
                	Ext.example.msg('Create Company Success', 'Company was created successfully');
                	createWindow.close();
                	me.getCompanyGrid().getStore().reload();
                	
                },
                failure : function(form, action) {
                	var errorsArray = action.result.errors;
                	if (errorsArray != null || errorsArray.length > 0) {
                		Ext.Array.each(errorsArray, function(errorObject) {
                			var fieldName = errorObject.fieldName;
                			var errorMsg = errorObject.errorMessage;
                			var field = form.findField(fieldName);
                			if (field) {
                				field.markInvalid(errorMsg);
                			}
                		});
                	}
                	Ext.example.msg('Create Company Error', action.result.message);
                }
    		});
		}
	},
	
	showCreateCompanyWindow : function() {
		var createCompanyWindow = Ext.create('TGT.view.company.create.CreateCompanyWindow',{
			title: 'Create Company'
		});
		createCompanyWindow.show();
	},
	
	
	cancelCreateCompany : function() {
		var window = Ext.ComponentQuery.query('window#createCompanyWindow')[0];
		window.close();
	}
});