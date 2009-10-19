Ext.onReady(function(){
	
	Ext.BLANK_IMAGE_URL = '/spring-extjs-ejes/js/ext-3.0.0/resources/css/resources/images/default/s.gif';

	var columns = [];
	var sm = new Ext.grid.CheckboxSelectionModel();
	columns[0] = sm;
	var gridColModel = new Ext.grid.ColumnModel(columns);
	var index = 1;
	
	//cria as colunas
	var jsonstore = new Ext.data.Store({
		proxy: new Ext.data.HttpProxy(
			new Ext.data.Connection({
				url:'griddinamico/getColumnsJson.action'
			})
		),
		reader:new Ext.data.JsonReader({
			root:'rows',
			fields:['header','align','sortable','width','dataIndex']
		}),
		listeners: {
	    'load': function () {            
		jsonstore.each(function(r) {
			columns[index] = { header: r.data['header'],
							   align: r.data['align'], 
							   sortable: r.data['sortable'],
							   width: r.data['width'],
							   dataIndex: r.data['dataIndex']};
	        index++;
	      });
	      gridColModel.setConfig(columns);
	    }
	  }

	});
	
	jsonstore.load();
	
	//carrega os dados
	var data = new Ext.data.Store({
		proxy: new Ext.data.HttpProxy(
			new Ext.data.Connection({
				url:'griddinamico/getStoreFieldJson.action'
			})
		),
		reader:new Ext.data.JsonReader({})
	});

	var cm = gridColModel;


    // cria o grid
    var grid = new Ext.grid.GridPanel({
    	autoHeight:true,
		autoWidth:true,
		region: 'center',
		split: true,
		border:true,
		forceFit:true,
		store:data,
		sm: sm,
		cm:cm,
		title: 'Grid Dinâmico - Extjs',
		frame:true,
		bbar:new Ext.PagingToolbar({
			pageSize:10,
			store:data,
			displayInfo:true,
			displayMsg: 'Mostrando {0} - {1} de {2}',
			emptyMsg:'sem dados'
		}),
		tbar : [
		        {
		          text   : 'Imprimir',
		          handler: function() {
		        	Ext.ux.GridPrinter.print(grid);
		          }
		        }
		      ]
    });
    
    data.load({params: {meta: true, start: 0}});

    //div do grid
    grid.render('grid-dinamico');
});