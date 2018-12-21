(function ($) {

  $("<li><a href=\"/\">All Campuses</a></li>").insertBefore( $( "#mobile_campus .campus-selector .first") );

  $('.dl-menu ul').addClass('dl-submenu');

  // Remove links we don't want in the mobile menu
  if (('#dl-menu .mobile-menu-disable').length) {
    $('#dl-menu .mobile-menu-disable').parent().remove();
  }

  $( '#dl-menu' ).dlmenu({
    animationClasses : { classin : 'dl-animate-in-2', classout : 'dl-animate-out-2' }
  });

  $(function() {
    $(".dl-trigger").click(function() {
      $(".dl-menuwrapper").toggleClass("open-menu");
      $('body').toggleClass('no-scroll');
    });
  });

  //Android smartbanner menu position
  //if ($(".smartbanner_android")[0]){ $('.dl-menuwrapper .open-menu').css( "top", "82px" ); }

})(jQuery);
