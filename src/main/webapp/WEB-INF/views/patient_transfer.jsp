<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>JJONDDuk_Dentist  :></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="assets/images/favicon.ico">

		<!-- third party css -->
        <link href="resources/css/fullcalendar.min.css" rel="stylesheet" type="text/css" />
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
				if("${sessionScope.d_code}" == "") {
					alert("로그인이 필요합니다.");
					window.location.href='http://localhost:8090/reservation/';
				} else {
					var c_code = "${sessionScope.c_code}";
					var d_code = "${sessionScope.d_code}";
					
					$("#c_code").val(c_code);
					$("#d_code").val(d_code);
					
					//$('#logout-btn').show();
				}
			}
		</script>

		<!-- 민정js -->
		<script src="resources/js/dentist.js"></script>
		
		<script>
			$(document).ready(function() {
				makePatientOption();
			});
		</script>
		
		
    </head>

    <body>

        <!-- Begin page -->
        <div class="wrapper">
        	<form>
        		<input type="hidden" id="c_code" value="">
    			<input type="hidden" id="d_code" value="">
        		<input type="hidden" id="selected_Patient" value="">
        		<input type="hidden" id="selectedDate" value="">
        		<input type="hidden" id="selectedStartTime" value="">
        		<input type="hidden" id="selectedEndTime" value="">
        		<input type="hidden" id="selectedField" value="">
        	</form>

            <!-- ========== Left Sidebar Start ========== -->
            <div class="left-side-menu">

                <div class="slimscroll-menu" id="left-side-menu-container">

                    <!-- LOGO -->
                    <a href="index.html" class="logo text-center">
                        <span class="logo-lg">
                            <img src="resources/image/logo.png" alt="" height="40">
                        </span>
                        <span class="logo-sm">
                            <img src="assets/images/logo_sm.png" alt="" height="16">
                        </span>
                    </a>

                    <!--- Sidemenu -->
                    <ul class="metismenu side-nav">

                        <li class="side-nav-title side-nav-item">Menu</li>

                        <li class="side-nav-item active">
                            <a href="javascript: void(0);" class="side-nav-link" >
                                <i class="dripicons-view-list"></i>
                                <span> 환자 관리 </span>
                            </a>
                            <ul class="side-nav-second-level" aria-expanded="false">
                                <li>
                                    <a href="http://localhost:8090/reservation/schedule_check">스케줄 확인</a>
                                </li>
                                <li>
                                    <a href="http://localhost:8090/reservation/patient_list">환자 검색</a>
                                </li>
                            </ul>
                        </li>
                        <li class="side-nav-item active">
                            <a href="javascript: void(0);" class="side-nav-link" >
                                <i class="dripicons-user"></i>
                                <span> 의료진 </span>
                            </a>
                            <ul class="side-nav-second-level" aria-expanded="false">
                            	<li>
                                    <a href="http://localhost:8090/reservation/close_notice">휴진 등록</a>
                                </li>
                                <li>
                                    <a href="http://localhost:8090/reservation/dentist_list">의료진 목록</a>
                                </li>
                            </ul>
                        </li>
                        <li class="side-nav-item active">
                            <a href="javascript: void(0);" class="side-nav-link" >
                                <i class="dripicons-swap"></i>
                                <span> 환자 트랜스퍼 </span>
                            </a>
                            <ul class="side-nav-second-level" aria-expanded="false">
                                <li>
                                    <a href="http://localhost:8090/reservation/patient_transfer">환자 트랜스퍼</a>
                                </li>
                                <li>
                                    <a href="http://localhost:8090/reservation/receiveTransfer">받은 트랜스퍼</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <!-- End Sidebar -->

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
									class="font-weight-bold">  ${sessionScope.d_name} 님 </span> <span
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
                    </div>
                    <!-- end Topbar -->

                    <!-- Start Content-->
                    <div class="container-fluid">
                        
                        <!-- start page title -->
                        <div class="row">
                            <div class="col-12">
                                <div class="page-title-box">
                                    <h4 class="page-title">환자 트랜스퍼</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 
                        
                        <div class="row">
                            
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                    <h4>환자 정보</h4>
                                    <br>
                                    
                                    <div class="row">
                                    <div class="col-md-6">
                                    	<label for="PatientSelect">환자 선택</label>
                                        <select class="form-control select2 select2-hidden-accessible" id="PatientSelect" data-toggle="select2" data-select2-id="5" tabindex="-1" aria-hidden="true" onchange="getPatientInformation(),makeReservationOption(); ;">
                                        <option data-select2-id="3" id="123" >Select</option>
                                            <!-- <script>
                                            makePatientOption();
                                            </script> -->
                                        </select><span class="select2 select2-container select2-container--default" dir="ltr" data-select2-id="6" style="width: 327.4px;">
                                        <span class="selection"></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
                                    </div>
                                    </div>	
                                    
                                    	<div class="row">
                                    	<div class="col-md-6">
											<br>
											<div class="form-group position-relative mb-3">
                                                <label for="validationTooltip01">이름</label>
                                                <input type="text" class="form-control" id="validationTooltip01" placeholder="이름" value="" required readonly="readonly">
                                            </div>
                                            <div class="form-group position-relative mb-3">
                                                <label for="validationTooltip02">전화번호</label>
                                                <input type="text" class="form-control" id="validationTooltip03" placeholder="전화번호" value="" required readonly="readonly">
                                            </div>
                                    	</div>
                                    	
                                    	<div class="col-md-6">
                                    		<br>
                                    		<div class="form-group position-relative mb-3">
                                                <label for="validationTooltip02">생년월일</label>
                                                <input type="text" class="form-control" id="validationTooltip02" placeholder="생년월일" value="" required readonly="readonly">
                                            </div>
                                    	</div>
                                    	
                                    	</div>
                                    	
                                    </div>
                                    
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <form class="needs-validation" novalidate>

   										<div class="row">
											<div class="col-md-6">                           
	
                                                <label for="ClinicSelect">병원 선택</label>
                                                <select class="form-control select2 select2-hidden-accessible" id="ClinicSelect" data-toggle="select2" data-select2-id="1" tabindex="-1" aria-hidden="true">
                                                    <option data-select2-id="3" >Select</option>
                                                    <optgroup label="천안">
                                                    <script>
                                                    makeOption();
                                                    </script>
                                                    </optgroup>
                                                </select><span class="select2 select2-container select2-container--default" dir="ltr" data-select2-id="2" style="width: 327.4px;"><span class="selection"></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
           										
           										
           										<button type="button" id="2check" class="btn btn-primary btn-rounded" onclick="javascript:makeDentistOption()">의료진 검색</button>
           										
           										<br><br><br>
           										
											</div>
										<div class="col-md-6">
			
											<label for="ClinicSelect">진료과</label>
                                                <select class="form-control select2 select2-hidden-accessible" id="FieldSelect" data-toggle="select2" data-select2-id="2" tabindex="-1" aria-hidden="true">
                                                    <option data-select2-id="3" >Select</option>
                                                    <option value="임플란트">임플란트</option>
                                                    <option value="틀니">틀니</option>
                                                    <option value="치아 성형">치아 성형</option>
                                                    <option value="턱관절 치료">턱관절 치료</option>
                                                    <option value="교정">교정</option>
                                                    <option value="치아 미백">치아 미백</option>
                                                    <option value="보철치료">보철치료</option>
                                                    <option value="신경치료">신경치료</option>
                                                    <option value="치주치료">치주치료</option>
                                                    <option value="사랑니 발치">사랑니 발치</option>
                                                    <option value="스케일링">스케일링</option>
                                                    <option value="검진">검진</option>
                                                </select><span class="select2 select2-container select2-container--default" dir="ltr" data-select2-id="2" style="width: 327.4px;"><span class="selection"></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
                                           
											</div>
										</div> <!-- row -->

										<div class="row">

											<div class="col-md-6">
												<label for="DentistSelect">의료진 선택</label>
                                                <select class="form-control select2 select2-hidden-accessible" id="DentistSelect" data-toggle="select2" data-select2-id="3" tabindex="-1" aria-hidden="true">
                                                    <option data-select2-id="3" id="123" >Select</option>
                                                    
                                                </select><span class="select2 select2-container select2-container--default" dir="ltr" data-select2-id="2" style="width: 327.4px;">
                                                <span class="selection"></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
											</div>

											<div class="col-md-6">

												<label for="PatientSelect">진료 선택</label>
                                                <select class="form-control select2 select2-hidden-accessible" id="ReservationSelect" data-toggle="select2" data-select2-id="4" tabindex="-1" aria-hidden="true">
                                                    <!-- <option data-select2-id="3" id="123" >Select</option> -->
                                                    <script>
                                                    //makeReservationOption();
                                                    </script>
                                                </select><span class="select2 select2-container select2-container--default" dir="ltr" data-select2-id="2" style="width: 327.4px;">
                                                <span class="selection"></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
											</div>

										</div><br>
										<div class="row">
											<div class="col-lg-3">
												<label for="datepicker-out">날짜 선택</label>
												<div id="datepicker-out" data-provide="datepicker-inline" style="margin-top:20px"></div>
											</div>	
											<div id="schedule-area" class="col-lg-9"></div>	
										</div>
											<br><br>
											<div class="row">
												<button class="btn btn-primary" type="button" onclick="transferSendButton()">전송</button>
											</div>

                                        
                                        </form>
                                    </div> <!-- end card-body-->
                                </div> <!-- end card-->
                            </div> <!-- end col-->
                            
                        </div>
                        <!-- end row -->
                        
                        
                        
                    </div> <!-- container -->

                </div> <!-- content -->

                <!-- Footer Start -->
                <footer class="footer">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6">
                                2018 - 2019 © Hyper - Coderthemes.com
                            </div>
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
                        <input type="checkbox" class="custom-control-input" id="notifications-check" checked>
                        <label class="custom-control-label" for="notifications-check">Notifications</label>
                    </div>

                    <div class="custom-control custom-checkbox mb-2">
                        <input type="checkbox" class="custom-control-input" id="api-access-check">
                        <label class="custom-control-label" for="api-access-check">API Access</label>
                    </div>

                    <div class="custom-control custom-checkbox mb-2">
                        <input type="checkbox" class="custom-control-input" id="auto-updates-check" checked>
                        <label class="custom-control-label" for="auto-updates-check">Auto Updates</label>
                    </div>

                    <div class="custom-control custom-checkbox mb-2">
                        <input type="checkbox" class="custom-control-input" id="online-status-check" checked>
                        <label class="custom-control-label" for="online-status-check">Online Status</label>
                    </div>

                    <div class="custom-control custom-checkbox mb-2">
                        <input type="checkbox" class="custom-control-input" id="auto-payout-check">
                        <label class="custom-control-label" for="auto-payout-check">Auto Payout</label>
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
                                <a href="#" class="text-info font-weight-bold mb-1 d-block">You sold an item</a>
                                <small>Paul Burgess just purchased “Hyper - Admin Dashboard”!</small>
                                <p class="mb-0 pb-2">
                                    <small class="text-muted">5 minutes ago</small>
                                </p>
                            </div>
                        </div>

                        <div class="timeline-item">
                            <i class="mdi mdi-airplane bg-primary-lighten text-primary timeline-icon"></i>
                            <div class="timeline-item-info">
                                <a href="#" class="text-primary font-weight-bold mb-1 d-block">Product on the Bootstrap Market</a>
                                <small>Dave Gamache added
                                    <span class="font-weight-bold">Admin Dashboard</span>
                                </small>
                                <p class="mb-0 pb-2">
                                    <small class="text-muted">30 minutes ago</small>
                                </p>
                            </div>
                        </div>

                        <div class="timeline-item">
                            <i class="mdi mdi-microphone bg-info-lighten text-info timeline-icon"></i>
                            <div class="timeline-item-info">
                                <a href="#" class="text-info font-weight-bold mb-1 d-block">Robert Delaney</a>
                                <small>Send you message
                                    <span class="font-weight-bold">"Are you there?"</span>
                                </small>
                                <p class="mb-0 pb-2">
                                    <small class="text-muted">2 hours ago</small>
                                </p>
                            </div>
                        </div>

                        <div class="timeline-item">
                            <i class="mdi mdi-upload bg-primary-lighten text-primary timeline-icon"></i>
                            <div class="timeline-item-info">
                                <a href="#" class="text-primary font-weight-bold mb-1 d-block">Audrey Tobey</a>
                                <small>Uploaded a photo
                                    <span class="font-weight-bold">"Error.jpg"</span>
                                </small>
                                <p class="mb-0 pb-2">
                                    <small class="text-muted">14 hours ago</small>
                                </p>
                            </div>
                        </div>

                        <div class="timeline-item">
                            <i class="mdi mdi-upload bg-info-lighten text-info timeline-icon"></i>
                            <div class="timeline-item-info">
                                <a href="#" class="text-info font-weight-bold mb-1 d-block">You sold an item</a>
                                <small>Paul Burgess just purchased “Hyper - Admin Dashboard”!</small>
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
        
        <!-- Typehead -->
        <script src="resources/js/handlebars.min.js"></script>
        <script src="resources/js/typeahead.bundle.min.js"></script>

	<!-- Demo -->
	<script src="resources/js/demo.typehead.js"></script>
	
		<!-- third party js -->
        <script src="resources/js/jquery-ui.min.js"></script>
        <script src="resources/js/fullcalendar.min.js"></script>
        <!-- third party js ends -->

        <!-- demo app -->
        <script src="resources/js/demo.calendar.js"></script>
        <!-- end demo js-->

    </body>
</html>
