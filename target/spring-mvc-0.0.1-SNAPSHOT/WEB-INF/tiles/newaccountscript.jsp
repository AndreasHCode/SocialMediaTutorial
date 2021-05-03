<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">

function onLoad() {
	$("#password").keyup(checkPasswordMatch);
	$("#confirm").keyup(checkPasswordMatch);
	
	$("#details").submit(canSubmit);
}

function canSubmit() {
	var password = $("#password").val();
	var confirm = $("#confirm").val();
	
	if (password != confirm) {
		alert("Passwords do not match")
		return false;
	} else {
		return true;
	}
}

function checkPasswordMatch() {
	var password = $("#password").val();
	var confirm = $("#confirm").val();
	
	if (password.length > 3) {
		if (password == confirm) {
			$("#matchPass").text("<fmt:message key='MatchedPasswords.user.password' />");
			$("#matchPass").addClass("valid");
			$("#matchPass").removeClass("error");
		} else {
			$("#matchPass").text("<fmt:message key='UnmatchedPasswords.user.password' />");
			$("#matchPass").addClass("error");
			$("#matchPass").removeClass("valid");
		}
	}
}

$(document).ready(onLoad);

</script>
