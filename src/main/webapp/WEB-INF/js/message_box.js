(function ($) {
    $.extend({
        warn: function (message, timeToLive) {
            msg('警告', message, 'warning', timeToLive)
        },
        error: function (message, timeToLive) {
            msg('错误', message, 'error', timeToLive)
        },
        success: function (message, timeToLive) {
            msg('成功', message, 'success', timeToLive)
        },
        info: function (message, timeToLive) {
            msg('提示', message, 'information', timeToLive)
        },
        confirm: function (message,fn) {
            $("body").append('<div id="message" class="dialog"><p>'+message+'</p></div>');
            $("#message").dialog({
                autoOpen: true,
                title:'确认操作',
                height: 150,
                resizable: false,
                modal: true,
                close: function () {
                    $(this).remove();
                },buttons: {
                    "确认": function() {
                        fn();
                        $( this ).dialog( "close" );
                    },
                    "取消": function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
        }
    })
    function msg(title, message, type, timeToLive) {
        var ttl = timeToLive || 3000;
        $("body").append('<div id="message"><span class="message" style="padding:20px 15px 15px 50px;"><strong>' + title + ' </strong>' + message + '</span></div>');
        $("#message").click(function(){ $("#message").dialog("close");});
        $("#message").dialog({
            autoOpen: true,
            width: 376,
            height: 60,
            modal: false,
            show: {
                effect: "drop",
                duration: 1000
            },
            hide: {
                effect: "drop",
                duration: 1000
            },
            resizable: false,
            close: function () {
                $("#message").remove();
            }, dialogClass: 'noTitleStuff '+type
        });


        if(ttl > 0){
            setTimeout(function () {
                $("#message").dialog("close");
            }, ttl);
        }
    }
})(jQuery);