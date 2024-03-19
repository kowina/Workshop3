<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<div class="container-fluid">
    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">UsersCRUD</h1>
        <a href="<c:url value="/user/list"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
            <i class="fas fa-download fa-sm text-white-50"></i> Lista użytkowników</a>
    </div>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Dodaj uzytkownika</h6>
        </div>
        <div class="card-body">
            <div class="form-responsive">
                <form action="/user/add" method="post">
                    <div class="form-group">
                        <label >Nazwa</label>
                        <input type="text" class="form-control" name="userName" placeholder="Nazwa użytkownika" required>
                    </div>
                    <div class="form-group">
                        <label >Email</label>
                        <input type="email" class="form-control" name="email" placeholder="Email użytkownika" required>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" name="password" placeholder="Password" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Zapisz</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- /.container-fluid -->
<%@ include file="footer.jsp" %>
