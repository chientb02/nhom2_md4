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
                        <td>${data[i].address.name} ${data[i].address.city.name}</td>
                        <td>${data[i].bedroom_count}</td>
                        <td>${data[i].bathroom_count}</td>
                        <td>${data[i].description}</td>
                        <td>${data[i].price}</td>
                        <td><p id="\img${data[i].idHome}\"></p></td>

                        <td>${data[i].status.name}</td>
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
displayAddress()
function displayCity() {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/cities`,
        success: function (data) {
            let content = "<label for='select_city'>Thành phố</label><br>"
            content += '<select id="select_city" onchange="displayDistrict()"  class="form-select">';
            content += `<option>--Chọn thành phố--</option>`;
            for (let i = 0; i<data.length; i++) {

                content += `<option value = ${data[i].idCity}> ${data[i].name} </option>`;
            }
            content += '</select>'
            document.getElementById("city").innerHTML = content;
        }

    })
}
function displayDistrict() {
    let idCity = $('#select_city').val();
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/api/addresses/city/${idCity}`,
        success: function (data) {
            let content = "<label for='select_district'>Quận/huyện</label><br>"
            content += '<select id="select_district"  class="form-select">';
            for (let i = 0; i<data.length; i++) {
                content += `<option value = ${data[i].idAddress}> ${data[i].name} </option>`;
            }
            content += '</select>'
            document.getElementById("district").innerHTML = content;
        }
    })
}
function displayStatus() {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/status`,
        success: function (data) {
            let content = "<label for='select_status'>Trạng thái</label><br>"
            content += '<select id="select_status" class="form-select">';
            content += `<option>--Chọn trạng thái--</option>`;
            for (let i = 0; i<data.length; i++) {
                content += `<option value = ${data[i].idStatus}> ${data[i].name} </option>`;
            }
            content += '</select>'
            document.getElementById("status").innerHTML = content;
        }

    })
}
function displayAddress() {
    displayCity();
    displayDistrict();
    displayStatus()
}
function save() {
    let home
    let name = $("#name").val()
    let bedroom_count = $("#bedroom_count").val()
    let bathroom_count = $("#bathroom_count").val()
    let description = $("#description").val()
    let price = $("#price").val()
    let district = $("#select_district").val()
    let status = $("#select_status").val()
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
            address: {
                idAddress: district
            },
            status: {
                idStatus: status
            },
            image: localStorage.getItem("img")
        }
    } else {
        home = {
            name: name,
            bedroom_count: bedroom_count,
            bathroom_count: bathroom_count,
            description: description,
            price: price,
            address: {
                idAddress: district
            },
            status: {
                idStatus: status
            }
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