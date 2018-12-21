(function($) {
  $(document).ready(function(){
    $('ul.gwp-simple-menu li.expanded').each(function() {
      var list_item = $(this);
      //var parent_anchor = list_item.children("a");
      if (list_item.children(".gwp-simple-menu").length) {
        list_item.children(".gwp-simple-menu a").attr("href","#");
      }
      
      list_item.children(".gwp-simple-menu a").on('click', function(e) {
        e.preventDefault();
        list_item.siblings("li").removeClass("active-trail");
        $(this).parent("li").toggleClass("active-trail");
      });
    });
  });
})(jQuery);
