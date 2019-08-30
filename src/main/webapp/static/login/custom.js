
/* jQuery Pre loader
 -----------------------------------------------*/
$(window).load(function () {
    $('.preloader').fadeOut(1000); // set duration in brackets    
});


$(document).ready(function () {

    if ($(".homepage").length !== 0) {
        /* Home Slideshow Vegas
         -----------------------------------------------*/
        $(function () {
        	//console.log($('body'));
            $('body').vegas({
                slides: [

                    {src: $("#ctxstatic").val()+'/login/slide-1.jpg'},
                    {src: $("#ctxstatic").val()+'/login/slide-2.jpg'}
                ],
                timer: false,
                transition: ['zoomOut']
            });
        });
        //    $("#submit2").on("click", function () {
        //    event.preventDefault();
          //  window.location.href = "list.html";
        //})
    }



    /* Back top
     -----------------------------------------------*/
    $(window).scroll(function () {
        if ($(this).scrollTop() > 200) {
            $('.go-top').fadeIn(200);
        } else {
            $('.go-top').fadeOut(200);
        }
    });
    // Animate the scroll to top
    $('.go-top').click(function (event) {
        event.preventDefault();
        $('html, body').animate({scrollTop: 0}, 300);
    });

    $(".m-head-menu").on("click", function () {
        $(".mobile-menu-bg").show();
        $(".mobile-menu-box").css("left", "0");
    });

    $(".mobile-menu-bg").on("click", function () {
        $(".mobile-menu-bg").hide();
        $(".mobile-menu-box").css("left", "-100%");
    });

    /* wow
     -------------------------------*/
    new WOW({mobile: false}).init();
    $(".m-head-menu").on("click",function () {
            $(".mobile-menu-bg").show();
            $(".mobile-menu-box").css("left","0");
        });

        $(".mobile-menu-bg").on("click",function () {
            $(".mobile-menu-bg").hide();
            $(".mobile-menu-box").css("left","-100%");
        });
});


