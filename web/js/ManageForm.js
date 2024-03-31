function displayUpdateForm() {
    document.getElementById("update-form").style.display = "block";
    document.getElementById("blur").style.display = "block";
}
function displayAddForm() {
    document.getElementById("add-form").style.display = "block";
    document.getElementById("blur").style.display = "block";
}
function hideForm() {
    if (document.getElementById("add-form")!==null)
        document.getElementById("add-form").style.display = "none";
    if (document.getElementById("update-form")!==null)
        document.getElementById("update-form").style.display = "none";
    document.getElementById("blur").style.display = "none";
}