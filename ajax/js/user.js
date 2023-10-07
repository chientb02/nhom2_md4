let arr;
function displayAll() {
    $.ajax({
        url: "http://localhost:8080/api/users",
        type: "GET",
        success: function (data) {
            arr = data
            let content = `<h2>List user</h2>`
            content += `<table><tr>
                        <th>STT</th>
                        <th>Name</th>
                        <th>Avatar</th>
                        <th>Gender</th>
                        <th>Age</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th colspan="2">Action</th>
                        </tr>`
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
                        <td>${i + 1}</td>
                        <td>${data[i].fullName}</td>
                        <td><img style="width: 100px; height: 100px" src="../src/main/resources/static/image/${data[i].avatar}" alt=""></td>
                        <td>${data[i].sex}</td>
                        <td>${data[i].age}</td>
                        <td>${data[i].address}</td>
                        <td>${data[i].phone}</td>
                        <td><button onclick="updateUser(${data[i].idUser})">Update</button></td>
                        </tr>`
            }
            content += `</table>`
            document.getElementById("users").innerHTML = content
        }
    })
}
function updateUser(id) {
    $.ajax({
        url: `http://localhost:8080/api/users/${id}`,
        type: "GET",
        success: function (data) {
            document.getElementById("name").value = data.fullName
            document.getElementById("sex").value = data.sex
            document.getElementById("age").value = data.age
            document.getElementById("address").value = data.address
            document.getElementById("phone").value = data.phone
            localStorage.setItem("img", data.image)
            localStorage.setItem("idUpdate", data.idUser)
        }
    })
}

function save1() {
    let user
    let name = $("#name").val()
    let sex = $("#sex").val()
    let age = $("#age").val()
    let address = $("#address").val()
    let phone = $("#phone").val()
    let file = $("#file")[0].files[0]
    let id = +localStorage.getItem("idUpdate")

    if (file === undefined) {
        file = new File([], "", undefined)
    }

    if (id !== -1) {
        user = {
            idUser: id,
            fullName: name,
            sex: sex,
            age: age,
            address: address,
            phone: phone,
            avatar: localStorage.getItem("img")
        }
    } else {
        user = {
            fullName: name,
            sex: sex,
            age: age,
            address: address,
            phone: phone
        }
    }

    let formData = new FormData()
    formData.append("user",
        new Blob([JSON.stringify(user)], {type: 'application/json'}))
    formData.append("file", file)

    $.ajax({
        url: "http://localhost:8080/api/users",
        type: "POST",
        processData: false,
        contentType: false,
        data: formData,
        success: function () {
            alert("Update successfully!")
            displayAll()
            localStorage.setItem("idUpdate", "-1")
        }
    })
    document.getElementById("form").reset()
    event.preventDefault()
}