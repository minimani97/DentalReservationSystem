<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>JJONDDuk_Mypage</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta
	content="A fully featured admin theme which can be used to build CRM, CMS, etc."
	name="description" />
<meta content="Coderthemes" name="author" />
<!-- App favicon -->
<link rel="shortcut icon" href="assets/images/favicon.ico">

<!-- third party css -->
<link href="resources/css/fullcalendar.min.css" rel="stylesheet"
	type="text/css">
<!-- third party css end -->

<!-- App css -->
<link href="resources/css/icons.min.css" rel="stylesheet"
	type="text/css" />
<link href="resources/css/app.min.css" rel="stylesheet" type="text/css" />

<!-- jquery.js -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- jquery.js ends -->

<script src="resources/js/login.js"></script>

<script>
	$(document).ready(function() {	
		$('#logout-btn').hide();
		setPatientInfo();
	});
	
	function setPatientInfo() {
		if("${sessionScope.p_code}" == "") {
			alert("로그인이 필요합니다.");
			window.location.href='http://localhost:8090/reservation/';
		} else {
			var p_code = "${sessionScope.p_code}";
			$("#user_code").val(p_code);
			
			//$('#logout-btn').show();
		}

		console.log("세션값이당: " + p_code);
	}
</script>

<!-- mypage js -->
<script src="resources/js/mypage.js"></script>
<!-- end mypage js -->

</head>

<body>
	<!-- Begin page -->
	<div class="wrapper">

		<!-- res_hidden values -->
		<form>
			<input type="hidden" id="user_code" value="">
			<input type="hidden" id="selectedMember" value="">
		</form>
		<!-- res_hidden values ends -->

		<!-- ========== Left Sidebar Start ========== -->
		<div class="left-side-menu">

			<div class="slimscroll-menu" id="left-side-menu-container">

				<!-- LOGO -->
				<a href="http://localhost:8090/reservation/homeForPatient" class="logo text-center"> <span
					class="logo-lg"> <img src="resources/image/logo.png" alt=""
						height="40">
				</span> <span class="logo-sm"> <img src="assets/images/logo_sm.png"
						alt="" height="16">
				</span>
				</a>

				<!--- Sidemenu -->
				<ul class="metismenu side-nav">

					<li class="side-nav-title side-nav-item">메뉴</li>

					<li class="side-nav-item"><a
						href="http://localhost:8090/reservation/homeForPatient"
						class="side-nav-link"> <i class="dripicons-home"></i><span>
								홈 </span></span>
					</a></li>

					<li class="side-nav-item"><a
						href="http://localhost:8090/reservation/reservation"
						class="side-nav-link"> <i class="dripicons-calendar"></i><span>
								진료예약 </span></span>
					</a></li>

					<li class="side-nav-item active"><a href="javascript:void(0);"
						class="side-nav-link" aria-expanded="true"> <i
							class="dripicons-user"></i> <span> 마이페이지 </span> <span
							class="menu-arrow"></span>
					</a>
						<ul class="side-nav-second-level collapse in"
							aria-expanded="false" style="">
							<li><a href="http://localhost:8090/reservation/mypage"
								area-expanded="true">정보 조회 / 수정</a></li>
							<li><a
								href="http://localhost:8090/reservation/reservationList">예약내역
									확인</a></li>
						</ul></li>

					<div class="clearfix"></div>
			</div>
			<!-- Sidebar -left -->

		</div>
		<!-- Left Sidebar End -->

		<!-- ============================================================== -->
		<!-- Start Page Content here -->
		<!-- ============================================================== -->

		<div class="content-page">
			<div class="content">

				<!-- Topbar Start -->
				<div class="navbar-custom">
					<ul class="list-unstyled topbar-right-menu float-right mb-0">
						<li class="dropdown notification-list"><a
							class="nav-link dropdown-toggle nav-user arrow-none mr-0"
							data-toggle="dropdown" href="#" role="button"
							aria-haspopup="false" aria-expanded="false"> <span
								class="account-user-avatar"> </span> <span> <span
									class="font-weight-bold">  ${sessionScope.p_name} 님 </span> <span
									class="account-position">   환영합니다.   </span>
							</span>

						</a>
							<div
								class="dropdown-menu dropdown-menu-right dropdown-menu-animated topbar-dropdown-menu profile-dropdown">
								<!-- item-->
								<a href="javascript:void(0);" class="dropdown-item notify-item">
									<i class="mdi mdi-logout mr-1" onclick="logout()"></i> <span
									onclick="logout()">로그아웃</span>
								</a>

							</div></li>

					</ul>
					<button class="button-menu-mobile open-left disable-btn">
						<i class="mdi mdi-menu"></i>
					</button>
					<!-- <button id="logout-btn" class="btn btn-primary mt-2 mb-2 float-right" onclick="logout()" style="display:none">로그아웃</button> -->
				</div>
				<!-- end Topbar -->

				<!-- Start Content-->
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="page-title-box">
								<h4 class="page-title">정보 조회 / 수정</h4>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<p class="text-muted">
									※ 이름을 선택해 주세요 : <select id="family-list"
										onchange="getPatientInfo(this.value)"><option></option></select>
								</p>

								<div class="row">
									<div class="col-lg-6">
										<form>

											<div class="form-group mb-3">
												<label for="simpleinput">이름</label> <input type="text"
													id="name" class="form-control" disabled>
											</div>

											<div class="form-group mb-3">
												<label for="example-date">생년월일</label> <input
													class="form-control" id="birth" type="date" name="date"
													disabled>
											</div>

											<div class="form-group mb-3">
												<label for="simpleinput">주소</label> <input type="text"
													id="address" class="form-control">
											</div>

											<div class="form-group">
												<label for="simpleinput">전화번호</label> <input type="text"
													id="phone-num" class="form-control"
													data-toggle="input-mask" data-mask-format="000-0000-0000"
													maxlength="13">
											</div>

											<input type="button" style="float: right"
												id="change-myinfo-btn" class="btn btn-primary"
												onclick="editMyInfo()" value="정보 수정하기" disabled>

										</form>
									</div>
									<!-- end col -->

									<div class="col-lg-6">
										<form>

											<div class="form-group mb-3">
												<label for="example-password">비밀번호</label> <input
													type="password" id="change-password" class="form-control"
													value="" oninput="checkPassword()">
											</div>

											<div class="form-group">
												<label for="example-password">비밀번호 확인</label> <input
													type="password" id="check-password" class="form-control"
													value="" oninput="checkPassword()">
												<div class="invalid-feedback">비밀번호가 일치하지 않습니다.</div>
											</div>

											<input type="button" style="float: right"
												id="change-password-btn" class="btn btn-primary"
												onclick="changePassword()" value="비밀번호 변경하기" disabled>

										</form>
									</div>
									<!-- end col -->
								</div>
								<!-- end row-->
							</div>
							<!-- end card-body -->
						</div>
						<!-- end card -->
					</div>
					<!-- end col -->
				</div>

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">

								<h4 class="header-title">가족 목록</h4>
								<p class="text-muted font-14 mb-4">가족 등록을 원하시면 하단의 가족등록 버튼을
									눌러 등록해주세요. ( 가족으로 등록해야 같은 아이디로 예약이 가능합니다. )</p>

								<div class="table-responsive-sm">
									<table class="table table-centered mb-0">
										<thead>
											<tr>
												<th>이름</th>
												<th>생년월일</th>
												<th>관계</th>
												<th></th>
											</tr>
										</thead>
										<tbody id="family-list-tbody">
										</tbody>
									</table>
								</div>
								<!-- end table-responsive-->

								<br> <input type="button" style="float: right"
									id="change-password-btn" class="btn btn-primary"
									onclick="modalInit()" data-toggle="modal"
									data-target="#myModal" value="가족등록">

								<script>
									function modalInit() {
										$('#register-name').val("");
										$('#register-relationship').val("");
										$('#register-birth').val("");
										$('#register-addr').val("");
										$('#register-tel').val("");
									}
								</script>

								<!-- Modal content -->
								<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
									aria-labelledby="myModalLabel" style="display: none;"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h4 class="modal-title" id="myModalLabel">가족등록</h4>
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">×</button>
											</div>
											<div class="modal-body">
												<form>
													<label>가족으로 등록하려는 분의 정보를 입력해주세요.<br>
													<br></label>

													<div class="form-group mb-3">
														<label for="example-password">이름</label> <input
															type="text" id="register-name" class="form-control"
															value="" oninput="checkValues()">
													</div>

													<div class="form-group">
														<label for="example-password">관계</label> <input
															type="text" id="register-relationship"
															class="form-control" value="" oninput="checkValues()">
													</div>

													<div class="form-group mb-3">
														<label for="example-date">생년월일</label> <input
															class="form-control" id="register-birth" type="date"
															name="date" oninput="checkValues()">
													</div>

													<div class="form-group">
														<label>전화번호</label> <input type="text" id="register-tel"
															class="form-control" data-toggle="input-mask"
															data-mask-format="000-0000-0000" maxlength="13">
														<span class="font-13 text-muted" oninput="checkValues()">e.g
															"01x-xxxx-xxxx"</span>
													</div>

													<div class="form-group mb-3">
														<label for="simpleinput">주소</label> <input type="text"
															id="register-addr" class="form-control"
															oninput="checkValues()"> <span
															class="font-13 text-muted">상세하게 주소를 적지 않으셔도 괜찮습니다.</span>
													</div>

													<script>
														function checkValues() {
															var input_name = $('#register-name').val();
															var input_relationship = $('#register-relationship').val();
															var input_birth = $('#register-birth').val();
															var input_addr = $('#register-addr').val();
															var input_tel = $('#register-tel').val();
																			
															if(input_name != "" && input_relationship != "" && input_birth != "" && input_addr != "" && input_tel != "") {
																$('#register-btn').attr('disabled', false);
															} else {
																$('#register-btn').attr('disabled', true);
															}
														}
													</script>

												</form>
											</div>
											<div class="modal-footer">
												<button type="button" id="register-btn"
													class="btn btn-primary" onclick="registerFamilyInfo()"
													disabled>등록하기</button>
												<button type="button" id="register-cancel-btn"
													class="btn btn-light" data-dismiss="modal">취소</button>
											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>
								<!-- Modal content end -->

							</div>
							<!-- end card body-->
						</div>
						<!-- end card -->
					</div>
				</div>
				<!-- end row-->

				<!-- container -->

			</div>
			<!-- content -->

			<!-- Footer Start -->
			<footer class="footer">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-6">2018 - 2019 © Hyper - Coderthemes.com
						</div>
					</div>
				</div>
			</footer>
			<!-- end Footer -->

		</div>

		<!-- ============================================================== -->
		<!-- End Page content -->
		<!-- ============================================================== -->


	</div>
	<!-- END wrapper -->


	<!-- Right Sidebar -->
	<div class="right-bar">

		<div class="rightbar-title">
			<a href="javascript:void(0);" class="right-bar-toggle float-right">
				<i class="dripicons-cross noti-icon"></i>
			</a>
			<h5 class="m-0">Settings</h5>
		</div>

		<div class="slimscroll-menu rightbar-content">

			<!-- Settings -->
			<hr class="mt-0" />
			<h5 class="pl-3">Basic Settings</h5>
			<hr class="mb-0" />

			<div class="p-3">
				<div class="custom-control custom-checkbox mb-2">
					<input type="checkbox" class="custom-control-input"
						id="notifications-check" checked> <label
						class="custom-control-label" for="notifications-check">Notifications</label>
				</div>

				<div class="custom-control custom-checkbox mb-2">
					<input type="checkbox" class="custom-control-input"
						id="api-access-check"> <label class="custom-control-label"
						for="api-access-check">API Access</label>
				</div>

				<div class="custom-control custom-checkbox mb-2">
					<input type="checkbox" class="custom-control-input"
						id="auto-updates-check" checked> <label
						class="custom-control-label" for="auto-updates-check">Auto
						Updates</label>
				</div>

				<div class="custom-control custom-checkbox mb-2">
					<input type="checkbox" class="custom-control-input"
						id="online-status-check" checked> <label
						class="custom-control-label" for="online-status-check">Online
						Status</label>
				</div>

				<div class="custom-control custom-checkbox mb-2">
					<input type="checkbox" class="custom-control-input"
						id="auto-payout-check"> <label
						class="custom-control-label" for="auto-payout-check">Auto
						Payout</label>
				</div>

			</div>


			<!-- Timeline -->
			<hr class="mt-0" />
			<h5 class="pl-3">Recent Activity</h5>
			<hr class="mb-0" />
			<div class="pl-2 pr-2">
				<div class="timeline-alt">
					<div class="timeline-item">
						<i class="mdi mdi-upload bg-info-lighten text-info timeline-icon"></i>
						<div class="timeline-item-info">
							<a href="#" class="text-info font-weight-bold mb-1 d-block">You
								sold an item</a> <small>Paul Burgess just purchased “Hyper -
								Admin Dashboard”!</small>
							<p class="mb-0 pb-2">
								<small class="text-muted">5 minutes ago</small>
							</p>
						</div>
					</div>

					<div class="timeline-item">
						<i
							class="mdi mdi-airplane bg-primary-lighten text-primary timeline-icon"></i>
						<div class="timeline-item-info">
							<a href="#" class="text-primary font-weight-bold mb-1 d-block">Product
								on the Bootstrap Market</a> <small>Dave Gamache added <span
								class="font-weight-bold">Admin Dashboard</span>
							</small>
							<p class="mb-0 pb-2">
								<small class="text-muted">30 minutes ago</small>
							</p>
						</div>
					</div>

					<div class="timeline-item">
						<i
							class="mdi mdi-microphone bg-info-lighten text-info timeline-icon"></i>
						<div class="timeline-item-info">
							<a href="#" class="text-info font-weight-bold mb-1 d-block">Robert
								Delaney</a> <small>Send you message <span
								class="font-weight-bold">"Are you there?"</span>
							</small>
							<p class="mb-0 pb-2">
								<small class="text-muted">2 hours ago</small>
							</p>
						</div>
					</div>

					<div class="timeline-item">
						<i
							class="mdi mdi-upload bg-primary-lighten text-primary timeline-icon"></i>
						<div class="timeline-item-info">
							<a href="#" class="text-primary font-weight-bold mb-1 d-block">Audrey
								Tobey</a> <small>Uploaded a photo <span
								class="font-weight-bold">"Error.jpg"</span>
							</small>
							<p class="mb-0 pb-2">
								<small class="text-muted">14 hours ago</small>
							</p>
						</div>
					</div>

					<div class="timeline-item">
						<i class="mdi mdi-upload bg-info-lighten text-info timeline-icon"></i>
						<div class="timeline-item-info">
							<a href="#" class="text-info font-weight-bold mb-1 d-block">You
								sold an item</a> <small>Paul Burgess just purchased “Hyper -
								Admin Dashboard”!</small>
							<p class="mb-0 pb-2">
								<small class="text-muted">1 day ago</small>
							</p>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>


	<div class="rightbar-overlay"></div>
	<!-- /Right-bar -->

	<!-- App js -->
	<script src="resources/js/app.min.js"></script>

	<!-- third party js -->
	<script src="resources/js/jquery-ui.min.js"></script>
	<script src="resources/js/fullcalendar.min.js"></script>
	<!-- thrid party js ends -->

	<!-- demo app -->
	<script src="resources/js/demo.form-wizard.js"></script>
	<script src="resources/js/demo.calendar.js"></script>
	<!-- end demo app -->

</body>
</html>
