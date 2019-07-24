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
		setPatientInfo();
	});
	
	function setPatientInfo() {
		if("${sessionScope.p_code}" == "") {
			alert("로그인이 필요합니다.");
			window.location.href='http://localhost:8090/reservation/';
		} else {
			var p_code = "${sessionScope.p_code}";
			$("#user_code").val(p_code);
			
			$('#logout-btn').show();
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
							<li><a href="http://localhost:8090/reservation/mypage">정보
									조회 / 수정</a></li>
							<li><a
								href="http://localhost:8090/reservation/reservationList"
								area-expanded="true">예약내역 확인</a></li>
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
					
					<!-- <button id="logout-btn" class="btn btn-primary mt-2 mb-2 float-right" onclick="logout()"  style="display:none">로그아웃</button> -->
				</div>
				<!-- end Topbar -->

				<!-- Start Content-->
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="page-title-box">
								<h4 class="page-title">예약내역 확인</h4>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-12">
							<div class="card">
								<div class="card-body">

									<h4 class="header-title">
										예약자 이름을 선택해주세요 : <select id="family-list"
											onchange="loadReservationList(this.value)"><option></option></select>
									</h4>
									<p class="text-muted font-14 mb-4"></p>

									<div class="table-responsive-sm">
										<table class="table table-centered mb-0">
											<thead>
												<tr>
													<th></th>
													<th>예약일시</th>
													<th>치과명</th>
													<th>진료과목명</th>
													<th></th>
												</tr>
											</thead>
											<tbody id="reservation-list-tbody">
											</tbody>
										</table>
									</div>
									<!-- end table-responsive-->

									<!-- Modal content -->
									<div id="myModal" class="modal fade" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										style="display: none;" aria-hidden="true">
										<div class="modal-dialog modal-full-width">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="myModalLabel">예약 정보</h4><br>
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">×</button>
												</div>
												<div class="modal-body">


													<div class="card-body">
														<div class="row">
															<div class="col-lg-6">
																<form>
																	<div class="form-group mb-3">
																		<label for="example-disable">치과</label> <input
																			type="text" class="form-control" id="check_clinic"
																			disabled="" value="">
																	</div>
																	<div class="form-group mb-3">
																		<label for="example-disable">진료과목</label> <input
																			type="text" class="form-control" id="check_field"
																			disabled="" value="">
																	</div>
																	<div class="form-group mb-3">
																		<label for="example-disable">의료진</label> <input
																			type="text" class="form-control" id="check_dentist"
																			disabled="" value="">
																	</div>
																	<div class="form-group mb-3">
																		<label for="example-disable">예약일시</label> <input
																			type="text" class="form-control" id="check_dateTime"
																			disabled="" value="">
																	</div>
																</form>
															</div>
															<!-- end col -->

															<div class="col-lg-6">
																<form>
																	<div class="form-group mb-3">
																		<label for="example-disable">특이사항</label>
																		<textarea disabled="" class="form-control"
																			id="check_additionalInfo" rows="4"></textarea>
																	</div>
																	<div class="form-group mb-3">
																		<label for="example-disable">개인정보 제공 동의 여부</label> <input
																			type="text" class="form-control"
																			id="check_info_concent" disabled="" value="">
																	</div>
																	<p class="text-muted" style="text-align:right">※ 예약일 하루 전까지만 예약 취소가 가능합니다.</p>
																</form>
																<br>
																<button type="button" id="cancel-reservation-btn" class="btn btn-primary" style="float:right" onclick="" disabled>예약취소</button>
															</div>
															<!-- end col -->
														</div>
														<!-- end row-->

													</div>

													<!-- <label>※ 예약일 하루 전까지만 예약 취소가 가능합니다.<br>
												<br></label> -->
												</div>
												<!-- <div class="modal-footer">
													<button type="button" id="register-cancel-btn"
														class="btn btn-light" data-dismiss="modal">취소</button>
												</div> -->
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
						<!-- end col-->
					</div>

				</div>
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

	<script>
		
	</script>

</body>
</html>
