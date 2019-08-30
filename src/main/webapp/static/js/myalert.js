function alertModal(message,title,url) {

    $("#modal-content").html(message);
    if(title && message) {
        $("#modal-ok").click(function(){
            $(top.siMenu('z10086','lm520',title,url));
        });
    }
    var option={
        show:true,
        backdrop:'static'
    }
    $("#alert").modal(option);
}