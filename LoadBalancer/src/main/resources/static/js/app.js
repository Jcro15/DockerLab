var api = (function () {
    var url=window.location.href+'mensajes';

    function addMessage (){
        var mensaje=document.getElementById("mensaje").value;
        axios.post(url,mensaje)
            .then(res => {
                getMessages()
            }
        )
    }
    function getMessages(){

        $("#TMensajes > tbody").empty();
        axios.get(url).then(res=>{
            console.log(res.data)
            res.data.map(mensaje=>{
                console.log(mensaje)
                $("#TMensajes > tbody").append(
                    "<tr>" +
                    " <td>" + mensaje.mensaje + "</td>" +
                    "<td>" + mensaje.fecha + "</td> " +
                    "</tr>"
                );
            })
        })
    }




    return {
        addMessage : addMessage,
        getMessages:getMessages
    };
})();
