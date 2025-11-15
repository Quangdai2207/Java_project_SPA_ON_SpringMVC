export const theme = () => {
    const html = $("html");
    const savedTheme = localStorage.getItem("theme");

    if (savedTheme === "dark" || (!savedTheme && window.matchMedia("(prefers-color-scheme: dark)").matches)) {
        html.addClass("dark");
        $(".light-mode-icon").addClass("hidden");
        $(".dark-mode-icon").removeClass("hidden");
    }
}