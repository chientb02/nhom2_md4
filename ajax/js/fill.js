function fill() {
    let idHome=localStorage.getItem("idUpdate")
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/api/homes/"+idHome,
        type: "GET",
        success: function (data) {
            document.getElementById("name").value = data.name
            document.getElementById("bedroom_count").value = data.bedroom_count
            document.getElementById("bathroom_count").value = data.bathroom_count
            document.getElementById("description").value = data.description
            document.getElementById("price").value = data.price
        }
    })
}