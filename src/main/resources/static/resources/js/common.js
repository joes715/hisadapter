function sendAjax(opt) {
    showMask();
    $.ajax({
        url: opt.url,
        type: opt.type,
        dataType: "json",
        data: opt.data,
        success: opt.callback,
        error: function (result) {
            hideMask();
            showMsg("Note", "Exception error!");
        }
    });
}

function form2Json(formId) {
    var paramArray = $("#" + formId).serializeArray();
    var json = {};
    $(paramArray).each(function (idx, item) {
        json[item.name] = item.value;
    });
    return json;
}
