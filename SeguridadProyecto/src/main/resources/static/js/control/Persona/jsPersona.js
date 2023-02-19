app.controller('gestionPersonasController', ['$scope', 'SweetAlert', 'requestService', function($scope, SweetAlert, requestService) {
    $scope.registroPorPagina = 10;
    $scope.currentPage = 1;
    $scope.personas = []

    $scope.rembemberCurrentPage = (pagina) =>{
        $scope.currentPage = pagina;
    }

    $scope.consultarPersona = () =>{
        requestService.getRequest("/api/admin/personas/listar",
            (success) => {
                    $scope.personas = success.data,
                    $scope.personas =  $scope.personas.map(persona => ({...persona, nombre: persona.nombre + " " + persona.apellidoPaterno + " " +  persona.apellidoMaterno }))
            },
            (error) => $scope.errorhttp(error.status)
        )

    }


    $scope.errorhttp = function (status) {
        switch (Number(status)) {
            case 401:
                SweetAlert.swal({
                    title: "Sesión expirada",
                    text: "Por tu seguridad tu sesión ha sido cerrada.",
                    type: "error",
                    timer: 5000,
                    allowEscapeKey: false,
                    showCancelButton: false,
                    showConfirmButton: false
                });
                setTimeout(function () {
                    window.location.replace("/");
                }, 5000);
                break;
            case 403:
                SweetAlert.swal({
                    title: "Acceso denegado",
                    text: "Lamentablemente no cuentas con los permisos necesarios para realizar esta acción",
                    type: "error",
                    timer: 3000
                });
                break;
            case 500:
                SweetAlert.swal({
                    title: "Error interno",
                    text: "Un error interno ocurrió dentro del sistema",
                    type: "error",
                    timer: 3000
                });
                break;
            default:
                SweetAlert.swal({title: "Error interno", text: "Ocurrio un error", type: "error", timer: 3000});
        }
    }
}]);