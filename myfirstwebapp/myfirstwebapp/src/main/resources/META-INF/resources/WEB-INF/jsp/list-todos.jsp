<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<h1>Your todos   </h1>

	<table class="table">
		<thead>
			<tr>
				<th>Description</th>
				<th>Date</th>
				<th>Is Done?</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todo}" var="todos">
				<tr>
					
					<td>${todos.description}</td>
					<td>${todos.date}</td>
					<td>${todos.done}</td>
					<td><a href="update-todo?id=${todos.id}" class="btn btn-warning">Update </a></td>
					<td><a href="delete-todo?id=${todos.id}" class="btn btn-danger">Delete </a></td>
					
				</tr>
			
			</c:forEach>
		
		</tbody>
	</table>
	<a class="btn btn-success"  href="add-todo" >Add Todo</a>
</div>
<%@ include file="common/footer.jspf" %>