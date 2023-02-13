let app = angular.module('mensajes',[ 'oitozero.ngSweetAlert']);

app.controller('loginController', ['$scope', 'SweetAlert', function($scope, SweetAlert) {
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