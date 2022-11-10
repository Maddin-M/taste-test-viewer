// prevent all forms from submitting at once
$(document).on("submit", (form) => { form.preventDefault(); });
const toast = $(".toast")

function post(method, table) {
    // ref: https://stackoverflow.com/a/24838789/13045488
    $.ajax({
        type: "POST",
        url: "/rest/" + table + "/" + method,
        data: $("#" + method + "-" + table).serialize(),
    })
    .done(() => { toastSuccess(method + " " + table + " was successful!") })
    .fail(( error ) => { toastError(error.responseText) });
}

function toastError(msg) {
    toast.removeClass("text-bg-success");
    toast.addClass("text-bg-danger");
    showToast(msg);
}

function toastSuccess(msg) {
    toast.removeClass("text-bg-danger");
    toast.addClass("text-bg-success");
    showToast(msg);
}

function showToast(msg) {
    $(".toast-body").text(msg);
    new bootstrap.Toast(toast).show();
}