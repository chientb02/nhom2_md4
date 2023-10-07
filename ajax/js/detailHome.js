
function displayDetail() {
    let idHome=localStorage.getItem("idHome")
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/api/homes/"+idHome,
        type: "GET",
        success: function (data) {
            document.getElementById("detail").innerHTML=`<div class="col-md-6 border-end" >
                <div class="d-flex flex-column justify-content-center">
                    <div class="main_image" id="imageMain"></div>
                    <div class="thumbnail_images">
                        <ul id="thumbnail">
                           
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="p-3 right-side">
                    <div class="d-flex justify-content-between align-items-center">
                        <h3 id="nameDetail">${data.name}</h3>
                    </div>
                    <div class="mt-2 pr-3 content">
                        <p id="descriptionDetail">${data.description}</p>
                    </div>
                    <h3 id="priceDetail"> ${data.price} $</h3>
                    <div class="mt-5">
                        <h3 class="d-flex flex-row" >Địa chỉ: </h3>
                        <span  class="fw-bold" id="addressDetail">${data.address.name}</span>
                        <br>
                        <span class="fw-bold" id="cityDetail">${data.address.city.name}</span>
                        <div class="mt-5">
                            <span class="fw-bold" id="bedroom">Phòng ngủ: ${data.bedroom_count}</span>
                            <br>
                            <span class="fw-bold" id="bathroom">Phòng tắm: ${data.bathroom_count}</span>
                        <div class="mt-5">
                            <label for="checkin"></label>Checkin<input type="date" id="checkin">
                            <label for="checkout"></label>Checkout<input type="date" id="checkout">

                        </div>
                        
                        </div>
                        <div class="buttons d-flex flex-row mt-5 gap-3">
                            <button class="btn btn-outline-dark" onclick="rentHome()">Đặt ngay</button>
                            
                        </div>
                    </div>
                </div>
            </div>`
            displayOneImg(idHome)
            displayImg(idHome)
        }

    })

    // localStorage.setItem("idHome",0)

}
function changeImage(element) {

    var main_prodcut_image = document.getElementById('main_product_image');
    main_prodcut_image.src = element.src;
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
            content += `<li><img onclick="changeImage(this)" width="70" src="../../src/main/resources/static/image/${response[i].image}" alt=""/></li>`
        }
        document.getElementById("thumbnail").innerHTML = content;
    });
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
            content += `<img id="main_product_image" width="350" src="../../src/main/resources/static/image/${response[i].image}" alt=""/>`
            break
        }
        document.getElementById("imageMain").innerHTML = content;
    });
}
