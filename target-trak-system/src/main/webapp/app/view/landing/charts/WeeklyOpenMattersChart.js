Ext.define('TGT.view.landing.charts.WeeklyOpenMattersChart', {
    extend : 'Ext.chart.Chart',
    alias : 'widget.landing.charts.weeklyopenmatters',
    requires : [
	    'Ext.chart.Chart'
    ],
    store : 'WeeklyOpenMatters',
    width : 250,
    
    height : 250,
    axes: [
        {
        	type : 'Numeric',
        	position : 'left',
        	fields : ['matterOpenCount'],
        	title : 'Matters',
        	grid : true,
        	minimum : 0
        }, {
        	type : 'Category',
        	position : 'bottom',
        	fields : ['date'],
        	title : 'Trailing 7 Days'
        }
    ],
    series : [
        {
        	type : 'line',
        	axis : 'left',
        	highlight : true,
        	tips: {
        		trackMouse : true,
        		width : 140,
        		height : 28,
        		renderer : function(storeItem, item) {
        			this.setTitle(storeItem.get('matterOpenCount') + ' matters opened');
        		}
        	},
        	label : {
        		display : 'insideEnd', 'text-anchor': 'middle',
        		field : 'matterOpenCount',
        		//renderer : Ext.util.Format.numberRenderer('0'),
        		orientation : 'vertical',
        		color : '#333'
        	},
        	xField : 'date',
        	yField : 'matterOpenCount'
        }
    ]
});