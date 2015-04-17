<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<title>Welcome to Target-Trak</title>
		<link rel="stylesheet" type="text/css" href="../css/login.css" />
	</head>
	<body>
		<div class="container">
			<div class="login">
				<h1>Target-Trak Login</h1>
				<c:url var="loginUrl" value="/j_spring_security_check"/>
				<form method="POST" action="${loginUrl}">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<c:if test="${not empty param.err}">
						<div>
							<p class="error_messages">
								<label>
									Username and/or password is invalid
								</label>
							</p>
						</div>
					</c:if>
					<c:if test="${not empty param.out}">
						<div>
							<p class="error_messages">
								<label>You've been successfully logged out</label>
							</p>
						</div>
					</c:if>
					<c:if test="${not empty param.time}">
						<div>
							<p class="error_messages">
								<label>You've been logged out due to inactivity</label>
							</p>
						</div>
					</c:if>
					<c:if test="${not empty param.accessdenied}">
						<div>
							<p class="error_messages">
								<label>Access Denied!</label>
							</p>
						</div>
					</c:if>
					<p>
						<input type="text" name="j_username" value="" placeholder="Username">
					</p>
					<p>
						<input type="password" name="j_password" value="" placeholder="Password">
					</p>
					<p class="remember_me">
						<label>
							<input type="checkbox" name="remember_me" id="remember_me"> Remember me on this computer
						</label>
					</p>
					<p class="submit">
						<input type="submit" name="commit" value="Login">
					</p>
				</form>
			</div>
	
			<div class="login-help">
				<p>
					Forgot your password? <a href="<c:url value='/sys/forgotPassword.htm' />">Click here to reset it</a>.
				</p>
			</div>
			<div class="login-help">
				<p>
					Not a member <a href="<c:url value='/sys/register.htm' />">Click here to register</a>.
				</p>
			</div>
		</div>
	</body>
</html>