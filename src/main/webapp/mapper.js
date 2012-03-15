$(document).ready(function() {initialize();});

var map;
var infowindow;
var events;
var markers;
var selected;
var latlng = new google.maps.LatLng(37.784088,-122.401455);
function initialize() {
    $('#list').height($(window).height() - 139);
    $('#map').width($(window).width() - 241);
    $('#map').height($(window).height() - 89);

    var myOptions = {
        zoom: 18,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("map"), myOptions);
    infowindow = new google.maps.InfoWindow();
}

function fetchEvents() {
    $('#list').html('');
    if (markers != null) {
        for (var marker in markers) {
            markers[marker].setMap(null);
        }
        markers = null;
    }
    map.setCenter(latlng);
    $.getJSON('/events', function(data) {
        if ( data.error == 401 ) {
            window.location = '/login';
            return;
        } else {
            events = new Object();
            markers = new Object();
        }

        $.each(data, function(key, val) {

            $('<div class="listitem" id="' + val.Id + '">' + val.Name + '</div>').appendTo('#list');
            var myLatlng = new google.maps.LatLng(val.Lat__c,val.Lon__c);
            var icon = 'https://chart.googleapis.com/chart?chst=d_bubble_text_small&chld=bbT|' + val.Name + '|45B7EE|000000';
            var markerImage = new google.maps.MarkerImage(icon);
            var marker = new google.maps.Marker({
                  position: myLatlng,
                  map: map,
                  animation: google.maps.Animation.DROP,
                  title: val.Name,
                  icon: markerImage,
                  eventid: val.Id
              });
            events[val.Id] = val;
            markers[val.Id] = marker;

            google.maps.event.addListener(marker, 'click', function() {
                showInfoWindowByID(this.eventid);
            });


        });
        $(".listitem").click(function() {
            showInfoWindow($(this));
        });

    });

}
function showInfoWindow(clicked) {
    var eventid = clicked.attr('id');
    showInfoWindowByID(eventid);
}

function showInfoWindowByID(eventid) {

    $(selected).css("background-color","white");
    $('#' + eventid).css("background-color","whitesmoke");
    selected = '#' + eventid;

    $.getJSON('/event/' + eventid , function(data) {

        var description = '';
        if ( data.Description__c ) description = data.Description__c;
        var contentString = "<div class='description'><h3>"+ data.Name + "</h3>" + description + "</div>";
        infowindow.setContent(contentString);
        infowindow.open(map,markers[data.Id]);

    });

}
