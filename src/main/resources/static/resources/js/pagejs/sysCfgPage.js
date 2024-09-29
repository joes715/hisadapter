
function loadDsData() {
    loadSysCfg('queryDbCfg', 'ds-local-fm', 'ds-local');
    loadSysCfg('queryDbCfg', 'ds-his-fm', 'ds-his');
    loadSysCfg('queryDbCfg', 'ds-th-fm', 'ds-th');
    loadSysCfg('queryDbCfg', 'ds-ws-fm', 'ds-ws');
    loadSysCfg('queryGwCfg', 'gwfm', null);
}

function saveSysCfg(act, fmid, ds_type) {
    if ($("#" + fmid).form("validate")) {
        var data = form2Json(fmid);
        data.act = act;
        data.ds_type = ds_type;

        var opt = {};
        opt.url = basePath + "/hospital/syscfg";
        opt.type = "POST";
        opt.data = data;
        opt.callback = function (result) {
            hideMask();
            showMsg("Note", result.msg);
        };

        sendAjax(opt);
    }
}

function loadSysCfg(act, fmid, ds_type) {
    var data = {
        act: act,
        ds_type: ds_type
    };

    var opt = {};
    opt.url = basePath + "/hospital/syscfg";
    opt.type = "GET";
    opt.data = data;
    opt.callback = function (result) {
        hideMask();
        showMsg("Note", result.msg);
        $("#" + fmid).form("load", result.data);
    };

    sendAjax(opt);
}

function showDsSmaple() {
    openCenterDialog("ds_sample");
}