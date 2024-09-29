
function loadRoomData() {
    if (ctxInited) {
        $("#hisRoomDataTbl").datagrid("reload", {
            "act": "queryByParam",
            "wardCode": $("#wardCode").textbox("getValue")
        });
    }
}