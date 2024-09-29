function loadEmployeeData() {
    if (ctxInited) {
        $("#hisEmployeeDataTbl").datagrid("reload", {
            "act": "queryByParam",
            "keyword": $("#emp_keyword").textbox("getValue")
        });
    }
}