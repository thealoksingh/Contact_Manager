<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
    />
    <script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
	integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    
    <title>Login/SignUP</title>
    <style type="text/css">
    @import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Montserrat', sans-serif;
}

body {
  background-color: #c9d6ff;
  background: linear-gradient(to right, #e2e2e2, #c9d6ff);
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  height: 100vh;
}
.header h1 {
  font-size: 7vw;
  font-weight: 800;
  margin-bottom: 50px;
  background: linear-gradient(to right, #e2e2e2, #c9d6ff);
  padding: 16px 24px;
  position: relative;
  border-radius: 8px;
  box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.01);

  &::after {
    position: absolute;
    content: '';
    top: 15px;
    left: 0;
    right: 0;
    z-index: -1;
    height: 100%;
    width: 100%;
    transform: scale(0.9) translateZ(0);
    filter: blur(15px);

    background: rgb(63, 114, 251);
    background: linear-gradient(
      90deg,
      rgba(63, 114, 251, 1) 5%,
      rgba(252, 70, 107, 1) 57%,
      rgba(252, 70, 107, 1) 82%
    );
    background-size: 300% 300%;
    animation: animateGlow 1.25s linear infinite;
  }
}

@keyframes animateGlow {
  0% {
    background-position: 0% 50%;
  }
  100% {
    background-position: 200% 50%;
  }
}

.containers {
  background-color: #fff;
  border-radius: 30px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.35);
  position: relative;
  overflow: hidden;
  width: 50vw;
  max-width: 100%;
  min-height: 480px;
  height: 70vh;
}

.containers p {
  font-size: 14px;
  line-height: 20px;
  letter-spacing: 0.3px;
  margin: 20px 0;
}

.containers span {
  font-size: 12px;
}

.containers a {
  color: #333;
  font-size: 13px;
  text-decoration: none;
  margin: 15px 0 10px;
}

.containers button {
  background-color: orange;
  color: #fff;
  font-size: 12px;
  padding: 10px 45px;
  border: 1px solid transparent;
  border-radius: 8px;
  font-weight: 600;
  letter-spacing: 0.5px;
  text-transform: uppercase;
  margin-top: 10px;
  cursor: pointer;
}

.containers button.hidden {
  background-color: transparent;
  border-color: #fff;
}

.containers form {
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 40px;
  height: 100%;
}

.containers input {
  background-color: #eee;
  border: none;
  margin: 8px 0;
  padding: 10px 15px;
  font-size: 13px;
  border-radius: 8px;
  width: 100%;
  outline: none;
}

.form-containers {
  position: absolute;
  top: 0;
  height: 100%;
  transition: all 0.6s ease-in-out;
}

.sign-in {
  left: 0;
  width: 50%;
  z-index: 2;
  font-size: 2vw;
}

.containers.active .sign-in {
  transform: translateX(100%);
}

.sign-up {
  left: 0;
  width: 50%;
  opacity: 0;
  z-index: 1;
  font-size: 1.19vw;
}

.containers.active .sign-up {
  transform: translateX(100%);
  opacity: 1;
  z-index: 5;
  animation: move 0.6s;
}

@keyframes move {
  0%,
  49.99% {
    opacity: 0;
    z-index: 1;
  }
  50%,
  100% {
    opacity: 1;
    z-index: 5;
  }
}

.social-icons {
  margin: 20px 0;
}

.social-icons a {
  border: 1px solid #ccc;
  border-radius: 20%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 3px;
  width: 40px;
  height: 40px;
}

.toggle-containers {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: all 0.6s ease-in-out;
  border-radius: 150px 0 0 100px;
  z-index: 1000;
}

.containers.active .toggle-containers {
  transform: translateX(-100%);
  border-radius: 0 150px 100px 0;
}

.toggle {
  background-color: orange;
  height: 100%;
  background: linear-gradient(to right, #f8a25c, #e1ec3c);
  color: #fff;
  position: relative;
  left: -100%;
  height: 100%;
  width: 200%;
  transform: translateX(0);
  transition: all 0.6s ease-in-out;
}

.containers.active .toggle {
  transform: translateX(50%);
}

.toggle-panel {
  position: absolute;
  width: 50%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 30px;
  text-align: center;
  top: 0;
  transform: translateX(0);
  transition: all 0.6s ease-in-out;
}

.toggle-left {
  transform: translateX(-200%);
}

.containers.active .toggle-left {
  transform: translateX(0);
}

.toggle-right {
  right: 0;
  transform: translateX(0);
}

.containers.active .toggle-right {
  transform: translateX(200%);
}
    </style>
  </head>

  <body>
  
   <!-- alert -->
    <% if (application.getAttribute("message")!=null){
		
	
		
	%>
	
	<div class="container mt-5 container-fluid  ">
        <div class="alert alert-<%= application.getAttribute("alertType") %> alert-dismissible fade show" role="alert">
            <strong>Message!</strong> <%= application.getAttribute("message") %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </div>
    <%} application.setAttribute("message",null);
    %>
      <!-- alert close -->
      
    <div class="header">
      <h1 >Contact Manager</h1>
    </div>
     
    
    
    <div class="containers" id="containers">
      <div class="form-containers sign-up">
        <form action="/ContactManager/users" method="Post" >
          <h1>Create Account</h1>

          <!-- dfghjj -->
         
          <!-- dfghj -->
          <span>or use your email for registeration</span>
          <input type="text" placeholder="Name" name="name"/>
          <input type="email" placeholder="Email" name="email"/>
          <input type="password" placeholder="Password" name="psw" />
        <!--  <input type="confirmpassword" placeholder="Confirm Password" /> -->
          <button type="submit">Create Account</button>
        </form>
      </div>
      <div class="form-containers sign-in">
        <form action="/ContactManager/users" method="get">
          <h1>Log In</h1>
          <div class="social-icons">
            <a href="#" class="icon"
              ><i class="fa-brands fa-google-plus-g"></i
            ></a>
            <a href="#" class="icon"><i class="fa-brands fa-facebook-f"></i></a>
            <a href="#" class="icon"><i class="fa-brands fa-github"></i></a>
            <a href="#" class="icon"
              ><i class="fa-brands fa-linkedin-in"></i
            ></a>
          </div>
          <span>or use your email password</span>
          <input type="email" placeholder="Email"  name="email"/>
          <input type="password" placeholder="Password" name="psw"/>
          <a href="#">Forget Your Password?</a>
          <button type="submit">Log In</button>
        </form>
      </div>
      <div class="toggle-containers">
        <div class="toggle">
          <div class="toggle-panel toggle-left">
            <h1>Welcome Back!</h1>
            <p>Enter your personal details to use all of site features</p>
            <button class="hidden" id="login">Log In</button>
          </div>
          <div class="toggle-panel toggle-right">
            <h1>Welcome, Be Part of Family!</h1>
            <p>Enter your personal details to use all of site features</p>
            <button class="hidden" id="register">Sign Up</button>
          </div>
        </div>
      </div>
    </div>

    <script >
    const containers = document.getElementById('containers');
    const registerBtn = document.getElementById('register');
    const loginBtn = document.getElementById('login');

    registerBtn.addEventListener('click', () => {
      containers.classList.add('active');
    });

    loginBtn.addEventListener('click', () => {
      containers.classList.remove('active');
    });
</script>
  </body>
</html>