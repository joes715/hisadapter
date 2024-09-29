function loadBedData() {
    if (ctxInited) {
        $("#hisBedDataTbl").datagrid("reload", {
            "act": "queryByParam",
            "wardCode": $("#wardCode").textbox("getValue")
        });
    }
}