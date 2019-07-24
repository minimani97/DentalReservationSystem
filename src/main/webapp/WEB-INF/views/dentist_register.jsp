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
        
        <!-- jquery.js -->
		<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
		<!-- jquery.js ends -->
        
        <!-- 민정js -->
		<script src="resources/js/dentist.js"></script>
	
        <!-- App css -->
        <link href="resources/css/icons.min.css?after" rel="stylesheet" type="text/css" />
        <link href="resources/css/app.min.css?after" rel="stylesheet" type="text/css" />

    </head>

    <body>
    
    	<form>
    		<input type="hidden" id="c_code" value="1">
    		<input type="hidden" id="d_code" value="1">
    	</form>

        <!-- Begin page -->
        <div class="wrapper">

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
                                    <a href="calender.jsp">스케줄 확인</a>
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
                                    <h4 class="page-title">의료진 등록</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 
                        
                       
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="header-title">정보 등록</h4>
                                        <p class="text-muted font-14">
                                           	 개인정보를 입력해주세요
                                        </p>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <form action="#">
                                                
                                                	<div class="form-group mb-3">
                                                        <label for="example-fileinput">대표 사진</label>
                                                        <input type="file" id="example-fileinput" class="form-control-file">
                                                    </div>
                                                
                                                    <div class="form-group">
                                                        <label>이름</label>
                                                        <input type="text" class="form-control" id="d_name" data-toggle="input-mask" value="김민정">
                                                    </div>
                                                    <div class="form-group">
                                                        <label>전화번호</label>
                                                        <input type="text" class="form-control" id="d_tel" value="01022697010" data-toggle="input-mask" data-mask-format="000-0000-0000" placeholder="010-1234-5678">
                                                    </div>
                                                    <div class="form-group">
                                                        <label>이메일</label>
                                                        <input type="text" class="form-control" id="email" data-toggle="input-mask" value="alswjd7010@gmail.com">
                                                    </div>
                                                    
                                                </form>
                                            </div> <!-- end col -->

                                            <div class="col-md-6">
                                                <form action="#">
                                                	<div class="form-group mb-3">
                                                        <label for="example-select">소속 병원</label><br>
                                                        <select class="form-control" id="c_code">
                                                            <option>스마일치과</option>
                                                            <option>승밍치과</option>
                                                            <option>밍정치과</option>
                                                            <option>에린치과</option>
                                                            <option>응미치과</option>
                                                        </select>
                                                    </div>
                                                	<div class="form-group mb-3">
                                                        <label for="example-select">진료과</label><br>
                                                        	<!-- <form action="dentist.js">
																<input type="checkbox" name="type" id="type" value="1">구강 내과 
																<input type="checkbox" name="type" id="type" value="2">구강병리과<br>
																<input type="checkbox" name="type" id="type" value="3">영상치의학과
																<input type="checkbox" name="type" id="type" value="4">소아치과<br>
																<input type="submit" value="Submit">
															</form> -->
                                                        <select class="form-control" id="d_field">
                                                            <option>임플란트</option>
                                                            <option>틀니</option>
                                                            <option>치아성형</option>
                                                            <option>턱관절 치료</option>
                                                            <option>교정</option>
                                                            <option>치아미백</option>
                                                            <option>보철치료</option>
                                                            <option>신경치료</option>
                                                            <option>치주치료</option>
                                                            <option>사랑니발치</option>
                                                            <option>스케일링</option>
                                                            <option>검진</option>
                                                        </select>
                                                    </div>
                                                    <div class="form-group mb-3">
                                                        <label for="example-textarea">학력</label>
                                                        <textarea class="form-control" class="example-textarea1" id="edu" rows="5" placeholder="●">ㅇㅇㅇ</textarea>
                                                    </div>
                                                    <div class="form-group mb-3">
                                                        <label for="example-textarea">경력</label>
                                                        <textarea class="form-control" class="example-textarea2" id="career" rows="5" placeholder="●">ddddddd</textarea>
                                                    </div>
													<div class="row">
														<input type="button"
															class="btn btn-primary mt-4 mb-2 btn-rounded Dregister" id="choose"
															value="등록" onclick="javascript:Dregister()">
													</div>

											</form>
                                            </div> <!-- end col -->
                                        </div>
                                        <!-- end row -->

                                    </div> <!-- end card-body -->
                                </div> <!-- end card -->
                            </div> <!-- end col -->
                        </div> <!-- end row -->

				</div> <!-- container -->

                </div> <!-- content -->

                <!-- Footer Start -->
                <footer class="footer">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6">
                                2018 - 2019 Â© Hyper - Coderthemes.com
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
                                <small>Paul Burgess just purchased âHyper - Admin Dashboardâ!</small>
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
                                <small>Paul Burgess just purchased âHyper - Admin Dashboardâ!</small>
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

</body>
</html>
