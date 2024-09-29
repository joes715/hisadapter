
var T_DELETE = "POST";
var T_PUT = "POST";
var min_length_msg = "Min length {0}";
var max_length_msg = "Max length {0} and not space";
var num_required_msg = "Must number";
var num_required_gl0_msg = "Must number, and Large then 0";
var page_size = 20;
var page_list = [20, 40, 80];

function showMsg(title, msg) {
    showMsg(title, msg, null);
}

function showMsg(title, msg, color) {
    new jBox('Notice', {
        width: 300,
        height: 60,
        position: {
            x: 'right',
            y: 'bottom'
        },
        stack: false,
        animation: {
            open: 'slide',
            close: 'zoomIn'
        },
        color: color,
        title: title,
        content: msg
    });
}

function showMask(title, id) {
    var maskTarget;
    if (undefined != id && null != id && "" != id) {
        maskTarget = "#" + id;
    } else {
        maskTarget = document.body;
    }

    if (undefined == title || null == title || "" == title) {
        title = "Loading, pls wait ...";
    }

    if (0 == $("#mask").length && 0 == $("#message").length) {
        $("<div id=\"mask\" class=\"datagrid-mask\"></div>").css({
            display: "block",
            width: "100%",
            height: "100%"
        }).appendTo(maskTarget);
        $("<div id=\"message\" class=\"datagrid-mask-msg\"></div>")
            .html(title)
            .appendTo(maskTarget)
            .css({
                display: "block",
                left: ($(maskTarget).outerWidth(true) - 190) / 2
            });
    } else {
        $("#mask").css("display", "block");
        $("#message").html(title).css("display", "block");
    }
}

function hideMask() {
    if (0 < $("#mask").length && 0 < $("#message").length) {
        $("#mask").remove();
        $("#message").remove();
    }
}

Math.uuid = function () {
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid = new Array(36), rnd = 0, r;
    for (var i = 0; i < 36; i++) {
        if (i == 8 || i == 13 || i == 18 || i == 23) {
            uuid[i] = '-';
        } else if (i == 14) {
            uuid[i] = '4';
        } else {
            if (rnd <= 0x02) {
                rnd = 0x2000000 + (Math.random() * 0x1000000) | 0;
            }
            r = rnd & 0xf;
            rnd = rnd >> 4;
            uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
        }
    }
    return uuid.join('');
};

function getTxt(txt) {
    var result = "";

    if (null != txt) {
        txt = $.trim(txt);
        if ((txt.length > 0) && ("null" != txt) && ("undefined" != txt)) {
            result = txt;
        }
    }

    return result;
}

function isNull(txt) {
    var result = false;

    if (getTxt(txt).length == 0) {
        result = true;
    }

    return result;
}

function getEvent() {
    // IE
    if (document.all) {
        return window.event;
    }

    func = getEvent.caller;
    while (func != null) {
        var arg0 = func.arguments[0];
        if (arg0) {
            if ((arg0.constructor == Event || arg0.constructor == MouseEvent)
                || (typeof (arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
                return arg0;
            }
        }

        func = func.caller;
    }

    return null;
}

function openCenterDialog(id) {
    $("#" + id).dialog("open");
    $("#" + id).dialog('center');
}