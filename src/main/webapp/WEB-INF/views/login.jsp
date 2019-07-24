<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>JJONDDuk_Reservation</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="assets/images/favicon.ico">

        <!-- App css -->
        <link href="resources/css/icons.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/css/app.min.css" rel="stylesheet" type="text/css" />

		<!-- jquery.js -->
		<script type="text/javascript"
			src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
		<!-- jquery.js ends -->
		
		<script src="resources/js/login.js"></script>

    </head>

    <body class="authentication-bg">

        <div class="account-pages mt-5 mb-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <div class="card">

                            <!-- Logo -->
                            <div class="card-header pt-4 pb-4 text-center bg-primary">
                                <a href="http://localhost:8090/reservation">
                                    <span><img src="resources/image/logo.png" alt="" height="40"></span>
                                </a>
                            </div>

                            <div class="card-body p-4">
                                
                                <div class="text-center w-75 m-auto">
                                    <h4 class="text-dark-50 text-center mt-0 font-weight-bold">로그인</h4>
                                    <p class="text-muted mb-4">환자/의료진 여부를 선택하고 로그인하세요.</p>
                                </div>

                                <form action="#">
                                
                                	<div class="form-group">
                                        <div class="custom-control">
                                            <label>
                                            <input type="radio" name="user_type" value="P"/>회원(환자)</label>
    										<label style="margin-left: 20px;">
    										<input type="radio" name="user_type" value="D"/>의료진</label>
    										<label style="margin-left: 20px;">
    										<input type="radio" name="user_type" value="A"/>관리자</label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="fullname">아이디</label>
                                        <input class="form-control" type="text" id="id" required="" placeholder="아이디 입력하세요.">
                                    </div>

                                    <div class="form-group">
                                        <label for="password">비밀번호</label>
                                        <input class="form-control" type="password" required="" id="password" placeholder="비밀번호 입력하세요.">
                                    </div>

                                    <!-- <div class="form-group mb-3">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="checkbox-signin" checked>
                                            <label class="custom-control-label" for="checkbox-signin">로그인 정보 기억하기</label>
                                        </div>
                                    </div> -->

                                    <div class="form-group mb-0 text-center">
                                        <button class="btn btn-primary" type="submit" onclick="goLogin()"> 로그인 </button>
                                    </div>

									<div class="text-center" style="margin-top:20px">
										<a href="http://localhost:8090/reservation/register"
											class="text-muted ml-1"><b>회원가입</b></a>
									</div>

							</form>
                            </div> <!-- end card-body -->
                        </div>
                        <!-- end card -->

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

        <!-- App js -->
        <script src="resources/js/app.min.js"></script>
        
    </body>
</html>
