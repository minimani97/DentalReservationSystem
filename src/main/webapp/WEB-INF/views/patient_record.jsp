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
        <link rel="shortcut icon" href="resources/images/favicon.ico">
        
		<!-- jquery.js -->
		<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
		<!-- jquery.js ends -->
		
        <!-- 민정js -->
		<script src="resources/js/dentist.js"></script>


        <!-- third party css -->
        <link href="resources/css/dataTables.bootstrap4.css" rel="stylesheet" type="text/css" />
        <link href="resources/css/responsive.bootstrap4.css" rel="stylesheet" type="text/css" />
        <link href="resources/css/buttons.bootstrap4.css" rel="stylesheet" type="text/css" />
        <link href="resources/css/select.bootstrap4.css" rel="stylesheet" type="text/css" />
        <!-- third party css end -->

        <!-- App css -->
        <link href="resources/css/icons.min.css?after" rel="stylesheet" type="text/css" />
        <link href="resources/css/app.min.css?after" rel="stylesheet" type="text/css" />
        
        <!-- <script >
        $(document).ready(function() {
        	getPriscription(num);
        });
        </script> -->

    </head>

    <body>
    	<form>
    		<input type="hidden" id="c_code" value="1">
    		<input type="hidden" id="d_code" value="1">
    		<input type="hidden" id="num" value="">
    		<input type="hidden" id="date" value="">
    		<input type="hidden" id="p_name" value="">
    		<input type="hidden" id="content" value="">
    	</form>
    
    <div id="myModal" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="padding-right: 16px; display: none;" aria-modal="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="myModalLabel">진단 내용</h4>
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                    </div>
                                                    
                                                    <div class="modal-body" id="DBcontentBox">
                                                    	<!-- <script>
                                                   	 		DBgetContent(num, date, p_name, content);
                                                    	</script> -->
                                                       
                                                    </div>
                                                    <div class="modal-footer" id="modalButton">
                                                        <!-- <button type="button" class="btn btn-light" data-dismiss="modal">닫기</button> -->
                                                        <!-- <button type="button" class="btn btn-primary" id="ContentAlter" onclick="javascript:AlterContent()">수정</button> -->
                                                    </div>
                                                </div>
                                            </div>
   </div>
    
    
    
<input type="hidden" id="patientCode" value=""> 
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
                                    <h4 class="page-title" value="">환자 :></h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 


                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <!-- <h4 class="header-title">Basic Data Table</h4> -->
                                        <p class="text-muted font-14 mb-4">
                                        	환자의 개인 진료 내역 입니다.
                                        </p>

                                        <table id="basic-datatable" class="table dt-responsive nowrap" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>이름</th>
                                                    <th>날짜</th>
                                                    <th>진료분야</th>
                                                    <th>의료진</th>
                                                    <th>특이사항</th>
                                                    <th>정보 동의 여부</th>
                                                    <th>진단 내용</th>
                                                </tr>
                                            </thead>
                                        
                                            <tbody id="onePatientList">
                                            	<script>
                                            		One_Patient_List();
                                            	</script>
                                            </tbody>
                                        </table>

                                    </div> <!-- end card body-->
                                </div> <!-- end card -->
                            </div><!-- end col-->
                        </div>
                        <!-- end row-->

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

        <!-- third party js -->
        <script src="resources/js/jquery.dataTables.min.js"></script>
        <script src="resources/js/dataTables.bootstrap4.js"></script>
        <script src="resources/js/dataTables.responsive.min.js"></script>
        <script src="resources/js/responsive.bootstrap4.min.js"></script>
        <script src="resources/js/dataTables.buttons.min.js"></script>
        <script src="resources/js/buttons.bootstrap4.min.js"></script>
        <script src="resources/js/buttons.html5.min.js"></script>
        <script src="resources/js/buttons.flash.min.js"></script>
        <script src="resources/js/buttons.print.min.js"></script>
        <script src="resources/js/dataTables.keyTable.min.js"></script>
        <script src="resources/js/dataTables.select.min.js"></script>
        <!-- third party js ends -->

        <!-- demo app -->
        <script src="resources/js/demo.datatable-init.js"></script>
        <!-- end demo js-->

    </body>
</html>
