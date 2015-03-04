<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<title>Welcome to Target-Trak</title>
		<link rel="stylesheet" type="text/css" href="/target-trak-system/css/registration.css" />
	</head>
	<body>
		<div class="container">
			<div class="registration">
				<h1>Target-Trak Forgot your Password</h1>
				
				<form:form method="POST" action="/target-trak-system/sys/handleForgotPasswordEmail.htm" modelAttribute="forgotPasswordForm">
					
					<p class="remember_me">
						<label>Email: <span class="registration-error-text">*</span>
							<form:errors cssClass="registration-error-text" cssStyle="registration-error-text" path="email" /> 
							<form:input type="text" path="email" />
						</label>
					</p>
					<p class="submit">
						<input type="submit" name="submitBtn" value="Submit">
					</p>
				</form:form>
			</div>
		</div>
	</body>
</html>