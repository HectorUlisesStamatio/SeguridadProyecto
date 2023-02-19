app.factory('requestService',['$http', function ($http) {
    $http.defaults.headers.post["Content-Type"] = "application/json";

    return {
        getRequest: function (url, success, error) {
            return $http({
                method: 'GET',
                dataType: "json",
                url: url
            }).then(success, error);
        },
        postRequest: function (url, data, success, error) {
            $http({
                method: 'POST',
                data: data,
                url: url
            }).then(success, error);
        },
        putRequest: function (url, data, success, error) {
            return $http({
                method: 'PUT',
                data: data,
                url: url
            }).then(success, error);
        },
        deleteRequest: function (url, success, error) {
            return $http({
                method: 'DELETE',
                url: url
            }).then(success, error);
        }
    }
}]);