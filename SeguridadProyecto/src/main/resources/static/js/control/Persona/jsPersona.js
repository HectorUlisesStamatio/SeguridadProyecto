app.controller('gestionPersonasController', ['$scope', 'SweetAlert', 'requestService', function($scope, SweetAlert, requestService) {
    $scope.registroPorPagina = 10;
    $scope.currentPage = 1;
    $scope.personas = [];
    $scope.persona = {}
    $scope.personaAux = {};
    $scope.personaDetalle = {};

    let mdlModificarPersona = new bootstrap.Modal(document.getElementById('mdlModificarPersona'), {
        keyboard: false
    })
    let mdlRegistrarPersona = new bootstrap.Modal(document.getElementById('mdlRegistrarPersona'), {
            keyboard: false
    })

    $scope.resetForm = () =>{
        $scope.createForm.$setPristine();
        $scope.createForm.$setSubmitted();
        $scope.createForm.$setUntouched();
        $scope.createForm.$setDirty();
    }


    $scope.rembemberCurrentPage = (pagina) =>{
        $scope.currentPage = pagina;
    }

    $scope.consultarPersona = () =>{
        requestService.getRequest("/api/admin/personas/listar",
            (success) => {
                    $scope.personas = success.data;
                    $scope.personas =  $scope.personas.map(persona => ({...persona, nombre: persona.nombre + " " + persona.apellidoPaterno + " " +  persona.apellidoMaterno }))
            },
            (error) => $scope.errorhttp(error.status)
        )

    }

    $scope.mdlModificarPersona = (persona) =>{
        requestService.getRequest("/api/admin/personas/"+persona.id,
            (success) => {
                $scope.personaAux = success.data;
            },
            (error) => {
                mdlModificarPersona.hide()
                $scope.errorhttp(error.status)
            }
        )
    }

    $scope.mdlModificarPersonaActualizar = (id) =>{
        requestService.putRequest(
            "/api/admin/personas/"+id,
            $scope.personaAux,
            (success) => {
                mdlModificarPersona.hide()
                SweetAlert.swal({
                    title: "Alerta de éxito",
                    text: "Actualización exitosa.",
                    type: "success",
                });
                $scope.consultarPersona();
            },
            (error) => {
                $scope.errorhttp(error.status)
            }
        )
    }


    $scope.mdlEliminarPersona = (persona) =>{

        SweetAlert.swal({
            title: "Eliminación de persona",
            text: `¿Estás seguro de eliminar a ${persona.nombre}?`,
            type: "info",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Aceptar",
            cancelButtonText: "Cancelar",
            closeOnConfirm: false,
            closeOnCancel: true,
        }, function (isConfirm) {
            if (isConfirm) {
                /*Obtiene los métodos*/
                requestService.deleteRequest(
                    "/api/admin/personas/" + persona.id,

                    /*Si la petición se ejecutó exitosamente*/
                    (success) => {
                        SweetAlert.swal({
                            title: "Alerta de éxito",
                            text: "Eliminación exitosa.",
                            type: "success",
                        });
                        $scope.consultarPersona();
                    },
                    (error) => {
                        /*Ejecuta el método errorhttp
                        * y dependiendo del status es el mensaje que mostará*/
                        $scope.errorhttp(error.status)
                    }
                )
            }
        });

    }

    $scope.mdlDetallePersona = (persona) =>{
        requestService.getRequest("/api/admin/personas/"+persona.id,
            (success) => {
                $scope.personaDetalle = angular.copy(success.data);
            },
            (error) => {
                $scope.errorhttp(error.status)
            }
        )
    }

    $scope.mdlRegistrarPersona = () =>{
        requestService.postRequest("/api/admin/personas/",
            $scope.persona,
            (success) => {
                mdlRegistrarPersona.hide()
                SweetAlert.swal({
                    title: "Alerta de éxito",
                    text: "Registro exitoso.",
                    type: "success",
                });
                $scope.persona = {}
                $scope.resetForm()
                $scope.consultarPersona();
            },
            (error) => {
                mdlRegistrarPersona.hide()
                $scope.errorhttp(error.status)
            }
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