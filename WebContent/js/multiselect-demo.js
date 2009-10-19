/*!
 * Ext JS Library 3.0.0
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.onReady(function(){

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

   var ds = new Ext.data.ArrayStore({
        data: [
               ['1', 'Nome'],
               ['2', 'Email'],
               ['3', 'Endereco']
              ],
        fields: ['value','text'],
        sortInfo: {
            field: 'value',
            direction: 'ASC'
        }
    });

    /*
     * Ext.ux.form.ItemSelector Example Code
     */
    var isForm = new Ext.form.FormPanel({
        title: 'Selecione as colunas',
        width:700,
        bodyStyle: 'padding:10px;',
        renderTo: 'itemselector',
        items:[{
            xtype: 'itemselector',
            name: 'itemselector',
            fieldLabel: 'Colunas',
	        imagePath: '/spring-extjs-ejes/js/ext-3.0.0/ux/images/',
            multiselects: [{
                width: 250,
                height: 200,
                store: ds,
                displayField: 'text',
                valueField: 'value'
            },{
                width: 250,
                height: 200,
                store: [],
                tbar:[{
                    text: 'Limpar',
                    handler:function(){
	                    isForm.getForm().findField('itemselector').reset();
	                }
                }]
            }]
        }],

        buttons: [{
            text: 'Salvar',
            handler: function(){
                if(isForm.getForm().isValid()){
                    var conn = new Ext.data.Connection();
                    conn.request({
                        url: 'griddinamico/setColunas.action',
                        method: 'POST',
                        params: {"colunas": isForm.getForm().getValues(true)}
                    });
                }
            }
        }]
    });

});
