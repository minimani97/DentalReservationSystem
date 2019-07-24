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

<script src="resources/js/home_map.js"></script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=214630e88c9f141416bd0733325ee211&libraries=services"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

</head>

<script>
	$(document).ready(function() {			
		setPatientInfo();
	});
	
	function setPatientInfo() {
		if("${sessionScope.p_code}" == "") {
			$('#login-btn').show();
			$('#logout-btn').hide();
		} else {
			var p_code = "${sessionScope.p_code}";
			$("#user_code").val(p_code);
		}

		console.log("세션값이당: " + p_code);
	}
</script>

<body>	
	<!-- Begin page -->
	<div class="wrapper">
	
		<!-- res_hidden values -->
			<form>
				<input type="hidden" id="selectedClinic" value="1">
				<input type="hidden" id="selectedField" value="">
				<input type="hidden" id="selectedDentist" value="">
				<input type="hidden" id="selectedDate" value="">
				<input type="hidden" id="selectedStartTime" value="">
				<input type="hidden" id="selectedEndTime" value="">
			</form>
		<!-- res_hidden values ends -->

		<!-- ========== Left Sidebar Start ========== -->
		<div class="left-side-menu">

			<div class="slimscroll-menu" id="left-side-menu-container">

				<!-- LOGO -->
				<a href="http://localhost:8090/reservation/" class="logo text-center"> <span
					class="logo-lg"> <img src="resources/image/logo.png" alt=""
						height="40">
				</span> <span class="logo-sm"> <img src="assets/images/logo_sm.png"
						alt="" height="16">
				</span>
				</a>

				<!--- Sidemenu -->
				<ul class="metismenu side-nav">

					<li class="side-nav-title side-nav-item">메뉴</li>

					<li class="side-nav-item"><a href="http://localhost:8090/reservation"
						class="side-nav-link active"> <i class="dripicons-home"></i><span>
								홈 </span></span>
					</a></li>

					<li class="side-nav-item"><a href="http://localhost:8090/reservation/clinic_register"
						class="side-nav-link"> <i class="mdi mdi-hospital-building"></i><span>
								병원 등록 </span></span>
					</a></li>

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
					<button class="button-menu-mobile open-left disable-btn">
						<i class="mdi mdi-menu"></i>
					</button>
					<button id="login-btn" class="btn btn-primary mt-2 mb-2 float-right" onclick="window.location.href='http://localhost:8090/reservation/login'">로그인</button>
					<!-- <button id="logout-btn" class="btn btn-primary mt-2 mb-2 float-right" onclick="" style="display:none">로그아웃</button> -->
				</div>
				<!-- end Topbar -->

				<!-- Start Content-->
				<div class="container-fluid">
					<!-- <img src="resources/image/CaptainAmerica.jpg"
						style="display: block; margin-left: auto; margin-right: auto;" /> -->
						
						<div class="row">
                            <div class="col-12">
                                <div class="page-title-box">
                                    <h4 class="page-title">홈</h4>
                                </div>
                            </div>
                        </div>
						
						<div class="text-center">
                                    <h3 class="mb-2">치과 진료예약, 첫사랑니와 함께하세요!</h3>
                                    <p class="text-muted w-50 m-auto">
                                        의료진과 환자가 모두 편리한 '첫사랑니'입니다^-^*
                                    </p>
                                </div>
						
						<div class="row mt-sm-4 mt-3 mb-3">
                                    <div class="col-md-4">
                                        <div class="card card-pricing">
                                            <div class="card-body text-center">
                                                <h4 class="card-pricing-plan-name font-weight-bold text-uppercase">손쉬운 예약</h4>
                                                <i class="card-pricing-icon dripicons-calendar text-primary"></i>
                                                <!-- <h2 class="card-pricing-price">$19 <span>/ Month</span></h2> -->
                                                <ul class="card-pricing-features">
                                                	<li>치과를 찾아보고 일일히 전화로 예약하지 마세요-<br>온라인으로 쉽고 간편하게 예약 가능합니다!</li>
                                                    <li>내가 원하는 치과 및 의료진에게 치료받을 수 있습니다-<br>병원 예약 현황을 직접 보고 선택하여 쉽게 예약하세요!</li>
                                                </ul>
                                            </div>
                                        </div> <!-- end Pricing_card -->
                                    </div> <!-- end col -->
                                    
                                    <div class="col-md-4">
                                        <div class="card card-pricing">
                                            <div class="card-body text-center">
                                                <h4 class="card-pricing-plan-name font-weight-bold text-uppercase">간편한 예약관리</h4>
                                                <i class="card-pricing-icon dripicons-user text-primary"></i>
                                                <!-- <h2 class="card-pricing-price">$29 <span>/ Month</span></h2> -->
                                                <ul class="card-pricing-features">
                                                    <li>본인이 직접 예약하기 힘든 가족이 있으신가요?<br>내 가족의 예약을 클릭 한 번으로 쉽게 접수 가능합니다!</li>
                                                    <li>예약내역을 한 눈에 확인하고 싶으신가요?<br>예약내역 확인은 물론, 간편하게 예약 취소하세요!</li>
                                                </ul>
                                            </div>
                                        </div> <!-- end Pricing_card -->
                                    </div> <!-- end col -->

                                    <div class="col-md-4">
                                        <div class="card card-pricing">
                                            <div class="card-body text-center">
                                                <h4 class="card-pricing-plan-name font-weight-bold text-uppercase">의료진이 편리한 첫사랑니</h4>
                                                <i class="card-pricing-icon dripicons-user text-primary"></i>
                                                <!-- <h2 class="card-pricing-price">$29 <span>/ Month</span></h2> -->
                                                <ul class="card-pricing-features">
                                                    <li>협력 병원에 클릭 한 번으로 환자 정보를 전송하세요-<br>진료기록을 뽑지 않아 편리합니다!</li>
                                                    <li>오늘 나의 진료 스케줄을 확인하세요-<br>오늘의 스케줄을 한 눈에 확인할 수 있습니다!</li>
                                                </ul>
                                            </div>
                                        </div> <!-- end Pricing_card -->
                                    </div> <!-- end col -->

                                </div>
				</div>
				
				<div class="row">
                            <div class="col-12">
                                <div class="page-title-box">
                                    <h4 class="page-title"> ▶ 병원 검색하기</h4>
                                </div>
                            </div>
                        </div>
				
				<div class="container-fluid">
					<div class="form-group row mb-3">
						<div style="width: 1500px; text-align: center; margin-bottom: 20px">
							<input type="text" id="sample5_address"
								style="width: 400px; margin-bottom: 20px; margin-top: 20px;"
								placeholder="주소 검색 버튼을 눌러 주소를 검색해주세요." disabled> <input
								type="button" class="btn btn-primary"
								onclick="sample5_execDaumPostcode()" value="주소 검색"> <input
								type="button" class="btn btn-primary" onclick="init()"
								value="내 위치에서 가까운 병원 검색">
						</div>
						<div id="map"
							style="width: 1000px; height: 600px; display: block; margin-left: auto; margin-right: auto;">
						</div>
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