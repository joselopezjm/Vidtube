var app = angular.module("example", []);
app.controller("MyCtrl", ["$scope", "$http", function ($scope, $http) {
        $scope.test = "Mi libro luna de pluton, ha sido un exito...";
    $http.get("./")
}
                          ]);