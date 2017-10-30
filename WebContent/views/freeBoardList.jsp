<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/common.js"></script>
<script>
	function viewContents( title/*, date */) {
		//alert(date);
		var form = createForm("contentsForm", "freeContent", "post");

		//createinput("hidden", "title", title);
		createObj("hidden", "title", "title", "");
		//createObj("hidden", "date", "date", "");

		relationObj("contentsForm", "title");
		//relationObj("contentsForm", "date");
		//relationObj("contentsForm", "date");
		form.submit();
	}
</script>
</head>
<body>
	${freelist }
	
	<!-- <button onClick="viewContents("'bean.get(i).getTitle(), bean.get(i).getDate()")">bean.get(i).getTitle()</button> -->
</body>
</html>