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
       'TGT.view.company.create.CreateCompanyForm',
       'TGT.view.company.edit.EditCompanyWindow',
       'TGT.view.company.edit.EditCompanyForm'
    ],

	stores : [ 'Companies', 'Cities', 'Countries', 'States'],

	models : [ 'Company', 'LabelValuePair'],

	views : [ 
        'company.search.CompanyAdministration'
    ],

	refs : [ 
        { ref : 'companySearchForm', 	selector : '[xtype=company.search.companysearchform]' }, 
        { ref : 'companyGrid', 			selector : '[xtype=company.search.companygrid]' }, 
        { ref : 'createCompanyWindow',  selector : '[xtype=company.create.createcompanywindow]' },
        { ref : 'searchCompanyButton', 	selector : 'button[name="searchCompanyBtn"]' }, 
    	{ ref : 'createCompanyButton', 	selector : 'button[name="createCompanyBtn"]' }, 
    	{ ref : 'editCompanyWindow',  	selector : 'window#editCompanyWindow' },
    	{ ref : 'saveCompanyButton', 	selector : 'button#saveCompanyBtn' }
    ],
	
	onLaunch : function() { },
	
	init : function() {
		this.application.on('companyactionclick', this.showEditCompanyWindow);
				
		this.getCitiesStore().load({ params : { referenceDataType : 'cities' }});
		this.getStatesStore().load({ params : { referenceDataType : 'states' } });
		this.getCountriesStore().load({ params : { referenceDataType : 'countries' } });
		this.getCompaniesStore().load();
		
		this.control({
            'button#showCreateCompanyBtn' 	: { click : this.showCreateCompanyWindow },
            'button#cancelCreateCompanyBtn' : { click : this.cancelCreateCompany },
            'button#createCompanyBtn' 		: { click : this.createCompany },
            'button#searchCompanyBtn' 		: { click : this.searchCompaniesByCriteria },
            'button#saveCompanyBtn'			: { click : this.updateCompany },
            'button#cancelEditCompanyBtn'	: { click : this.cancelUpdateCompany }
		});
	},
	
	updateCompany : function() {
		var me = this;
		var form = this.getSaveCompanyButton().up('form').getForm();
		form.submit({
			submitEmptyText : false,
			url : '/target-trak-system/sys/company/updateCompany.json',
			method : 'POST',
			headers : {
				'X-CSRF-TOKEN' : TGT.security.CsrfToken.getCsrfToken()
			},
			waitMsg : 'Updating Company. Please wait...',
			success : function(form, action) {
            	me.getCompanyGrid().getStore().reload();
            	me.getEditCompanyWindow().close();
            	Ext.example.msg('Update Company Success', 'Company has been updated successfully');
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
            	Ext.example.msg('Update Company Error', action.result.message);
            }
		});
	},
	
	cancelUpdateCompany : function() {
		this.getEditCompanyWindow().close();
		Ext.example.msg('Edit Company Cancelled', 'Editing of company has been cancelled.');
	},
	
	showEditCompanyWindow : function(record) {
		var editCompanyWindow = Ext.create('TGT.view.company.edit.EditCompanyWindow',{
			title: 'Edit Company'
		});
		editCompanyWindow.down('form').loadRecord(record);
		editCompanyWindow.show();
	},
	
	searchCompaniesByCriteria : function() {
		var me = this;
		var searchButton = me.getSearchCompanyButton();
		var form = searchButton.up('form').getForm();
		form.submit({
			submitEmptyText : false,
			url : '/target-trak-system/sys/company/searchCompaniesByCriteria.json',
			method : 'POST',
			headers : {
				'X-CSRF-TOKEN' : TGT.security.CsrfToken.getCsrfToken()
			},
			params : {
				page: 1,
				start : 0,
				limit : 15
			},
			waitMsg : 'Searching for Companies. Please wait...',
			success : function(form, action) {
            	var companiesStore = me.getCompanyGrid().getStore();
            	companiesStore.loadData(action.result.data);
            	companiesStore.totalCount = action.result.totalSize;
            	Ext.ComponentQuery.query('pagingtoolbar')[0].onLoad();
            }
		});
	},
	
	createCompany : function() {
		var createButton = this.getCreateCompanyButton(); 
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