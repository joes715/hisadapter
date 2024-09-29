function loadPatientData() {
    if (ctxInited) {
        $("#hisPatientDataTbl").datagrid("reload", {
            "act": "queryByParam",
            "wardCode": $("#wardCode").textbox("getValue"),
            "keyword": $("#keyword").textbox("getValue")
        });
    }
}

//opr1,opr2: Direct
//0,0:left to right
//0,1:all to right
//1,0:right to left
//1,1:all to left
function sg_cgmvItem(opr1, opr2) {

    //left to right
    if (opr1 == 0) {
        var checkedItems = null;

        //all to right
        if (opr2 == 1) {
            $("#device_tbl").datalist("checkAll");
        }
        checkedItems = $("#device_tbl").datalist("getChecked");

        if (null != checkedItems && checkedItems.length > 0) {
            var buf = [];
            $.each(checkedItems, function (index, item) {
                buf.push(item);
            });

            var data = $("#sg_device_tbl").datalist("getData");
            var rows = null;
            if (data && data.rows.length > 0) {
                rows = data.rows;
            }
            for (var i = buf.length - 1; i >= 0; i--) {
                var found = false;
                if (null != rows && rows.length > 0) {
                    $.each(rows, function (index, item) {
                        if (buf[i].arg3 == item.arg3) {
                            found = true;
                        }
                    });
                }
                if (!found) {
                    $("#sg_device_tbl").datalist("appendRow", buf[i]);
                }
                var rowIndex = $('#device_tbl').datalist('getRowIndex', buf[i]);
                $("#device_tbl").datalist("deleteRow", rowIndex);
            }
        } else {
            showMsg("Note", "Pls select some to moving");
        }

        resetTable("device_tbl");
        resetTable("call_group_device_tbl");
    }

    //right to left
    if (opr1 == 1) {
        var checkedItems = null;
        //all to left
        if (opr2 == 1) {
            $("#sg_device_tbl").datalist("checkAll");
        }
        checkedItems = $("#sg_device_tbl").datalist("getChecked");

        if (null != checkedItems && checkedItems.length > 0) {
            var buf = [];
            $.each(checkedItems, function (index, item) {
                buf.push(item);
            });
            var data = $("#device_tbl").datalist("getData");
            var rows = null;
            if (data && data.rows.length > 0) {
                rows = data.rows;
            }
            for (var i = buf.length - 1; i >= 0; i--) {
                var found = false;
                if (null != rows && rows.length > 0) {
                    $.each(rows, function (index, item) {
                        if (buf[i].arg3 == item.arg3) {
                            found = true;
                        }
                    });
                }
                if (!found) {
                    $("#device_tbl").datalist("appendRow", buf[i]);
                }
                var rowIndex = $('#sg_device_tbl').datalist('getRowIndex', buf[i]);
                $("#sg_device_tbl").datalist("deleteRow", rowIndex);
            }
        } else {
            showMsg("Note", "Pls select some to moving");
        }

        resetTable("device_tbl");
        resetTable("sg_device_tbl");
    }
}
