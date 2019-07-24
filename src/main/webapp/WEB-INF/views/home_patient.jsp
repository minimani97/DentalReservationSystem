<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>JJONDDuk_Patient</title>
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
			
			//$('#logout-btn').show();
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
						class="side-nav-link active"> <i class="dripicons-home"></i><span>
								홈 </span></span>
					</a></li>

					<li class="side-nav-item"><a href="http://localhost:8090/reservation/reservation"
						class="side-nav-link"> <i class="dripicons-calendar"></i><span>
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
                        
                        <!-- start page title -->
                        <div class="row">
                            <div class="col-12">
                                <div class="page-title-box">
                                    <h4 class="page-title">홈</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        <div class="row justify-content-center">
                            <div class="col-xl-10">

                                <!-- Pricing Title-->
                                <div class="text-center">
                                    <h3 class="mb-2">${sessionScope.p_name}님, 환영합니다.</h3>
                                    <p class="text-muted w-50 m-auto">
                                        원하는 메뉴를 선택하여 이용해주십시오.
                                    </p>
                                </div>

                                <!-- Plans -->
                                <div class="row mt-sm-5 mt-3 mb-3">
                                    <div class="col-md-6">
                                        <div class="card card-pricing">
                                            <div class="card-body text-center">
                                                <h4 class="card-pricing-plan-name font-weight-bold text-uppercase">치과 진료 예약하기</h4>
                                                <i class="card-pricing-icon dripicons-calendar text-primary"></i>
                                                <!-- <h2 class="card-pricing-price">$19 <span>/ Month</span></h2> -->
                                                <ul class="card-pricing-features">
                                                	<li>치과를 알아보고 직접 전화로 예약하는 것이 아닌<br>온라인으로 쉽고 간편하게 예약 가능합니다!</li>
                                                    <li>내가 원하는 치과 및 의료진에게 치료받을 수 있도록<br>직접 보고 선택하여 쉽게 예약 가능합니다!</li>
                                                </ul>
                                                <button class="btn btn-primary mt-4 mb-2 btn-rounded" onclick="location.href='http://localhost:8090/reservation/reservation'">예약하기</button>
                                            </div>
                                        </div> <!-- end Pricing_card -->
                                    </div> <!-- end col -->

                                    <div class="col-md-6">
                                        <div class="card card-pricing">
                                            <div class="card-body text-center">
                                                <h4 class="card-pricing-plan-name font-weight-bold text-uppercase">마이페이지</h4>
                                                <i class="card-pricing-icon dripicons-user text-primary"></i>
                                                <!-- <h2 class="card-pricing-price">$29 <span>/ Month</span></h2> -->
                                                <ul class="card-pricing-features">
                                                    <li>새로운 계정을 만들지 않아도 가족 등록 하나로<br>내 가족의 예약을 쉽게 진행할 수 있습니다!</li>
                                                    <li>한 눈에 내 예약 내역을 확인하고 예약 취소할 수<br>있습니다!</li>
                                                </ul>
                                                <button class="btn btn-primary mt-4 mb-2 btn-rounded" onclick="location.href='http://localhost:8090/reservation/mypage'">이동하기</button>
                                            </div>
                                        </div> <!-- end Pricing_card -->
                                    </div> <!-- end col -->

                                </div>
                                <!-- end row -->

                            </div> <!-- end col-->
                        </div>
                        <!-- end row -->
                        
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
