
function backLastPage() {
    onMenuItemClicked(lastItem);
}


function clearSource() {
    var elements = $("body").children();
    for (var i = 0; i < elements.length; i++) {
        var current = elements[i];
        var cls = $(current).attr('class');
        if (cls && (cls.includes("window") || cls.includes("combo-p") || cls.includes("split-proxy"))) {
            $(current).remove();
        }
    }
}

function onTopBtnClicked(obj) {
    if (obj) {
        var btn = $(obj).data("btn");
        if (btn) {
            if (btn == "pswd") {
                if (curUserName != "admin") {
                    showMsg("Note", "Can not modify passwd");
                } else {
                    loadPage(basePath + "/pages/frames/editAdminPswdPage.html")
                }
            } else if (btn == "about") {
                loadPage(basePath + "/pages/frames/aboutPage.html")
            } else if (btn == "logout") {
                $.messager.confirm("Exit", "Are you sure？", function (ok) {
                    if (ok) {
                        exitSystem();
                        location.href = "/";
                    }
                });
            }
        }
    }
}

function onMenuItemClicked(obj) {
    lastItem = obj;
    $("#contentPanel").empty();
    var fst = $(obj).attr('id');
    if (needClear) {
        clearSource();
    } else {
        needClear = true;
    }

    if (obj) {
        var uri = $(obj).data("src");
        loadPage(uri);
    }
}

function loadPage(uri) {
    ctxInited = false;
    if (uri) {
        showMask("Loading pls wait ...", null);
        $("#contentPanel").load(uri, null, function (response, statusCode, xhr) {
            $.parser.onComplete = function () {
                hideMask();
                init();
            };
            $.parser.parse("#contentPanel");
        });
    }
}

function exitSystem() {
    $.ajax({
        url: basePath + "/User/logout"
    });
}

function saveAdminPswd() {
    if (curUserName != "admin") {
        showMsg("Note", "Can not modify passwd");
        return;
    }

    if ($("#editAdminPswdfm").form("validate")) {
        showMask();
        var data = form2Json("editAdminPswdfm");
        data.act = "updateAdminPswd";
        data.userAccount = "admin";
        data._method = "PUT";

        var opt = {};
        opt.url = basePath + "/hospital/user";
        opt.type = T_PUT;
        opt.data = data;
        opt.callback = function (result) {
            hideMask();
            showMsg("Note", getTxt(result.msg));
        }
    }

    sendAjax(opt);
}

var mainPlatform = {
    init: function () {
        this.bindEvent();
    },

    bindEvent: function () {
        $(".sider-nav li").on("click", function () {
            $(".sider-nav li").removeClass("current");
            $(this).addClass("current");
        });

        $(".sider-nav-s li").on('click', function () {
            $('.sider-nav-s li').removeClass('active');
            $(this).addClass('active');
            onMenuItemClicked(this);
        });

        $(".pf-nav li").on("click", function () {
            onTopBtnClicked(this)
        });
    }
};