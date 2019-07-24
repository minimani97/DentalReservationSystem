<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>JJONDDuk_Reservation</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta
	content="A fully featured admin theme which can be used to build CRM, CMS, etc."
	name="description" />
<meta content="Coderthemes" name="author" />
<!-- App favicon -->
<link rel="shortcut icon" href="assets/images/favicon.ico">

<!-- third party css -->
<link href="resources/css/fullcalendar.min.css" rel="stylesheet" type="text/css">
<!-- third party css end -->

<!-- App css -->
<link href="resources/css/icons.min.css" rel="stylesheet" type="text/css" />
<link href="resources/css/app.min.css" rel="stylesheet" type="text/css" />

<!-- jquery.js -->
<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- jquery.js ends -->

<!-- reservation_logic js -->
<script src="resources/js/reservation_logic.js"></script>
<!-- end reservation_logic js -->

<script src="resources/js/login.js"></script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=214630e88c9f141416bd0733325ee211&libraries=services"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

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
			
			$('#logout-btn').show();
		}

		console.log("세션값이당: " + p_code);
	}
</script>

</head>

<body>	
	<!-- Begin page -->
	<div class="wrapper">
	
		<!-- res_hidden values -->
			<form>
				<input type="hidden" id="user_code" value="">
			
				<input type="hidden" id="selectedClinic" value="">
				<input type="hidden" id="selectedField" value="">
				<input type="hidden" id="selectedDentist" value="">
				<input type="hidden" id="selectedDate" value="">
				<input type="hidden" id="selectedStartTime" value="">
				<input type="hidden" id="selectedEndTime" value="">
				<input type="hidden" id="selectedPatient" value="">
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

					<li class="side-nav-item"><a href="http://localhost:8090/reservation/homeForPatient"
						class="side-nav-link"> <i class="dripicons-home"></i><span>
								홈 </span></span>
					</a></li>

					<li class="side-nav-item"><a href="http://localhost:8090/reservation/reservation"
						class="side-nav-link active"> <i class="dripicons-calendar"></i><span>
								진료예약 </span></span>
					</a></li>
					
					<li class="side-nav-item">
                            <a href="javascript:void(0);" class="side-nav-link">
                                <i class="dripicons-user"></i>
                                <span> 마이페이지 </span>
                                <span class="menu-arrow"></span>
                            </a>
                            <ul class="side-nav-second-level collapse" aria-expanded="false">
                                <li>
                                    <a href="http://localhost:8090/reservation/mypage">정보 조회 / 수정</a>
                                </li>
                                <li>
                                    <a href="http://localhost:8090/reservation/reservationList">예약내역 확인</a>
                                </li>
                            </ul>
                        </li>

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

					<!-- Start Wizard -->
					<div id="basicwizard" style="margin-top: 20px;">
						<ul class="nav nav-pills nav-justified form-wizard-header mb-3">
							<li class="nav-item" data-target-form="#cardForm"><a
								href="#first" data-toggle="tab"
								class="wizard-item nav-link rounded-0 pt-2 pb-2 active"> <i
									class="mdi mdi-hospital-building mr-1"></i> <span
									class="d-none d-sm-inline">치과선택</span>
							</a></li>
							<li class="nav-item" data-target-form="#profileForm"><a
								href="#second" data-toggle="tab"
								class="wizard-item nav-link rounded-0 pt-2 pb-2"><i
									class="dripicons-medical mr-1"></i> <span
									class="d-none d-sm-inline">진료과목</span> </a></li>
							<li class="nav-item" data-target-form="#profileForm"><a
								href="#third" data-toggle="tab"
								class="wizard-item nav-link rounded-0 pt-2 pb-2"> <i
									class="mdi mdi-doctor mr-1"></i> <span
									class="d-none d-sm-inline">의료진</span>
							</a></li>
							<li class="nav-item" data-target-form="#profileForm"><a
								href="#fourth" data-toggle="tab"
								class="wizard-item nav-link rounded-0 pt-2 pb-2"> <i
									class="mdi mdi-calendar-clock mr-1"></i> <span
									class="d-none d-sm-inline">예약일시</span>
							</a></li>
							<li class="nav-item" data-target-form="#profileForm"><a
								href="#fifth" data-toggle="tab"
								class="wizard-item nav-link rounded-0 pt-2 pb-2"> <i
									class="mdi mdi-face-profile mr-1"></i> <span
									class="d-none d-sm-inline">예약자정보</span>
							</a></li>
							<li class="nav-item" data-target-form="#otherForm"><a
								href="#sixth" data-toggle="tab"
								class="wizard-item nav-link rounded-0 pt-2 pb-2"> <i
									class="dripicons-checkmark mr-1"></i> <span
									class="d-none d-sm-inline">예약확인</span>
							</a></li>
							<li class="nav-item" data-target-form="#otherForm"><a
								href="#seventh" data-toggle="tab"
								class="wizard-item nav-link rounded-0 pt-2 pb-2"> <i
									class="mdi mdi-checkbox-marked-circle-outline mr-1"></i> <span
									class="d-none d-sm-inline">완료</span>
							</a></li>
						</ul>

						<div class="tab-content mb-0 b-0">

							<div class="tab-pane active" id="first">
                        <form id="accountForm" method="post" action="#"
                           class="form-horizontal">
                           <div class="row">
                              <div class="col-12">
                                 <div class="form-group row mb-3">
                                    <div style="width: 1500px; text-align: center;">
                                    <input type="text" id="sample5_address"
                                          style="width: 400px; margin-bottom: 20px; margin-top: 20px;"
                                          placeholder="주소 검색 버튼을 눌러 주소를 검색해주세요." disabled>
                                       <input type="button" class="btn btn-primary"
                                          onclick="sample5_execDaumPostcode()" value="주소 검색">
                                       <input type="button" class="btn btn-primary" onclick="init()" value="내 위치에서 가까운 병원 검색">
                                    </div>
                                    <div id="map" style="width: 1500px; height: 600px; text-align: center; margin-top: 20px;">
                                    </div>
                                 </div>
                              </div>
                           </div>
                        </form>
                     </div>

							<div class="tab-pane fade" id="second">
								<div id="fieldInfo" class="row">
									<!-- <script type="text/javascript">
										loadFieldInfo();
									</script> -->
									
								</div>
							</div>

							<div class="tab-pane fade" id="third">
								<div id="dentistInfo" class="row"></div>
							</div>

							<div class="tab-pane fade" id="fourth">
								<div class="card">
									<div class="card-body">
										<label> ※ 예약을 원하시는 일자를 선택하면 예약 가능한 시간을 알 수 있습니다.</label><br>
										<label> ※ 예약을 원하시는 시간을 선택한 후 다음 버튼을 눌러주세요.</label>
										<div class="row">
											<div class="col-lg-3">
												<div id="datepicker-out" data-provide="datepicker-inline" style="margin-top:20px">
												</div>
											</div>
											<div id="schedule-area" class="col-lg-9">
												
											</div>
										</div>
									</div>
								</div>
								<!-- Add New Event MODAL -->
								<div class="modal fade" id="event-modal" tabindex="-1" style="display: none;" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header pr-4 pl-4 border-bottom-0 d-block">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                <h4 class="modal-title">Add New Event</h4>
                                            </div>
                                            <div class="modal-body pt-3 pr-4 pl-4"><form><div class="row"><div class="col-12"><div class="form-group"><label class="control-label">Event Name</label><input class="form-control" placeholder="Insert Event Name" type="text" name="title"></div></div><div class="col-12"><div class="form-group"><label class="control-label">Category</label><select class="form-control" name="category"><option value="bg-danger">Danger</option><option value="bg-success">Success</option><option value="bg-primary">Primary</option><option value="bg-info">Info</option><option value="bg-dark">Dark</option><option value="bg-warning">Warning</option></select></div></div></div></form></div>
                                            <div class="text-right pb-4 pr-4">
                                                <button type="button" class="btn btn-light " data-dismiss="modal">Close</button>
                                                <button type="button" class="btn btn-success save-event  " style="">Create event</button>
                                                <button type="button" class="btn btn-danger delete-event  " data-dismiss="modal" style="display: none;">Delete</button>
                                            </div>
                                        </div> <!-- end modal-content-->
                                    </div> <!-- end modal dialog-->
                                </div>
								<!-- end MODAL -->								
							</div>

							<div class="tab-pane fade" id="fifth">
								<div class="card">
									<div class="card-body">
										<h2 class="header-title">예약 정보 입력</h2>
										<p class="text-muted">예약하시는 분의 이름을 선택하고, 추가 정보를 입력해주세요.</p>
										<p style="margin-bottom:20px;">예약자를 선택하세요 : <select id="family-list" onchange="getPatientInfo(this.value)"><option></option></select><br><p>

										<div class="row">
											<div class="col-lg-6">
												<form>
													<div class="form-group mb-3">
														<label for="simpleinput">이름</label>
														<span class="text-danger">*</span> 
														<input type="text" id="name" class="form-control" disabled>
													</div>

													<div class="form-group mb-3">
														<label for="example-date">생년월일</label>
														<span class="text-danger">*</span>
														<input class="form-control" id="birth" type="date" name="date" disabled>
													</div>

													<div class="form-group">
														<label>전화번호</label><span class="text-danger">*</span>
														<input type="text" id="phone_num" class="form-control"
															data-toggle="input-mask" data-mask-format="000-0000-0000"
															maxlength="13" disabled>
															<!-- <span class="font-13 text-muted">e.g "01x-xxxx-xxxx"</span> -->
													</div>

													<div class="form-group mb-3">
														<label for="simpleinput">주소</label>
														<span class="text-danger">*</span>
														<input type="text" id="address" class="form-control" disabled>
														<!-- <span class="font-13 text-muted">상세하게 주소를 적지 않으셔도 괜찮습니다.</span> -->
													</div>
												</form>
											</div>
											<!-- end col -->

											<div class="col-lg-6">
												<form>
													<div class="form-group mb-3">
														<label for="example-textarea">특이사항</label>
														<textarea class="form-control" id="additional-info" rows="7"></textarea>
													</div>

													<div class="custom-control custom-checkbox" style="margin-left:10px">
														<input type="checkbox" class="custom-control-input"
															id="customCheck1"> <label
															class="custom-control-label" for="customCheck1">협력 치과로의 개인정보 제공에 동의합니다.</label>
													</div>

													<br>
													<br>
													<!-- <button type="submit" class="btn btn-primary"
														style="float: right; margin-right: 20px">예약하기</button> -->
												</form>
											</div>
											<!-- end col -->
										</div>
										<!-- end row-->

									</div>
								</div>
							</div>

							<div class="tab-pane fade" id="sixth">
								<div class="card">
									<div class="card-body">
										<h2 class="header-title">예약 정보 확인</h2>
										<p class="text-muted">예약 정보를 확인하시고 잘못된 부분은 이전으로 돌아가서 다시 선택해주세요.</p>

										<div class="row">
											<div class="col-lg-6">
												<form>
													<div class="form-group mb-3">
                                                        <label for="example-disable">치과</label>
                                                        <input type="text" class="form-control" id="check_clinic" disabled="" value="">
                                                    </div>
                                                    <div class="form-group mb-3">
                                                        <label for="example-disable">진료과목</label>
                                                        <input type="text" class="form-control" id="check_field" disabled="" value="">
                                                    </div>
                                                    <div class="form-group mb-3">
                                                        <label for="example-disable">의료진</label>
                                                        <input type="text" class="form-control" id="check_dentist" disabled="" value="">
                                                    </div>
                                                    <div class="form-group mb-3">
                                                        <label for="example-disable">예약일시</label>
                                                        <input type="text" class="form-control" id="check_dateTime" disabled="" value="">
                                                    </div>
												</form>
											</div>
											<!-- end col -->

											<div class="col-lg-6">
												<form>
													<div class="form-group mb-3">
                                                        <label for="example-disable">이름</label>
                                                        <input type="text" class="form-control" id="check_name" disabled="" value="">
                                                    </div>
                                                    <div class="form-group mb-3">
                                                        <label for="example-disable">생년월일</label>
                                                        <input type="text" class="form-control" id="check_birth" disabled="" value="">
                                                    </div>
                                                    <div class="form-group mb-3">
                                                        <label for="example-disable">전화번호</label>
                                                        <input type="text" class="form-control" id="check_phoneNum" disabled="" value="">
                                                    </div>
                                                    <div class="form-group mb-3">
                                                        <label for="example-disable">주소</label>
                                                        <input type="text" class="form-control" id="check_address" disabled="" value="">
                                                    </div>
                                                    <div class="form-group mb-3">
                                                        <label for="example-disable">특이사항</label>
                                                        <textarea disabled="" class="form-control" id="check_additionalInfo" rows="4"></textarea>
                                                    </div>
                                                    <div class="form-group mb-3">
                                                        <label for="example-disable">개인정보 제공 동의 여부</label>
                                                        <input type="text" class="form-control" id="check_info_concent" disabled="" value="">
                                                    </div>
                                                    <p class="text-muted" style="text-align:right">※ 다음을 누르시면 예약이 완료됩니다.</p>
												</form>
											</div>
											<!-- end col -->
										</div>
										<!-- end row-->

									</div>
								</div>
							</div>
							
							<div class="tab-pane fade" id="seventh">
								<form id="otherForm" method="post" action="#"
									class="form-horizontal"></form>
								<div class="row">
									<div class="col-12">
										<div class="text-center">
											<h2 class="mt-0">
												<i class="mdi mdi-check-all"></i>
											</h2>
											<h3 class="mt-0">예약이 완료되었습니다.</h3>
											<br>

											<p class="w-75 mb-2 mx-auto">온라인상으로는 예약일 하루 전까지만 예약 취소가 가능하고, 예약일 당일에는 온라인이 아닌 유선으로만 예약 취소가 가능합니다.</p>
											<br> 
											<button id="checkResList-btn" class="select-btn-in btn btn-primary"
												style="margin-right: 30px;" onclick="location.href='http://localhost:8090/reservation/reservationList'">예약 내역 확인하기</button>
											<button id="goToHome-btn" class="select-btn-in btn btn-primary"
												style="margin-left: 30px;" onclick="location.href='http://localhost:8090/reservation/homeForPatient'">홈으로 돌아가기</button>
										</div>
									</div>
									<!-- end col -->
								</div>
								<!-- end row -->

							</div>

							<ul id="prev-next-btn-area" class="list-inline wizard mb-0">
								<li class="previous list-inline-item disabled">
								<input type="button" href="#" id="previous-btn" onclick="goToPreviousPage()" class="btn btn-info" value="이전"></li>
								<li class="next list-inline-item float-right">
								<input type="button" href="#" id="next-btn" onclick="goToNextPage()" class="btn btn-info" value="다음" disabled></li>
							</ul>
						</div>
						<!-- tab-content -->
					</div>
					<!-- End Wizard -->

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
