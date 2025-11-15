import adminItems from "./handle_items.js";

export default function dropdownAdminRender() {
    const admin_dropdowns = [
        $("#admin-system-report"),
        $("#admin-manage-doctor"),
        $("#admin-manage-patient"),
        $("#super-admin-log"),
    ];
    adminItems(admin_dropdowns)
}