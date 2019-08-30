<div id="navbar" class="navbar navbar-default">
	<div class="container-fluid" id="navbar-container">
		<button type="button" class="navbar-toggle menu-toggler pull-left"
			id="menu-toggler" data-target="#sidebar">
			<span class="sr-only">Toggle sidebar</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>

		<div class="navbar-header pull-left">
			<a class="navbar-brand">
				欢迎来到中朝友联旅游网站管理平台!
			</a>
		</div>

		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav navbar-nav navbar-right">
				<li>
					<img class="img" src="static/assets/images/user.png">
					<a>${user.NAME}</a>
				</li>
				<li>
					<a onclick="editUserH('${user.LG_ID}', '${user.ID}');">密码修改</a>
				</li>
				<li>
					<a href="logout">退出</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="container">
		<img class="img logo-img" src="static/assets/images/logo.png">
		<div class="title">
			<p>
				<span>中朝友联旅游网站管理平台</span>
			</p>
		</div>
	</div>
</div>