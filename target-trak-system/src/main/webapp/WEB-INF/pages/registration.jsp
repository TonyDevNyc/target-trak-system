<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<title>Welcome to Target-Trak</title>
		<link rel="stylesheet" type="text/css" href="/target-trak-system/css/registration.css" />
	</head>
	<body>
		<div class="container">
			<div class="registration">
				<h1>Target-Trak Registration</h1>
				<form:form method="POST" action="/target-trak-system/sys/registerNewUser.htm" modelAttribute="registrationForm">
					<p class="remember_me">
						<label>First Name: <span class="registration-error-text">*</span>
							<form:errors cssClass="registration-error-text" cssStyle="registration-error-text" path="firstName" /> 
							<form:input type="text" path="firstName" />
						</label>
						<p class="remember_me">
						<label>Last Name: <span class="registration-error-text">*</span>
							<form:errors cssClass="registration-error-text" cssStyle="registration-error-text" path="lastName" /> 
							<form:input type="text" path="lastName" />
						</label>
					</p>
					<p class="remember_me">
						<label>Email: <span class="registration-error-text">*</span>
							<form:errors cssClass="registration-error-text" cssStyle="registration-error-text" path="email" /> 
							<form:input type="text" path="email" />
						</label>
					</p>
					<p class="remember_me">
						<label>Mobile: <span class="registration-error-text">*</span>
							<form:errors cssClass="registration-error-text" cssStyle="registration-error-text" path="mobileNumber" /> 
							<form:input type="text" path="mobileNumber" />
						</label>
					</p>
					<p class="remember_me">
						<label>Username: <span class="registration-error-text">*</span>
							<form:errors cssClass="registration-error-text" cssStyle="registration-error-text" path="username" /> 
							<form:input type="text" path="username" />
						</label>
					</p>
					<p class="remember_me">
						<label>Password: <span class="registration-error-text">*</span>
							<form:errors cssClass="registration-error-text" cssStyle="registration-error-text" path="password" /> 
							<form:password path="password" />
						</label>
					</p>
					<p class="remember_me">
						<label>Repeat Password: <span class="registration-error-text">*</span>
							<form:errors cssClass="registration-error-text" cssStyle="registration-error-text" path="password" /> 
							<form:input type="password" path="repeatedPassword" />
						</label>
					</p>
					<p class="submit">
						<input type="submit" name="registerBtn" value="Register">
					</p>
				</form:form>
			</div>
		</div>
	</body>
</html>