import adminItems from "./handle_items.js";

export default function dropdownSuperAdminRender() {
    const admin_dropdowns = [
        $("#super-admin-log"),
        $("#super-admin-manage-account"),
        $("#super-admin-middleware"),
    ];
    adminItems(admin_dropdowns)
}