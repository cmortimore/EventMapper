<html>
    <head>
        <title>Event Mapper</title>
        <link rel="stylesheet" media="screen" href="/index.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
	</head>
    <body>
		<div id="header">
			<div id="title"><h2>Event Mapper</h2></div>
			<div id="identity">
			<%

                String displayName = (String)session.getAttribute("displayName");
                if ( displayName == null ) displayName = "<a href='/oauth/login'>Login</a>";

			 %>

            <%= displayName %>
			</div>
		</div>
        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
        <script type="text/javascript" src="/mapper.js"></script>
		<div id="events">
			<div id="list"></div>
			<div id="loader"><input type="button" value="Load Events" onclick="fetchEvents();"></div>
		</div>
		<div id="map"></div>
    </body>
</html>
