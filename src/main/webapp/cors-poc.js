$(document).ready(function () {
    $.ajax({
        url: "http://10.80.18.202:8080/cors-poc/cors",
        xhrFields: {
            withCredentials: true
        }
    }).then(function (data) {
        $('.rq-count').append(data);
    });
});
