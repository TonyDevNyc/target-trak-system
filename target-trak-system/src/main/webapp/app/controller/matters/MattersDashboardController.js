Ext.define('TGT.controller.matters.MattersDashboardController', {
	
	extend : 'TGT.controller.BaseController',
	
	requires : [
       'TGT.store.MatterStatusCharts',
       'TGT.view.landing.charts.MatterStatusChart',
       'TGT.view.landing.charts.WeeklyOpenMattersChart',
       'TGT.view.landing.grids.MatterTasksGrid'
       
    ],

	stores : [ 'MatterStatusCharts', 'WeeklyOpenMatters', 'MatterTasks'],

	models : [ 'MatterStatusChart', 'DailyOpenMatter', 'MatterTask'],

	views : [ 
        'landing.charts.MatterStatusChart',
        'landing.charts.WeeklyOpenMattersChart',
        'landing.grids.MatterTasksGrid'
    ],

    onLaunch : function() {
    	var matterStatusChartStore = this.getMatterStatusChartsStore();
    	matterStatusChartStore.load();
    	
    	var weeklyOpenMatterStore = this.getWeeklyOpenMattersStore();
    	weeklyOpenMatterStore.load();
    	
    	var matterTaskStore = this.getMatterTasksStore();
    	matterTaskStore.load();
    },
    
    init : function() {
    	
    }
});