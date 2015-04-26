Ext.define('TGT.view.landing.charts.MatterStatusChart', {
    
	extend : 'Ext.chart.Chart',
    
    alias : 'widget.landing.charts.matterstatus',
    
    requires : [
	    'Ext.chart.Chart'
    ],
    
    width : 250,
    
    height : 250,
    
    store : 'MatterStatusCharts',
	
    
	
    legend : {
		position: 'right',
		visible: true
	},
	
//	items : [
//        {
//        	type  : 'text',
//            text  : 'Matters Status Breakdown',
//            font  : '14px Arial',
//            width : 100,
//            height: 30,
//            x : 50, //the sprite x position
//            y : 10  //t
//        } 
//    ],
    
    series : [
        {
        	type : 'pie',
        	angleField : 'count',
        	showInLegend : true,
        	tips : {
        		trackMouse: true,
                width: 140,
                height: 28,
                renderer: function(storeItem, item) {
                	this.setTitle(storeItem.get('label') +': '+ storeItem.get('percentage') + '%');
                }
        	},
        	highlight : {
                segment: {
                    margin: 20
                }
            },
        	label : {
        		field: 'label',
                display: 'rotate',
                contrast: true,
                font: '10px Arial',
                hideLessThan: 5
        	}
        }
    ]
});