export const theme_layout = () => {
    const $html = $("html");
    const savedTheme = localStorage.getItem("theme");

    // Áp dụng theme đã lưu hoặc theo hệ thống
    if (savedTheme === "dark" || (!savedTheme && window.matchMedia("(prefers-color-scheme: dark)").matches)) {
        $html.addClass("dark");
    }

    // Cập nhật icon
    const updateIcons = () => {
        const isDark = $html.hasClass("dark");
        $(".light-mode-icon").toggleClass("hidden", isDark);
        $(".dark-mode-icon").toggleClass("hidden", !isDark);
    };

    // Toggle khi bấm nút
    $('#theme-toggle').on("click", function () {
        $html.toggleClass("dark");
        const theme = $html.hasClass("dark") ? "dark" : "light";
        localStorage.setItem("theme", theme);
        updateIcons();
    });

    // Cập nhật icon khi load
    updateIcons();
}