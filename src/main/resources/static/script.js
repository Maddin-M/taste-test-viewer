$(document).ready(function () {
    $('.data-table-sortable').DataTable({
        paging: false,
        info: false,
        searching: false,
        autoWidth: false,
        language: {decimal: ','},
        order: [[1, 'desc']],
    });
});