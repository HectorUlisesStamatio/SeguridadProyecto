app.controller('loginController', ['$scope', 'SweetAlert', 'requestService', function($scope, SweetAlert, requestService) {
    console.log(requestService)
    $scope.mensaje = "Hola Mundo"
    $scope.showAlert = () =>{
        console.log("Entro aqui")
        SweetAlert.swal({
            title: "Alerta de exito",
            text: "Esta es una alerta de éxito.",
            type: "success",
        });
    }
}]);