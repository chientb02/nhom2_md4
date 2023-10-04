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
                        <td><button onclick="updateProduct(${data[i].id})">Update</button></td>
                        <td><button onclick="deleteProduct(${data[i].id})">Delete</button></td>
                        </tr>`
            }
            content += `</table>`
            document.getElementById("users").innerHTML = content
        }
    })
}