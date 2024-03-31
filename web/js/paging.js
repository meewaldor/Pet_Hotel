let currentPage = 1;
let rowsPerPage = 10;
function showPage(currPage) {
    let table = document.getElementById("table");
    let rows = document.getElementsByClassName("orderLine");
    let startIndex = (currPage - 1) * rowsPerPage + 1;
    let endIndex = startIndex + rowsPerPage;
    for (let i = 1; i < rows.length; i++) { // Start from 1 to skip the header row
        if (i >= startIndex && i < endIndex) {
            rows[i].style.display = 'table-row';
        } else {
            rows[i].style.display = 'none';
        }
    }
    document.getElementById('currentPage').innerHTML = currPage;
}
function previousPage() {
    if (currentPage > 1)
        currentPage--;
    showPage(currentPage);
}

function nextPage() {
    let rows = table.getElementsByTagName('tr');
    let totalPage = Math.ceil((rows.length - 1) / rowsPerPage);

    if (currentPage < totalPage) {
        currentPage++;
        showPage(currentPage);
    }
}
function initPage() {
    // Initial display
    showPage(currentPage);
}

function doDelete(obj, id) {
    if (confirm("Are you sure to delete service with id = " + id)) {
        window.location = "DispatchServlet?btAction=DeleteService&serviceId=" + id;
    }
}

