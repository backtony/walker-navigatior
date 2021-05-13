<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>WALKER NAVIGATOR</title>

  <script src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=${appKey}"></script>
  <script src="${pageContext.request.contextPath}/static/js/map.js"></script>
	<!-- Global stylesheets -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/static/css/icons/icomoon/styles.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/static/css/bootstrap_limitless.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/static/css/layout.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/static/css/components.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/static/css/colors.min.css" rel="stylesheet" type="text/css">
	<!-- /global stylesheets -->

	<!-- Core JS files -->
	<script src="${pageContext.request.contextPath}/static/js/main/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/main/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/plugins/loaders/blockui.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/plugins/ui/ripple.min.js"></script>
	<!-- /core JS files -->

	<!-- Theme JS files -->
	<script src="${pageContext.request.contextPath}/static/js/plugins/visualization/d3/d3.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/plugins/visualization/d3/d3_tooltip.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/plugins/forms/styling/switchery.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/plugins/forms/selects/bootstrap_multiselect.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/plugins/ui/moment/moment.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/plugins/pickers/daterangepicker.js"></script>

	<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/demo_pages/dashboard.js"></script>
	<!-- /theme JS files -->

</head>

<body onload="load()">

	<!-- Main navbar -->
	<div class="navbar navbar-expand-md navbar-dark bg-indigo navbar-static">
		<div class="navbar-brand">
			<a href="/" class="d-inline-block">
        
				<img src="${pageContext.request.contextPath}/static/images/logo_white.png" alt="">
			</a>  
		</div>

		<div class="d-md-none">
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-mobile">
				<i class="icon-tree5"></i>
        
			</button>
			<button class="navbar-toggler sidebar-mobile-main-toggle" type="button">
				<i class="icon-search4"></i>
			</button>
		</div>

		<div class="collapse navbar-collapse" id="navbar-mobile">
			<ul class="navbar-nav">
				<li class="nav-item">
					<!-- <a href="#" class="navbar-nav-link sidebar-control sidebar-main-toggle d-none d-md-block">
						<i class="icon-paragraph-justify3"></i>
					</a> -->
				</li>
			</ul>

			<!-- <span class="navbar-text ml-md-3">
				<span class="badge badge-mark border-orange-300 mr-2"></span>
				morning, gloria
			</span> -->

			<ul class="navbar-nav ml-md-auto">
				<!-- <li class="nav-item dropdown">
					<a href="#" class="navbar-nav-link dropdown-toggle" data-toggle="dropdown">
						<i class="icon-make-group mr-2"></i>
						Connect
					</a>

					<div class="dropdown-menu dropdown-menu-right dropdown-content wmin-md-350">
						<div class="dropdown-content-body p-2">
							<div class="row no-gutters">
								<div class="col-12 col-sm-4">
									<a href="#" class="d-block text-default text-center ripple-dark rounded p-3">
										<i class="icon-github4 icon-2x"></i>
										<div class="font-size-sm font-weight-semibold text-uppercase mt-2">Github</div>
									</a>

									<a href="#" class="d-block text-default text-center ripple-dark rounded p-3">
										<i class="icon-dropbox text-blue-400 icon-2x"></i>
										<div class="font-size-sm font-weight-semibold text-uppercase mt-2">Dropbox</div>
									</a>
								</div>
								
								<div class="col-12 col-sm-4">
									<a href="#" class="d-block text-default text-center ripple-dark rounded p-3">
										<i class="icon-dribbble3 text-pink-400 icon-2x"></i>
										<div class="font-size-sm font-weight-semibold text-uppercase mt-2">Dribbble</div>
									</a>

									<a href="#" class="d-block text-default text-center ripple-dark rounded p-3">
										<i class="icon-google-drive text-success-400 icon-2x"></i>
										<div class="font-size-sm font-weight-semibold text-uppercase mt-2">Drive</div>
									</a>
								</div>

								<div class="col-12 col-sm-4">
									<a href="#" class="d-block text-default text-center ripple-dark rounded p-3">
										<i class="icon-twitter text-info-400 icon-2x"></i>
										<div class="font-size-sm font-weight-semibold text-uppercase mt-2">Twitter</div>
									</a>

									<a href="#" class="d-block text-default text-center ripple-dark rounded p-3">
										<i class="icon-youtube text-danger icon-2x"></i>
										<div class="font-size-sm font-weight-semibold text-uppercase mt-2">Youtube</div>
									</a>
								</div>
							</div>
						</div>
					</div>
				</li> -->

				<li class="nav-item dropdown">
					<!-- <a href="#" class="navbar-nav-link dropdown-toggle" data-toggle="dropdown">
						<i class="icon-pulse2 mr-2"></i>
						Activity
					</a> -->
				
				</li>

				<!-- <li class="nav-item">
					<a href="#" class="navbar-nav-link">
						<i class="icon-switch2"></i>
						<span class="d-md-none ml-2">Logout</span>
					</a>
				</li> -->
			</ul>
		</div>
	</div>
	<!-- /main navbar -->


	<!-- Page content -->
	<div class="page-content">

		<!-- Main sidebar -->
		<div class="sidebar sidebar-light sidebar-main sidebar-expand-md">

			<!-- Sidebar mobile toggler -->
			<div class="sidebar-mobile-toggler text-center">
				<a href="#" class="sidebar-mobile-main-toggle">
					<i class="icon-arrow-left8"></i>
				</a>
				<span class="font-weight-semibold">검색</span>
				<a href="#" class="sidebar-mobile-expand">
					<i class="icon-screen-full"></i>
					<i class="icon-screen-normal"></i>
				</a>
			</div>
			<!-- /sidebar mobile toggler -->


			<!-- Sidebar content -->
			<div class="sidebar-content">
        
				<!-- Main navigation -->
				<div class="card card-sidebar-mobile">

				    

					<ul class="nav nav-sidebar" data-nav-type="accordion">
            <li class="nav-item px-3 my-3 input-group">
              <input type="text" class="form-control" placeholder="출발지 검색">
              <button class="btn btn-sm btn-outline-secondary" type="button" id="button-addon2">검색</button>
            </li>

            <li class="nav-item px-3 input-group">
              <input type="text" class="form-control" placeholder="도착지 검색">
              <button class="btn btn-sm btn-outline-secondary" type="button" id="button-addon2">검색</button>
            </li>
            
              <!-- Main -->
              <li class="nav-item-header">
                <div class="text-uppercase font-size-xs line-height-xs">목록</div> <i class="icon-menu" title="Main"></i>
              </li>
              
              <li class="nav-item" id="destination-list">
                <a href="#" class="nav-link active">
                  <i class="icon-paragraph-justify3"></i>
                  <span>
                    검색 결과
                  </span>
                </a>
              </li>
              
              
              <!-- /main -->

						

						

					</ul>
				</div>
				<!-- /main navigation -->

			</div>
			<!-- /sidebar content -->
			
		</div>
		<!-- /main sidebar -->


		<!-- Main content -->
		<div class="content-wrapper">



			<!-- Content area -->
			<div class="">
        <div id="map_div">
        </div>
			</div>
			<!-- /content area -->



		</div>
		<!-- /main content -->

	</div>
	<!-- /page content -->

</body>
</html>
