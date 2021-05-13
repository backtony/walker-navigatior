/* ------------------------------------------------------------------------------
 *
 *  # Custom JS code
 *
 *  Place here all your custom js. Make sure it's loaded after app.js
 *
 * ---------------------------------------------------------------------------- */

$(".content-wrapper").on("click", function(e){
    if ($('body').hasClass('sidebar-mobile-main')) {
        e.preventDefault()
        $('body').toggleClass('sidebar-mobile-main').removeClass('sidebar-mobile-secondary sidebar-mobile-right');
        $('.sidebar-main').removeClass('sidebar-fullscreen');
    }
})