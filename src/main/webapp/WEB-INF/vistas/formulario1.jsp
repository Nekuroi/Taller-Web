<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
	<div class = "container">
		<c:if test="${not empty datos}">
			<h4><span>${datos}</span></h4>
			<br>
		</c:if>
			
			<div id="datos" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<form:form action="enviar-formulario" method="POST" modelAttribute="persona">
			    	<h3 class="form-signin-heading">Taller Web I</h3>
					<hr class="colorgraph"><br>
					<div class="form-group">
						<label for="nombre">Nombre</label>
						<form:input path="nombre" id="nombre" type="text" class="form-control" />
					</div>
					<div class="form-group">
						<label for="apellido">Apellido</label>
						<form:input path="apellido" id="apellido" type="text" class="form-control"/>
					</div>
					<div class="form-group">
						<label for="direccion">Direccion</label>	
						<form:input path="direccion" id="direccion" type="text" class="form-control"/>
					</div>
					
					<button class="btn btn-lg btn-primary btn-block" type="Submit">Ok</button>
				</form:form>

				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>	
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	
	
	
</body>
</html>