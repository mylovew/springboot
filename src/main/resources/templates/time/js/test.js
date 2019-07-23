jQuery(document).ready(function(){
    //$(".right").css("width",(800-135)+"px");

    jQuery('.scrollbar-inner').scrollbar({
        "onScroll":function (y,x) {
            jQuery('.scroll-element.scroll-x.scroll-scrollx_visible').position({
                of: $( ".timeBody" ),
                at: "center+45 bottom-8"
            });
        }
    });
    jQuery('.scrollbar-dynamic').scrollbar({
        "onScroll":function (y,x) {
            //console.log(x);
        }
    });

    console.log(jQuery('.scroll-element .scroll-x .scroll-scrollx_visible').text());
    function body(hallNumber) {

    }








});