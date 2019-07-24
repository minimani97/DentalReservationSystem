<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" /> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="assets/images/favicon.ico">
        
        <!-- jquery.js -->
   	   <script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
       <!-- jquery.js ends -->

        <!-- App css -->
        <script src="resources/js/jquery-ui.min.js"></script>
        <link href="resources/css/icons.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/css/app.min.css" rel="stylesheet" type="text/css" />
        <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
         <script src="resources/js/clinic_register.js"></script>
         
         <!-- services 라이브러리 불러오기 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=214630e88c9f141416bd0733325ee211&libraries=services"></script>

    </head>

    <body class="authentication-bg">

        <div class="account-pages mt-5 mb-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <div class="card">
                            <!-- Logo-->
                            <div class="card-header pt-4 pb-4 text-center bg-primary">
                                <a href="index.html">
                                	<span><img src="resources/image/logo.png" alt="" height="40"></span>
                                </a>
                            </div>

                            <div class="card-body p-4">
                                
                                <div class="text-center w-75 m-auto">
                                    <h4 class="text-dark-50 text-center mt-0 font-weight-bold">병원등록</h4>
                                    <p class="text-muted mb-4"></p>
                                </div>

                                <form id="c_form" action="#" >
                                <fieldset>
                                    <div class="form-group">
                                        <label for="fullname">병원명</label>
                                        <input class="form-control" type="text" id="c_name" required="">
                                    </div>

                                    <div class="form-group">
                                        <label for="emailaddress">병원 전화번호</label>
                                        <input class="form-control" type="text" id="c_tel" placeholder="'-' 없이 입력하세요" required="">
                                    </div>

                                    <div class="form-group">
                                        <label for="password">병원 주소</label>
                                        <input class="form-control" type="text"  required="" id="c_address" value="">
                                        <br>
                                        <input type="button" class="btn btn-primary" onclick="sample5_execDaumPostcode2()" value="주소 찾기"/>
                                    	<input type="hidden" id="hide_addr_y" value="">
                                    	<input type="hidden" id="hide_addr_x" value="">
                                    </div>
                                    
                                     <div class="form-group">
                                        <label for="password">사업자 번호</label>
                                        <input class="form-control" type="text" required="" id="c_license">
                                    </div>
                                    

                                    <div class="form-group mb-0 text-center">
                                        <button class="btn btn-primary" type="button" onclick="clinic_register()"> 병원등록  </button>
                                    </div>
                                    </fieldset>
                                </form>
                            </div> <!-- end card-body -->
                        </div>
                        <!-- end card -->

                        <div class="row mt-3">
                            <div class="col-12 text-center">
                                
                            </div> <!-- end col-->
                        </div>
                        <!-- end row -->

                    </div> <!-- end col -->
                </div>
                <!-- end row -->
            </div>
            <!-- end container -->
        </div>
        <!-- end page -->

        <footer class="footer footer-alt">
            2018 - 2019 © Hyper - Coderthemes.com
        </footer>

        
       
    </body>
</html>
