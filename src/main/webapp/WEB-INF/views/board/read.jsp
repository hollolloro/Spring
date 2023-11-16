<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>화이팅♥</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<div class="container" style="margin-top: 100px">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card-shadow">
					<div class="card-body">

						<div class="form-group">
							<label for="board_writer_name">작성자</label> <input type="text"
								class="form-control" id="board_writer_name"
								name="board_writer_name" value="${readContent.content_writer_name}" disabled="disabled" />

						</div>

						<div class="form-group">
							<label for="board_date">작성날짜</label> <input type="text"
								class="form-control" id="board_date" name="board_date"
								value="${readContent.content_date }" disabled="disabled" />

						</div>

						<div class="form-group">
							<label for="board_subject">제목</label> <input type="text"
								class="form-control" id="board_subject" name="board_subject"
								value="${readContent.content_subject }" disabled="disabled" />

						</div>

						<div class="form-group">
							<label for="board_content">내용</label>
							<textarea class="form-control" id="board_content"
								name="board_content" rows="10" style="resize: none"
								disabled="disabled">${readContent.content_text }</textarea>
						</div>


						<div class="form-group">
							<div class="text-right">
								<a href="${root }board/main" class="btn btn-primary">목록보기</a> <a
									href="${root }board/modify" class="btn btn-info">수정하기</a> <a
									href="${root }board/delete" class="btn btn-danger">삭제하기</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>

		</div>
	</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>






