<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<style>
			.contentSelectLote{
				display: flex;
				justify-content: center;
			}
		
			.contentSelectLote-label{
				margin:auto 10px;
			}
			
	</style>
	<title>Insert title here</title>
</head>
<body>

		<script src="./JS/ajaxPetLotesDeCampoParaSelect.js"></script>
		
		<div class="contentSelectLote" >
			<label class="contentSelectLote-label">Seleccione el lote del proyecto:</label>
			<select class="contentSelectLote-select" name="loteSelect"></select>
		</div>

</body>
</html>