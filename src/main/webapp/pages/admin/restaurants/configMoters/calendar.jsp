<%@ page import="java.util.Locale"%>
    
<script type="text/javascript">

Ext.onReady(function(){

	new Ext.Button({
        text: '',
        renderTo:'div_buttons_prev',
        scale: 'medium',
        width:'50px',
        iconCls: 'back',
        iconAlign: 'left',
        handler:function(){
        	previousYear('<%=Locale.getDefault().getLanguage()%>');
        }
	});
	new Ext.Button({
        text: '',
        renderTo:'div_buttons_next',
        scale: 'medium',
        width:'50px',
        iconCls: 'forward',
        iconAlign: 'right',
        handler:function(){
        	nextYear('<%=Locale.getDefault().getLanguage()%>');
        }
	});
	
});

</script>
<div> 
     <table> 
      <tr>	
       <td>
          <div id="div_calendar" class="month" ></div>
       </td>
       <td style=vertical-align:bottom >       
          <div id="div_buttons_prev" ></div>	     
       </td>
       <td style=vertical-align:bottom >       
          <div id="div_buttons_next" ></div>
       </td>            
      </tr>	
     </table>
</div>      	      	
      	
