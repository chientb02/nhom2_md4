let arrHome;
let listDisplayPage;
let numberPage;
let totalPage;

function DisplayAllHomestay() {
    $.ajax({
        url: "http://localhost:8080/api/homes",
        type: "GET",
        success: function (data) {
            showHome(data)
            numberPage = 0;
            arrHome = data;
            listDisplayPage = data.reverse();
            showPage();
            // checkRoleHome()
        }
    })
}
function detailHome() {
    alert("Tính năng đang phát triển! ")
}

function Filter() {
    let price = $("#price").val();
    let priceValue = price.split("-")
    let minPrice = priceValue[0];
    let maxPrice = priceValue[1];
    let count_bathroom = $("#bathroom").val();
    let count_bedroom = $("#bedroom").val();
    let idCity = $('#select_city').val();
    if (idCity === "--Chọn thành phố--") {
        idCity = null;
    }
    let idDistrict = $('#select_district').val();
    if (idDistrict === undefined) {
        idDistrict = null;
    }
    let idStatus = $('#select_status').val();
    if (idStatus === "--Chọn trạng thái--") {
        idStatus = null;
    }
    // if (count_bathroom === "Chọn số phòng") {
    //     count_bathroom = null;
    // }
    // if (count_bedroom === "Chọn số phòng") {
    //     count_bedroom = null;
    // }
    newFilter = {
        minPrice: minPrice,
        maxPrice: maxPrice,
        count_bathroom: count_bathroom,
        count_bedroom: count_bedroom,
        city: {
            idCity: idCity
        },
        address: {
            idAddress: idDistrict
        },
        status: {
            idStatus: idStatus
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
            if (data == null) {
                document.getElementById("homes").innerHTML = "Khong tim thay"
            } else {
                numberPage = 0;
                arrHome = data;
                listDisplayPage = data.reverse();
                showPage();
            }
        }
    });
    event.preventDefault();
}

function checkRoleHome () {
    let role = localStorage.getItem("role") ;
    switch (role) {
        case "ROLE_USER" :
            let elements3 = document.querySelectorAll(".booking");

            for (let i = 0; i < elements3.length; i++) {
                elements3[i].style.display = "block";
            }
            // document.querySelectorAll(".text-body").style.display = "block" ;
            break;
        case "ROLE_HOST" :
            let elements = document.querySelectorAll(".update");

            for (let i = 0; i < elements.length; i++) {
                elements[i].style.display = "block";
            }
            let elements2 = document.querySelectorAll(".delete");

            for (let i = 0; i < elements2.length; i++) {
                elements2[i].style.display = "block";
            }
            // document.querySelectorAll(".update").style.display = "block" ;
            // document.querySelectorAll(".delete").style.display = "block" ;

            break;
    }
}

function searchByName() {
    let search = document.getElementById("search").value
    $.ajax({
        url: `http://localhost:8080/api/homes/search/${search}`,
        type: "GET",
        success: function (data) {
            showHome(data);
            numberPage = 0;
            arrHome = data;
            listDisplayPage = arrHome.reverse();
            showPage();
        }
    })
    event.preventDefault();
}


function displayOneImg(id) {
    var settings = {
        "url": `http://localhost:8080/api/homes/img/${id}`,
        "method": "GET",
        "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
        let content = "";
        for (let i = 0; i < response.length; i++) {
            content += `<img style="width: 100px" src="../../src/main/resources/static/image/${response[i].image}" alt=""/>`
            break;
        }
        document.getElementById("img" + id).innerHTML = content;
    });
}

// phan trang


function showPage() {
    let data = listDisplayPage;
    let elementPage = 8;
    totalPage = Math.ceil(data.length / elementPage);
    let startPage = (numberPage * elementPage);
    let endPage = ((numberPage + 1) * elementPage);
    let subArr = data.slice(startPage, endPage);
    showHome(subArr);
    showFootPage();
    checkRoleHome();
    console.log(totalPage)
}

function showFootPage() {
    let content = `<div id="footPage">
                     <button class="btn btn-outline-primary" id="previous" onclick="previousPage(numberPage)">Previous</button>
                     <span>${numberPage + 1}/${totalPage}</span>
                     <button class="btn btn-outline-primary" id="next" onclick="nextPage(numberPage)">Next</button>
                     </div>`
    document.getElementById("footPage").innerHTML = content;
    if (numberPage === 0) {
        $("#previous").hide();
    } else if (numberPage === totalPage - 1) {
        $("#next").hide();
    }
}

function previousPage(page) {
    numberPage = page - 1;
    showPage();
}

function nextPage(page) {
    numberPage = page + 1;
    showPage();
}

function showHome(data) {
    let content = ""
    for (let i = 0; i < data.length; i++) {
        if (data[i].deleted == null && data[i].status.idStatus === 1) {
            content += `
<div class="col-xl-3 col-lg-4 col-md-6">
    <div class="product-item">
        <p class="position-relatbg-light overflow-hidden">
        <p id="\img${data[i].idHome}\"></p>
        </div>
        <div class="text-center p-12">
            <span class="d-block h5 mb-2">${data[i].name}</span>
            <span class="text-danger me-1">Giá: ${data[i].price} VND</span>
            <span class="text-body"> Phòng ngủ: ${data[i].bedroom_count}</span><br>
            <span class="text-body"> Phòng tắm: ${data[i].bathroom_count}</span><br>
            <span class="text-body"> Địa chỉ: ${data[i].address.name},${data[i].address.city.name}</span>
        </div>
        <div class="d-flex border-top"  >
            <small class="w-100 text-center border-end  py-2" >
                <button style="border: none;background: none;display: none" onclick="toBill(${data[i].idHome})" class="booking text-body"><i class="far fa-heart text-primary me-2"></i>Đặt ngay</button>
            </small>
            <small class="w-100 text-center border-end py-2">
                <button class="text-body" style="border: none;background: none;" onclick="detailHome()" ><i class="fa fa-eye text-primary me-2"></i>Chi tiết</button>
            </small>
           
        </div>
    </div>
</div>`
            displayImg(data[i].idHome)

        }
    }

    document.getElementById("homes").innerHTML = content
}
function updateHs(id) {
    localStorage.setItem("idUpdate",id)
    window.location.href="createHomeStay.html"
}
function deleteHs(id) {
    if (confirm("Bạn có chắc muốn xóa?")) {
    $.ajax({
        url: `http://localhost:8080/api/homes/delete/${id}`,
        type: "GET",
        success: function () {
            alert("Delete successfully!")
            // displayAll1()
            DisplayAllHomestay()
        }
    })}
}
function displayImg(idHome) {
    var settings = {
        "url": `http://localhost:8080/api/homes/img/${idHome}`,
        "method": "GET",
        "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
        let content = "";
        for (let i = 0; i < response.length; i++) {
            content += `<img style="height: 250px; width: 350px"  src="../../src/main/resources/static/image/${response[i].image}" alt=""/>`

            console.log(content)
            break;
        }
        document.getElementById("img" + idHome).innerHTML = content;
    });
}

let arr;


function displayAll1() {
    let acc = localStorage.getItem("account");
    $.ajax({
        url: "http://localhost:8080/api/homes",
        type: "GET",
        success: function (data) {
            console.log(data)
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
            let j = 1;

            for (let i = 0; i < data.length; i++) {
                if (data[i].deleted == null) {

                    content += `<tr>
                        <td>${j++}</td>
                        <td>${data[i].name}</td>
                        <td>${data[i].address.name} ${data[i].address.city.name}</td>
                        <td>${data[i].bedroom_count}</td>
                        <td>${data[i].bathroom_count}</td>
                        <td>${data[i].description}</td>
                        <td>${data[i].price}</td>
                        <td><p id="\img${data[i].idHome}\"></p></td>
                       

                        <td>${data[i].status.name}</td>
                        <td>${data[i].account}</td>
                        
                        <td><button onclick="updateH(${data[i].idHome})">Update</button></td>
                        <td><button onclick="deleteH(${data[i].idHome})">Delete</button></td>
                        </tr>`
                    displayImg(data[i].idHome);


                }
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
            let content = "<label class='fw-bold' for='select_city'>Thành phố</label><br>"
            content += '<select id="select_city" onchange="displayDistrict()"  class="form-select">';
            content += `<option>--Chọn thành phố--</option>`;
            for (let i = 0; i < data.length; i++) {

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
            let content = "<label class='fw-bold' for='select_district'>Quận/huyện</label><br>"
            content += '<select id="select_district"  class="form-select">';
            for (let i = 0; i < data.length; i++) {
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
            let content = "<label class='fw-bold' for='select_status'>Trạng thái</label><br>"
            content += '<select id="select_status" class="form-select">';
            content += `<option>--Chọn trạng thái--</option>`;
            for (let i = 0; i < data.length; i++) {
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
            idHome: id,
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
    let acc = localStorage.getItem("account");

    formData.append("homes",
        new Blob([JSON.stringify(home)], {type: 'application/json'}))
    formData.append("account", acc)

    $.ajax({
        url: "http://localhost:8080/api/homes",
        type: "POST",
        processData: false,
        contentType: false,
        data: formData,
        success: function () {
            alert("Thành công!")
            displayAll1()
            localStorage.setItem("idUpdate", "-1")
            window.location.href="index.html"
        }
    })
    document.getElementById("form").reset()
    event.preventDefault()
}

function updateH(id) {
    $.ajax({
        url: `http://localhost:8080/api/homes/${id}`,
        type: "GET",
        success: function (data) {
            document.getElementById("name").value = data.name
            document.getElementById("bedroom_count").value = data.bedroom_count
            document.getElementById("bathroom_count").value = data.bathroom_count
            document.getElementById("description").value = data.description
            document.getElementById("price").value = data.price
            localStorage.setItem("idUpdate", data.idHome)
        }
    })
}

function deleteH(id) {
    $.ajax({
        url: `http://localhost:8080/api/homes/delete/${id}`,
        type: "GET",
        success: function () {
            alert("Delete successfully!")
            // displayAll1()
            DisplayAllHomestay()
        }
    })
}

function toBill(idHome) {
    localStorage.setItem("idHome", idHome);
    window.location.href = "bill.html";
}

function rentHome() {
    let acc = localStorage.getItem("account");
    let idHome = localStorage.getItem("idHome");
    let dateNow = new Date();
    let checkin = $("#checkin").val();
    let checkin1 = new Date(checkin)
    if (checkin1 < dateNow) {
        alert('Ngày checkin của bạn không hợp lệ');
        return;
    }
    let checkout = $("#checkout").val();
    let checkout1 = new Date(checkout)
    if (checkout1 <= checkin1) {
        alert('Ngày checkout phải lớn hơn ngày checkin.');
        return;
    }
    let newBill = {
        checkin: checkin,
        checkout: checkout
    }
    let formData = new FormData()
    formData.append("bills",
        new Blob([JSON.stringify(newBill)],
            {type: 'application/json'}))
    formData.append("account", acc)
    $.ajax({
        processData: false,
        contentType: false,
        type: "POST",
        data: formData,
        url: `http://localhost:8080/api/bills/${idHome}`,
        success: function () {
            alert("Bạn đã thuê nhà thành công!")
            localStorage.removeItem("idHome");
            showHome()


        }


    })
}

function historyBill() {
    let username = localStorage.getItem("account");
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: `http://localhost:8080/api/bills/${username}`,
        type: "GET",
        success: function (data) {
            arr = data
            let content = `<div style="margin-top:150px"> <h2 style="text-align: center; color: rgba(175,16,16,0.8); margin: 20px 20px" >Lịch sử thuê nhà của bạn</h2>`
            content += `<table id="display-list" class="table table-striped" style="margin: 10px 10px"><tr>
                        <th scope="col">STT</th>            
                        <th scope="col">Tên nhà</th>
                        <th scope="col">Tên chủ nhà</th>
                        <th scope="col">Ảnh</th>
                        <th scope="col">Địa chỉ</th>
                        <th scope="col">Ngày thuê</th>
                        <th scope="col">Ngày kết thúc</th>
                        <th scope="col">Tổng tiền</th>
                              
                        </tr>`
            let j = 1;
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
                        <td scope="row">${j++}</td>
                        <td scope="row">${data[i].home.name}</td>
                        <td scope="row">${data[i].home.account.username}</td>
                        <td scope="row"><p id="\img${data[i].home.idHome}\"></p></td>
                        <td scope="row">${data[i].home.address.name},${data[i].home.address.city.name}</td>
                        <td scope="row">${data[i].checkin}</td>
                        <td scope="row">${data[i].checkout}</td>
                        <td scope="row">${data[i].totalPrice} VNĐ</td>
                        </tr>`
                displayImg(data[i].home.idHome)
            }
            content += `</table> </div>`
            document.getElementById("bill").innerHTML = content
        }
    })
}

function displayByHost() {
    let username = localStorage.getItem("account");
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: `http://localhost:8080/api/homes//displayByHost/${username}`,
        type: "GET",
        success: function (data) {
            arr = data
            let content = `<h2>List home</h2>`
            content += `<table><tr>
                        <th>STT</th>
                        <th>Tên</th>
                        <th>Địa chỉ</th>
                        <th>Phòng ngủ</th>
                        <th>Phòng tắm</th>
                        <th>Miêu tả</th>
                        <th>Giá</th>
                        <th>Ảnh</th>
                        <th>Trạng thái</th>
                        <th colspan="2">Action</th>
                        </tr>`
            let j = 1;

            for (let i = 0; i < data.length; i++) {
                if (data[i].deleted == null) {

                    content += `<tr>
                        <td>${j++}</td>
                        <td>${data[i].name}</td>
                        <td>${data[i].address.name} ${data[i].address.city.name}</td>
                        <td>${data[i].bedroom_count}</td>
                        <td>${data[i].bathroom_count}</td>
                        <td>${data[i].description}</td>
                        <td>${data[i].price}</td>
                        <td><p id="\img${data[i].idHome}\"></p></td>
                        <td>${data[i].status.name}</td>
                        <td><button onclick="updateH(${data[i].idHome})">Update</button></td>
                        <td><button onclick="deleteH(${data[i].idHome})">Delete</button></td>
                        </tr>`
                    displayImg(data[i].idHome);


                }
            }
            content += `</table>`
            document.getElementById("homes").innerHTML = content
        }
    })

}

function DisplayAllHomestay1() {
    $.ajax({
        url: "http://localhost:8080/api/homes",
        type: "GET",
        success: function (data) {
            showHome1(data)
            numberPage = 0;
            arrHome = data;
            listDisplayPage = data.reverse();
            showPage();
            // checkRoleHome()
        }
    })
}
function showHome1(data) {
    let username = localStorage.getItem("account")
    let content = ""
    for (let i = 0; i < data.length; i++) {
        if (username === data[i].account.username) {
            if (data[i].deleted == null) {
                content += `
<div class="col-xl-3 col-lg-4 col-md-6">
    <div class="product-item">
        <p class="position-relatbg-light overflow-hidden">
        <p id="\img${data[i].idHome}\"></p>
        </div>
        <div class="text-center p-4">
            <span class="d-block h5 mb-2">${data[i].name}</span>
            <span class="text-primary me-1">Giá: ${data[i].price}</span>
            <span class="text-body"> Phòng ngủ: ${data[i].bedroom_count}</span><br>
            <span class="text-body"> Phòng tắm: ${data[i].bathroom_count}</span><br>
            <span class="text-body"> Địa chỉ: ${data[i].address.name},${data[i].address.city.name}</span>
        </div>
        <div class="border-top" >
           
            <div style="display: flex;margin-left: 5%">
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
            <button   onclick="updateHs(${data[i].idHome})" class="update btn btn-info " >Sửa</button>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
            <button onclick="deleteHs(${data[i].idHome})" class="delete btn btn-danger" >Xóa</button>
             </div>
        </div>
    </div>
</div>`
                displayImg(data[i].idHome)

            }
        }
    }

    document.getElementById("host").innerHTML = content
}

