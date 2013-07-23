<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="slider">				
	<div id='coin-slider'  >
		    <a href="<c:url value='/comFerComanda.action' />"target="_self">
		        <img src='<c:url value='/images/presentacio/image1_${sessionScope.WW_TRANS_I18N_LOCALE}.jpg' />' >
		 
		    </a>
		     <a href="<c:url value='/img/elements/pdf_${sessionScope.WW_TRANS_I18N_LOCALE}.pdf' />" target="_blank">
		        <img src='<c:url value='/images/presentacio/image2_${sessionScope.WW_TRANS_I18N_LOCALE}.jpg' />' >
		     
		    </a>
		      <a href="#" target="_self">
		        <img src='<c:url value='/images/presentacio/image3_${sessionScope.WW_TRANS_I18N_LOCALE}.jpg' />' >
		  
		    </a>
		      <a href="#" target="_self">
		        <img src='<c:url value='/images/presentacio/image4_${sessionScope.WW_TRANS_I18N_LOCALE}.jpg' />' >
		       
		    </a>
		     <a href="#" target="_self">
		        <img src='<c:url value='/images/presentacio/image5_${sessionScope.WW_TRANS_I18N_LOCALE}.jpg' />' >
		       
		    </a>
		    <a href="#" target="_self">
		        <img src='<c:url value='/images/presentacio/image6_${sessionScope.WW_TRANS_I18N_LOCALE}.jpg' />' >
		      
		    </a>
		   <a href="#" target="_self">
		        <img src='<c:url value='/images/presentacio/image7_${sessionScope.WW_TRANS_I18N_LOCALE}.jpg' />' >	      
		    </a>
	</div>
</div>
<script type="text/javascript">
	$('#coin-slider').coinslider({ width: 1000,height: 299});
</script>



