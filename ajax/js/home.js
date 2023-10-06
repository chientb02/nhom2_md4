let arr;
function displayAll1() {
    $.ajax({
        url: "http://localhost:8080/api/homes",
        type: "GET",
        success: function (data) {
            arr = data
            let content = `<h2>List home</h2>`
            content += `<table><tr>
                        <th>STT</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Bedroom</th>
                        <th>Bathroom</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Image</th>
                        <th>Status</th>
                        <th>Account</th>
                        <th colspan="2">Action</th>
                        </tr>`
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
                        <td>${i + 1}</td>
                        <td>${data[i].name}</td>
                        <td>${data[i].address}</td>
                        <td>${data[i].bedroom_count}</td>
                        <td>${data[i].bathroom_count}</td>
                        <td>${data[i].description}</td>
                        <td>${data[i].price}</td>
                        <td><p id="\img${data[i].idHome}\"></p></td>

                        <td>${data[i].status}</td>
                        <td>${data[i].account}</td>
                        
                        <td><button onclick="updateProduct(${data[i].id})">Update</button></td>
                        <td><button onclick="deleteProduct(${data[i].id})">Delete</button></td>
                        </tr>`
                displayImg(data[i].idHome);
            }
            content += `</table>`
            document.getElementById("homes").innerHTML = content
        }
    })
}
function save() {
    let home
    let name = $("#name").val()
    let bedroom_count = $("#bedroom_count").val()
    let bathroom_count = $("#bathroom_count").val()
    let description = $("#description").val()
    let price = $("#price").val()
    let files = $("#file")[0].files

    let formData = new FormData()

    for (let i = 0; i < files.length; i++) {
        formData.append("image" + i, files[i])
    }

    let id = +localStorage.getItem("idUpdate")

    if (file === undefined) {
        file = new File([], "", undefined)
    }

    if (id !== -1) {
        home = {
            id: id,
            name: name,
            bedroom_count: bedroom_count,
            bathroom_count: bathroom_count,
            description: description,
            price: price,
            image: localStorage.getItem("img")
        }
    } else {
        home = {
            name: name,
            bedroom_count: bedroom_count,
            bathroom_count: bathroom_count,
            description: description,
            price: price,
        }
    }


    formData.append("homes",
        new Blob([JSON.stringify(home)], {type: 'application/json'}))

    $.ajax({
        url: "http://localhost:8080/api/homes",
        type: "POST",
        processData: false,
        contentType: false,
        data: formData,
        success: function () {
            alert("Create successfully!")
            displayAll1()
            localStorage.setItem("idUpdate", "-1")
        }
    })
    document.getElementById("form").reset()
    event.preventDefault()
}
function displayImg(id) {
    var settings = {
        "url": `http://localhost:8080/api/homes/img/${id}`,
        "method": "GET",
        "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
        let content = "";
        for (let i = 0; i < response.length; i++) {
            content += `<img style="width: 100px" src="../../src/main/resources/static/image/${response[i].image}" alt=""/>`
        }
        document.getElementById("img" + id).innerHTML = content;
    });
}