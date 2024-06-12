<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Contact"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
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
</head>
<body>
	<nav style="background: linear-gradient(to right, #f8a25c, #e1ec3c)"
		class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Contact Manager</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Home</a></li>



				</ul>
				<form class="d-flex" role="search"
					action="/ContactManager/Search_contact" method="get">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search" id="name" name="name" required>
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
			
			
			<a href="${pageContext.request.contextPath}/logout" type="submit" class="btn btn-danger" style="margin-left:5px">Logout</a>
		
		
		</div>

		</div>
	</nav>
	<% if (application.getAttribute("message")!=null){
		
	
		
	%>

	<div class="container mt-2 container-fluid ">
		<div
			class="alert alert-<%= application.getAttribute("alertType") %> alert-dismissible fade show"
			role="alert">
			<strong>Message!</strong>
			<%= application.getAttribute("message") %>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</div>
	<%} application.setAttribute("message",null);
    %>
	<!-- table -->

	<table class="table table-info table-striped">

		<thead>
			<tr>
				<th scope="col">Sr. no.</th>
				<th scope="col">Name</th>
				<th scope="col">Delete</th>
				<th scope="col">View Detail</th>
			</tr>
		</thead>
		<tbody>
			<%
            List<Contact> contacts = (List<Contact>) request.getAttribute("contacts");
         //   if (contacts != null && !contacts.isEmpty()) {
                int i = 1; // Declare 'i' as a local variable here
                for (Contact contact : contacts) {
            %>
			<tr>
				<th scope="row"><%=i %></th>
				<td><%= contact.getName() %></td>

				<td>

					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#staticBackdrop<%=i %>">Delete</button> <!-- delete modal  -->

					<div class="modal fade" id="staticBackdrop<%=i%>"
						data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
						aria-labelledby="staticBackdropLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content"
								style="background: linear-gradient(to right, #fcb174, #e5ee6e)">
								<div class="modal-header">
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<h5>Do you want to delete This Contact</h5>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Deny</button>

									<form action="/ContactManager/contacts" method="post">
										<input type="hidden" name="operation" value="delete">
										<input type="hidden" name="oldnumber"
											value="<%= contact.getNumber() %>">
										<button type="submit" class="btn btn-danger">Delete</button>
									</form>
								</div>
							</div>
						</div>
					</div> <!--delete modal ended -->


				</td>



				<td><button type="submit" class="btn btn-primary"
						data-bs-toggle="modal" data-bs-target="#addBookModal<%=i %>">View
						Detail</button> <!--detail modal -->
					<div class="modal fade" id="addBookModal<%=i %>" tabindex="-1"
						aria-labelledby="addBookModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content"
								style="background: linear-gradient(to right, #fcb174, #e5ee6e)">
								<div class="modal-header">
									<h5 class="modal-title" id="addBookModalLabel">User
										Details</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<table class="table-warning">
										<thead>
											<tr>
												<th scope="col">Variable</th>
												<th scope="col">Value</th>
											</tr>
										</thead>
										<tbody>
											<tr>

												<td>Name</td>
												<td><%=contact.getName()%></td>

											</tr>
											<tr>

												<td>Contact</td>
												<td><%=contact.getNumber()%></td>

											</tr>
											<tr>

												<td>Address</td>
												<td><%=contact.getAddress()%></td>
											</tr>
										</tbody>
									</table>

									<button type="button" class="btn btn-primary"
										data-bs-toggle="modal"
										data-bs-target="#updateContactModal<%=i %>">Update</button>
								</div>
								<!-- Closing the modal-body div -->
							</div>
							<!-- Closing the modal-content div -->
						</div>
						<!-- Closing the modal-dialog div -->
					</div> <!-- Closing the modal div --> <!--update modal ended -->

					<div class="modal fade" id="updateContactModal<%=i %>"
						tabindex="-1" aria-labelledby="updateContactModalLabel"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content"
								style="background: linear-gradient(to right, #fcb174, #e5ee6e)">
								<div class="modal-header">
									<h5 class="modal-title" id="updateContactModalLabel">Add
										new Contact</h5>


									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>

								</div>

								<div class="modal-body">
									<form id="addContactForm" action="/ContactManager/contacts"
										method="post">
										<input type="hidden" name="operation" value="update">
										<input type="hidden" name="oldnumber"
											value="<%= contact.getNumber() %>">
										<div class="mb-3">
											<label for="name" class="form-label">Name</label>
											 <input
												placeholder="Old name = <%=contact.getName()%>" type="text"
												class="form-control" id="name" name="name" value="" required>
										</div>

										<div class="mb-3">
											<label for="number" class="form-label">Contact Number</label>
											<input placeholder="Old number = <%=contact.getNumber()%>" type="text" class="form-control" id="number"
												name="number" value="" required>
										</div>
										<div class="mb-3">
											<label for="address" class="form-label">Address</label> <input
												type="text" class="form-control" id="address" name="address"
												value="" required>
										</div>
										<button type="submit" class="btn btn-success">Submit</button>
									</form>
								</div>
							</div>
						</div>
					</div> <!--update modal ended --></td>





			</tr>






			<%
                    i++;
                }
                
             i=0;   
           
           %>
		</tbody>
	</table>



	<div style="position: absolute; right: 10px;">
		<button type="submit" class="btn btn-warning" data-bs-toggle="modal"
			data-bs-target="#addcontactModal">Add new Contact</button>
	</div>

	<!-- add new contact modal -->
	<div class="modal fade" id="addcontactModal" tabindex="-1"
		aria-labelledby="addContactModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"
				style="background: linear-gradient(to right, #fcb174, #e5ee6e)">
				<div class="modal-header">
					<h5 class="modal-title" id="addContactModalLabel">Add new
						Contact</h5>


					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<div class="modal-body">
					<form id="addContactForm" action="/ContactManager/contacts"
						method="post">

						<div class="mb-3">
							<label for="name" class="form-label">Name</label> <input
								type="text" class="form-control" id="name" name="name" value=""
								required>
						</div>
						<div class="mb-3">
							<label for="number" class="form-label">Contact Number</label> <input
								type="text" class="form-control" id="number" name="number"
								value="" required>
						</div>
						<div class="mb-3">
							<label for="address" class="form-label">Address</label> <input
								type="text" class="form-control" id="address" name="address"
								value="" required>
						</div>
						<button type="submit" class="btn btn-success">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>







	<script
		src="	https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>