 
 jQuery(function($) {
             $(document).ready(function() {
                 if (ismobile == 0) {
                     initialize();
                 };
             });
         });

         //<![CDATA[
         // this variable will collect the html which will eventually be placed in the side_bar
         //var side_bar_html = "";

         // arrays to hold copies of the markers and html used by the side_bar
         // because the function closure trick doesnt work there
         var gmarkers = [];
         var map = null;

         // A function to create the marker and set up the event window function
         function createMarker(latlng, pin, name, html) {
             var contentString = html;
             var marker = new google.maps.Marker({
                 position: latlng,
                 icon: pin,
                 map: map,
                 zIndex: Math.round(latlng.lat() * -100000) << 5
             });

             google.maps.event.addListener(marker, 'click', function() {
                 infowindow.setContent('<div class="info_content">' + contentString + '<div>');
                 infowindow.open(map, marker);
             });
             // save the info we need to use later for the side_bar
             gmarkers.push(marker);
             // add a line to the side_bar html
             //side_bar_html += '<a href="javascript:myclick(' + (gmarkers.length-1) + ')">' + name + '<\/a><br>';
         }

         function initialize() {
             // create the map
             var myOptions = {
                 zoom: 10,
                 scrollwheel: false,
                 center: new google.maps.LatLng(32.9496497, -97.1264698),
                 mapTypeControl: true,
                 mapTypeControlOptions: {
                     style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
                 },
                 navigationControl: true,
                 mapTypeId: google.maps.MapTypeId.ROADMAP,
                 // How you would like to style the map.
                 // This is where you would paste any style found on Snazzy Maps.
                 styles: [{
                     "featureType": "administrative",
                     "elementType": "geometry",
                     "stylers": [{
                         "color": "#a7a7a7"
                     }]
                 }, {
                     "featureType": "administrative",
                     "elementType": "labels.text.fill",
                     "stylers": [{
                         "visibility": "on"
                     }, {
                         "color": "#737373"
                     }]
                 }, {
                     "featureType": "landscape",
                     "elementType": "geometry.fill",
                     "stylers": [{
                         "visibility": "on"
                     }, {
                         "color": "#efefef"
                     }]
                 }, {
                     "featureType": "poi",
                     "elementType": "geometry.fill",
                     "stylers": [{
                         "visibility": "on"
                     }, {
                         "color": "#dadada"
                     }]
                 }, {
                     "featureType": "poi",
                     "elementType": "labels",
                     "stylers": [{
                         "visibility": "off"
                     }]
                 }, {
                     "featureType": "poi",
                     "elementType": "labels.icon",
                     "stylers": [{
                         "visibility": "off"
                     }]
                 }, {
                     "featureType": "road",
                     "elementType": "labels.text.fill",
                     "stylers": [{
                         "color": "#696969"
                     }]
                 }, {
                     "featureType": "road",
                     "elementType": "labels.icon",
                     "stylers": [{
                         "visibility": "off"
                     }]
                 }, {
                     "featureType": "road.highway",
                     "elementType": "geometry.fill",
                     "stylers": [{
                         "color": "#ffffff"
                     }]
                 }, {
                     "featureType": "road.highway",
                     "elementType": "geometry.stroke",
                     "stylers": [{
                         "visibility": "on"
                     }, {
                         "color": "#b3b3b3"
                     }]
                 }, {
                     "featureType": "road.highway",
                     "elementType": "labels.icon",
                     "stylers": [{
                         "visibility": "simplified"
                     }, {
                         "hue": "#ff0009"
                     }, {
                         "saturation": "-100"
                     }, {
                         "lightness": "8"
                     }]
                 }, {
                     "featureType": "road.arterial",
                     "elementType": "geometry.fill",
                     "stylers": [{
                         "color": "#ffffff"
                     }]
                 }, {
                     "featureType": "road.arterial",
                     "elementType": "geometry.stroke",
                     "stylers": [{
                         "color": "#d6d6d6"
                     }]
                 }, {
                     "featureType": "road.local",
                     "elementType": "geometry.fill",
                     "stylers": [{
                         "visibility": "on"
                     }, {
                         "color": "#ffffff"
                     }, {
                         "weight": 1.8
                     }]
                 }, {
                     "featureType": "road.local",
                     "elementType": "geometry.stroke",
                     "stylers": [{
                         "color": "#d7d7d7"
                     }]
                 }, {
                     "featureType": "transit",
                     "elementType": "all",
                     "stylers": [{
                         "color": "#808080"
                     }, {
                         "visibility": "off"
                     }]
                 }, {
                     "featureType": "water",
                     "elementType": "geometry.fill",
                     "stylers": [{
                         "color": "#d3d3d3"
                     }]
                 }],
             }
             map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

             google.maps.event.addListener(map, 'click', function() {
                 infowindow.close();
             });

             // Add markers to the map
             // Set up three markers with info windows

             var lat = 33.137224900000
             var lon = -96.849817400000
             var point = new google.maps.LatLng(lat, lon)
             var pin = 'http://cf.gatewaypeople.com/prod/s3fs-public/locator_frs.png?null'
             var marker = createMarker(point, pin, 'Frisco Campus', '<a href="/campus/frisco"><img class="map_campus_img" src="http://cf.gatewaypeople.com/prod/s3fs-public/styles/location_map/public/images/campus/frisco_0.jpg?oRnsE.LZOdv2UeP1k1zuZnYSbdhpnnj2&amp;itok=jwBD_dvu"></a>' +
                 '<a href="/campus/frisco" class="button arrow-point-right arrow-after color-white arrow-point-right arrow-after">Campus Info</a>' +
                 '<h5><a href="/campus/frisco">Frisco Campus <span class="campus-abbr">frs</span></a></h5>' +
                 '<div class="info_address">' +
                 '<p>7125 Legacy Drive<br />' +
                 'Frisco, ' +
                 'TX ' +
                 '75034<br />' +
                 '469.238.1000' +
                 '<br /> Saturdays: 4:00 pm, 5:45 pm' +
                 '<br /> Sundays: 9:00 am, 10:45 am, 12:30 pm' +
                 '</p>' +
                 '</div>' +
                 '');
             var lat = 32.780849100000
             var lon = -97.027732100000
             var point = new google.maps.LatLng(lat, lon)
             var pin = 'http://cf.gatewaypeople.com/prod/s3fs-public/locator_grp.png?null'
             var marker = createMarker(point, pin, 'Grand Prairie Campus', '<a href="/campus/grand-prairie"><img class="map_campus_img" src="http://cf.gatewaypeople.com/prod/s3fs-public/styles/location_map/public/images/campus/ministries_life_contact-us_webcard.jpg?Zv_8CetokRpq4uwVGspvSAUah6LlT1uK&amp;itok=GBfX8U9q"></a>' +
                 '<a href="/campus/grand-prairie" class="button arrow-point-right arrow-after color-white arrow-point-right arrow-after">Campus Info</a>' +
                 '<h5><a href="/campus/grand-prairie">Grand Prairie Campus <span class="campus-abbr">grp</span></a></h5>' +
                 '<div class="info_address">' +
                 '<p>2404 N Carrier Pkwy<br />' +
                 'Grand Prairie, ' +
                 'TX ' +
                 '75050<br />' +
                 '972.314.9000' +
                 '<br /> Saturdays: 4:00 pm' +
                 '<br /> Sundays: 9:00 am, 10:45 am, 12:30 pm' +
                 '</p>' +
                 '</div>' +
                 '');
             var lat = 32.874623400000
             var lon = -97.291302800000
             var point = new google.maps.LatLng(lat, lon)
             var pin = 'http://cf.gatewaypeople.com/prod/s3fs-public/locator_nfw.png?null'
             var marker = createMarker(point, pin, 'North Fort Worth Campus', '<a href="/campus/north-fort-worth"><img class="map_campus_img" src="http://cf.gatewaypeople.com/prod/s3fs-public/styles/location_map/public/images/campus/nfw_0.jpg?EAxQjA1ins7drf5Tvmlfunc0g8q8JKaA&amp;itok=aKQPk9lY"></a>' +
                 '<a href="/campus/north-fort-worth" class="button arrow-point-right arrow-after color-white arrow-point-right arrow-after">Campus Info</a>' +
                 '<h5><a href="/campus/north-fort-worth">North Fort Worth Campus <span class="campus-abbr">nfw</span></a></h5>' +
                 '<div class="info_address">' +
                 '<p>4209 Basswood Blvd<br />' +
                 'Fort Worth, ' +
                 'TX ' +
                 '76137<br />' +
                 '817.552.7581' +
                 '<br /> Saturdays: 4:00 pm, 5:45 pm' +
                 '<br /> Sundays: 9:00 am, 10:45 am, 12:30 pm' +
                 '</p>' +
                 '</div>' +
                 '');
             var lat = 32.884558400000
             var lon = -97.205769900000
             var point = new google.maps.LatLng(lat, lon)
             var pin = 'http://cf.gatewaypeople.com/prod/s3fs-public/locator_nrh.png?null'
             var marker = createMarker(point, pin, 'NRH Campus', '<a href="/campus/north-richland-hills"><img class="map_campus_img" src="http://cf.gatewaypeople.com/prod/s3fs-public/styles/location_map/public/images/campus/ministries_life_contact-us_nrh_webcard.jpg?m1QiXvPzVZQiRIdZGS6m0VJQf17GqyVD&amp;itok=u2eQQmFe"></a>' +
                 '<a href="/campus/north-richland-hills" class="button arrow-point-right arrow-after color-white arrow-point-right arrow-after">Campus Info</a>' +
                 '<h5><a href="/campus/north-richland-hills">NRH Campus <span class="campus-abbr">nrh</span></a></h5>' +
                 '<div class="info_address">' +
                 '<p>7501 Davis Blvd.<br />' +
                 'North Richland Hills, ' +
                 'TX ' +
                 '76182<br />' +
                 '817.328.2000' +
                 '<br /> Saturdays: 4:00 pm, 5:45 pm' +
                 '<br /> Sundays: 9:00 am, 10:45 am, 12:30 pm' +
                 '</p>' +
                 '</div>' +
                 '');
             var lat = 32.949752900000
             var lon = -97.126649500000
             var point = new google.maps.LatLng(lat, lon)
             var pin = 'http://cf.gatewaypeople.com/prod/s3fs-public/locator_slk.png?null'
             var marker = createMarker(point, pin, 'Southlake Campus', '<a href="/campus/southlake"><img class="map_campus_img" src="http://cf.gatewaypeople.com/prod/s3fs-public/styles/location_map/public/images/campus/slk_0.jpg?pDAOvvGuRnMwm5PVR2DwW5MUMR_xIbgS&amp;itok=FMNxCK4j"></a>' +
                 '<a href="/campus/southlake" class="button arrow-point-right arrow-after color-white arrow-point-right arrow-after">Campus Info</a>' +
                 '<h5><a href="/campus/southlake">Southlake Campus <span class="campus-abbr">slk</span></a></h5>' +
                 '<div class="info_address">' +
                 '<p>700 Blessed Way<br />' +
                 'Southlake, ' +
                 'TX ' +
                 '76092<br />' +
                 '817.552.5800' +
                 '<br /> Saturdays: 4:00 pm, 5:45 pm' +
                 '<br /> Sundays: 9:00 am, 10:45 am, 12:30 pm' +
                 '</p>' +
                 '</div>' +
                 '');
             var lat = 32.914611100000
             var lon = -96.787130400000
             var point = new google.maps.LatLng(lat, lon)
             var pin = 'http://cf.gatewaypeople.com/prod/s3fs-public/locator_dal.png?null'
             var marker = createMarker(point, pin, 'Dallas Campus', '<a href="/campus/dallas"><img class="map_campus_img" src="http://cf.gatewaypeople.com/prod/s3fs-public/styles/location_map/public/images/campus/dal_webcard.jpg?mLY9fAwNJ60CYSntFJ0KT5wMizvoOQAM&amp;itok=Sq2ZMo94"></a>' +
                 '<a href="/campus/dallas" class="button arrow-point-right arrow-after color-white arrow-point-right arrow-after">Campus Info</a>' +
                 '<h5><a href="/campus/dallas">Dallas Campus <span class="campus-abbr">dal</span></a></h5>' +
                 '<div class="info_address">' +
                 '<p>12123 Hillcrest Rd<br />' +
                 'Dallas, ' +
                 'TX ' +
                 '75230<br />' +
                 '469.801.7250' +
                 '<br /> Saturdays: 5:45 pm' +
                 '<br /> Sundays: 9:00 am, 10:45 am' +
                 '</p>' +
                 '</div>' +
                 '');
         }

         if (ismobile == 0) {
             var infowindow = new InfoBubble({
                 map: map,
                 content: '<div class="info_content">Some label</div>',
                 position: new google.maps.LatLng(-35, -35),
                 shadowStyle: 3,
                 padding: 20,
                 backgroundColor: '#CBCACA',
                 borderRadius: 0,
                 arrowSize: 25,
                 borderWidth: 1,
                 borderColor: '#CBCACA',
                 disableAutoPan: false,
                 hideCloseButton: true,
                 arrowPosition: 25, //arrowPosition: 25,
                 backgroundClassName: 'info-wrapper',
                 arrowStyle: 0,
                 disableAnimation: true,
                 maxWidth: 375,
                 minWidth: 375,
                 maxHeight: 340,
                 minHeight: 340
             });
         };

         // This function picks up the click and opens the corresponding info window
         function myclick(i) {
             google.maps.event.trigger(gmarkers[i], "click");
         }
