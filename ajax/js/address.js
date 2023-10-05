
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
function Filter() {
    let price = $("#price").val();
    let priceValue = price.split("-")
    let minPrice = priceValue[0];
    let maxPrice = priceValue[1];
    let count_bathroom = $("#bathroom").val();
    let count_bedroom = $("#bedroom").val();
    let idCity = $('#select_city').val();
    if(idCity === "--Chọn thành phố--") {
        idCity = null;
    }
    let idDistrict = $('#select_district').val();
    if(idDistrict === undefined) {
        idDistrict = null;
    }
    let idStatus = $('#select_status').val();
    if(idStatus === "--Chọn trạng thái--") {
        idStatus = null;
    }
    newFilter =  {
        minPrice: minPrice,
        maxPrice: maxPrice,
        count_bathroom: count_bathroom,
        count_bedroom: count_bedroom,
        city: {
            idCity: idCity
        },
        address: {
            idAddress : idDistrict
        },
        status: {
            idStatus : idStatus
        }
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newFilter),
        url: "http://localhost:8080/api/filters",
        success: function (data) {
            console.log(data)
            let content = `<h2>List home</h2>`
            content += `<table><tr>
                        <th>STT</th>
                        <th>Name</th>
                        <th>District</th>
                        <th>City</th>
                        <th>Bedroom</th>
                        <th>Bathroom</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Status</th                  
                        <th colspan="2">Action</th>
                        </tr>`
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
                        <td>${i + 1}</td>
                        <td>${data[i].name}</td>
                        <td>${data[i].address.name}</td>
                        <td>${data[i].address.city.name}</td>
                        <td>${data[i].bedroom_count}</td>
                        <td>${data[i].bathroom_count}</td>
                        <td>${data[i].description}</td>
                        <td>${data[i].price}</td>
<!--                        Chưa xử lý image//-->


                        <td>${data[i].status.name}</td>
                     
                     
                        </tr>`
            }
            content += `</table>`
            document.getElementById("1").innerHTML = content
        }




    });
    event.preventDefault()
}