
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
            // console.log(data)
            // document.getElementById("nameDetail").value=data.name;
            // document.getElementById("descriptionDetail").value=data.description;
            // document.getElementById("priceDetail").value=data.price;
            // document.getElementById("addressDetail").value=data.address.name;
            // document.getElementById("cityDetail").value=data.address.city.name;
            // document.getElementById("bedroom").value=data.bedroom_count;
            // document.getElementById("bathroom").value=data.bathroom_count;
            let text = `<div class="d-flex flex-column justify-content-center">
                    <div class="main_image">
                        <img src="https://i.imgur.com/TAzli1U.jpg" id="main_product_image" width="350">
                    </div>
                    <div class="thumbnail_images">
                        <ul id="thumbnail">
                            <li><img onclick="changeImage(this)" src="https://i.imgur.com/TAzli1U.jpg" width="70"></li>
                            <li><img onclick="changeImage(this)" src="https://i.imgur.com/w6kEctd.jpg" width="70"></li>
                            <li><img onclick="changeImage(this)" src="https://i.imgur.com/L7hFD8X.jpg" width="70"></li>
                            <li><img onclick="changeImage(this)" src="https://i.imgur.com/6ZufmNS.jpg" width="70"></li>
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
                    <h3 id="priceDetail"> ${data.price}$</h3>
                    <div class="mt-5">
                        <h3 class="d-flex flex-row" >Địa chỉ: </h3>
                        <span  class="fw-bold" id="addressDetail"></span>
                        <span class="fw-bold" id="cityDetail"></span>
                        <div class="mt-5">
                            <span class="fw-bold" id="bedroom"></span>
                            <br>
                            <span class="fw-bold" id="bathroom"></span>

                        </div>
                        <div class="buttons d-flex flex-row mt-5 gap-3">
                            <button class="btn btn-outline-dark">Đặt ngay</button>
                            <button class="btn btn-dark">Ưa thích</button>
                        </div>

                    </div>
                </div>
            </div>`
        }
    })

    // localStorage.setItem("idHome",0)

}
function changeImage(element) {

    var main_prodcut_image = document.getElementById('main_product_image');
    main_prodcut_image.src = element.src;


}