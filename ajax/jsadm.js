function onch () {
    let sl = document.getElementById("mySelect").value ;

    switch (sl) {
        case "1" : displayByAdm();
        break ;
        case "2" : displayByRole(2)
        break ;
        case "3" : displayByRole(3)
        break ;
        case "4" : displayByChange() ;
        break ;
    }
}
function displayByChange () {
    var settings = {
        "url": `http://localhost:8080/api/users/role/2`,
        "method": "GET",
        "timeout": 0,
        "headers": {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZW5hIiwiaWF0IjoxNjk1ODc0ODcwLCJleHAiOjg4MDk1ODc0ODcwfQ.jr8kKgRG4vrnAzXuOASjnyn4WoA2qP98AqrdM94P4_Q"
        },
    };

    $.ajax(settings).done(function (data) {
        let flag = true ;
            content = `<table>
                        <tr> 
                        <th>Tên </th>
                        <th colspan="2">Lựa Chọn </th>
                        </tr>
`
        for (let i = 0; i < data.length ; i++) {
            if(data[i].changeRole == 1) {
                flag = false ;
                content += `<tr>
                               <td>${data[i].fullName}</td> 
                               <td>  <button onclick="allowAcc(${data[i].idUser})">Cho phép</button></td> 
                               <td>  <button onclick="refuseAcc(${data[i].idUser})">Từ Chối</button></td> 
                            </tr>`
            }
        }
        content += `</table>`
            document.getElementById("listUser").innerHTML = content


        if(flag) {
        content = `<p>Danh sách trống</p>`
            document.getElementById("listUser").innerHTML = content
        }
    });
}


function displayByRole (role) {
    var settings = {
        "url": `http://localhost:8080/api/users/role/${role}`,
        "method": "GET",
        "timeout": 0,
        "headers": {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZW5hIiwiaWF0IjoxNjk1ODc0ODcwLCJleHAiOjg4MDk1ODc0ODcwfQ.jr8kKgRG4vrnAzXuOASjnyn4WoA2qP98AqrdM94P4_Q"
        },
    };

    $.ajax(settings).done(function (data) {
        if(data.length > 0) {

            content = `<table> <tr>
                        <th>STT</th>
                        <th>Tên</th>
                        <th>Ảnh</th>
                        <th>Tên Đăng Nhập</th>
                        <th>Quyền Hạn</th>
<!--                        // <th>Lựa chọn</th> -->
                        </tr>`;
            for (let i = 0; i < data.length; i++) {
                content += `<tr> 
                        <td> ${i + 1} </td>
                        <td> ${data[i].fullName} </td>
                        <td><img style="width: 100px; height: 100px" src="../src/main/resources/static/image/${data[i].avatar}" alt=""></td>
                        <td> ${data[i].account.username} </td>
                        <td> ${data[i].account.roles[0].name} </td>
<!--                        <td>  <button id="status" onclick=""> </button> -->
                        </tr>`
            }
            content += `</table>`
            document.getElementById("listUser").innerHTML = content
        }else {
            content = `<p>Danh sách trống</p>`
            document.getElementById("listUser").innerHTML = content
        }


    });
}
function displayByAdm () {
    var settings = {
        "url": `http://localhost:8080/api/users/roleAdm`,
        "method": "GET",
        "timeout": 0,
        "headers": {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZW5hIiwiaWF0IjoxNjk1ODc0ODcwLCJleHAiOjg4MDk1ODc0ODcwfQ.jr8kKgRG4vrnAzXuOASjnyn4WoA2qP98AqrdM94P4_Q"
        },
    };

    $.ajax(settings).done(function (data) {
        content = `<table> <tr>
                        <th>STT</th>
                        <th>Tên</th>
                        <th>Ảnh</th>
                        <th>Tên Đăng Nhập</th>
                        <th>Quyền Hạn</th>
<!--                        // <th>Lựa chọn</th> -->
                        </tr>` ;
        for (let i = 0; i < data.length ; i++) {
            content += `<tr> 
                        <td> ${i + 1} </td>
                        <td> ${data[i].fullName} </td>
                        <td><img style="width: 100px; height: 100px" src="../src/main/resources/static/image/${data[i].avatar}" alt=""></td>
                        <td> ${data[i].account.username} </td>
                        <td> ${data[i].account.roles[0].name} </td>
<!--                        <td>  <button id="status" onclick=""> </button> -->
                        </tr>`
        }
        content += `</table>`
        document.getElementById("listUser").innerHTML = content
    });
}


function allowAcc (id) {}
function refuseAcc (id) {}
