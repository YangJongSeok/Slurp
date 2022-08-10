<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
		history.back(-1);
	}
</script>
</head>
<body>

</body>
</html>